package ru.vse.zoo.impl.inventory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ru.vse.zoo.Inventory;
import ru.vse.zoo.Registry;
import ru.vse.zoo.util.Ensure;
import ru.vse.zoo.util.Times;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZooRegistry implements Registry {
    private static final String FILEPATH = "zoo-erp.json";
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(new TypeAdapterFactoryImpl())
            .setDateFormat("dd.MM.yyyy HH:mm:ss")
            .setPrettyPrinting()
            .create();
    private final Map<Integer, Inventory> inventoryMap = new HashMap<>();

    @Override
    public void add(Inventory inventory) {
        Ensure.notNull(inventory, "inventory");
        if (inventoryMap.putIfAbsent(inventory.getNumber(), inventory) != null) {
            throw new IllegalStateException("Inventory with number " + inventory.getNumber() + " already exists.");
        }
    }

    @Override
    public void remove(Inventory inventory) {
        Ensure.notNull(inventory, "inventory");
        inventoryMap.remove(inventory.getNumber());
    }

    @Override
    public Inventory getByNumber(int number) {
        return inventoryMap.get(number);
    }

    @Override
    public List<Inventory> list() {
        return inventoryMap.values().stream()
                .sorted(Comparator.comparing(Inventory::getNumber))
                .toList();
    }

    @Override
    public void save() {
        try (PrintWriter writer = new PrintWriter(FILEPATH, StandardCharsets.UTF_8)) {
            Map<Integer, Container> storeMap = new HashMap<>();
            inventoryMap.forEach((k, v) ->
                    storeMap.put(k, new Container(v.getClass().getName(), v))
            );
            var json = GSON.toJson(storeMap);
            writer.print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void load() {
        try (JsonReader reader = new JsonReader(new FileReader(FILEPATH))) {
            Type typeOfHashMap = new TypeToken<Map<Integer, Container>>() {
            }.getType();
            Map<Integer, Container> storeMap = GSON.fromJson(reader, typeOfHashMap);
            inventoryMap.clear();
            if (storeMap != null) {
                storeMap.forEach((k, v) -> {
                    var inventory = v.inventory;
                    if (inventory != null) {
                        inventoryMap.put(k, inventory);
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class TypeAdapterFactoryImpl implements TypeAdapterFactory {
        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            var type = typeToken.getType();
            if (type instanceof Class<?> clazz) {
                if (clazz == Instant.class) {
                    return (TypeAdapter<T>) new InstantTypeAdapter();
                } else if (clazz == Container.class) {
                    return new PolymorphTypeAdapter<>(gson, gson.getDelegateAdapter(this, typeToken));
                }
            }
            return null;
        }
    }

    private static class InstantTypeAdapter extends TypeAdapter<Instant> {
        @Override
        public void write(JsonWriter jsonWriter, Instant instant) throws IOException {
            jsonWriter.value(Times.format(instant));
        }

        @Override
        public Instant read(JsonReader jsonReader) throws IOException {
            return Times.parseInstant(jsonReader.nextString());
        }
    }

    private static class Container {
        private String type;
        private Inventory inventory;

        Container(String type, Inventory obj) {
            this.type = type;
            this.inventory = obj;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Inventory getInventory() {
            return inventory;
        }

        public void setInventory(Inventory inventory) {
            this.inventory = inventory;
        }
    }

    private static class PolymorphTypeAdapter<T> extends TypeAdapter<T> {
        private final Gson gson;
        private final TypeAdapter<T> delegate;

        PolymorphTypeAdapter(Gson gson, TypeAdapter<T> delegate) {
            this.gson = gson;
            this.delegate = delegate;
        }

        @Override
        public void write(JsonWriter jsonWriter, T obj) throws IOException {
            delegate.write(jsonWriter, obj);
        }

        @Override
        @SuppressWarnings("unchecked")
        public T read(JsonReader jsonReader) throws IOException {
            String typeName = null;
            Inventory inventory = null;
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                if ("type".equals(name)) {
                    typeName = jsonReader.nextString();
                } else if ("inventory".equals(name) && typeName != null) {
                    Class<?> type;
                    try {
                        type = Class.forName(typeName);
                    } catch (ClassNotFoundException e) {
                        throw new IllegalStateException("Unknown type " + typeName);
                    }
                    if (Inventory.class.isAssignableFrom(type)) {
                        inventory = (Inventory) gson.getAdapter(type).read(jsonReader);
                    }
                }
            }
            jsonReader.endObject();
            return (T) new Container(typeName, inventory);
        }
    }
}
