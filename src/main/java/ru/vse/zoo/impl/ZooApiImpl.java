package ru.vse.zoo.impl;

import ru.vse.zoo.Alive;
import ru.vse.zoo.Clinic;
import ru.vse.zoo.Inventory;
import ru.vse.zoo.Observable;
import ru.vse.zoo.Registry;
import ru.vse.zoo.ZooApi;
import ru.vse.zoo.impl.inventory.base.Animal;
import ru.vse.zoo.impl.inventory.base.Herbo;
import ru.vse.zoo.impl.inventory.base.Thing;
import ru.vse.zoo.impl.inventory.person.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZooApiImpl implements ZooApi {
    private final Registry registry;
    private final Clinic clinic;

    public ZooApiImpl(Registry registry, Clinic clinic) {
        this.registry = registry;
        this.clinic = clinic;
    }

    @Override
    public boolean accept(Inventory inventory) {
        if (inventory instanceof Observable animal) {
            clinic.examine(animal);
            if (!animal.isHealthy()) {
                return false;
            }
        }
        registry.add(inventory);
        return true;
    }

    @Override
    public Animal examineAnimal(int inventoryNumber) {
        Inventory inv = registry.getByNumber(inventoryNumber);
        if (inv instanceof Animal animal) {
            clinic.examine(animal);
            return animal;
        }
        return null;
    }

    @Override
    public Inventory search(int inventoryNumber) {
        return registry.getByNumber(inventoryNumber);
    }

    @Override
    public Inventory remove(int inventoryNumber) {
        Inventory inv = registry.getByNumber(inventoryNumber);
        if (inv != null) {
            registry.remove(inv);
        }
        return inv;
    }

    @Override
    public List<? extends Animal> listAnimals() {
        return registry.list().stream()
                .filter(x -> x instanceof Animal)
                .sorted(Comparator.comparingLong(Inventory::getNumber))
                .map(x -> (Animal) x)
                .toList();
    }

    @Override
    public List<? extends Animal> listContactableAnimals() {
        return registry.list().stream()
                .filter(x -> x instanceof Herbo)
                .map(x -> (Herbo) x)
                .filter(Herbo::isFriendly)
                .filter(Observable::isHealthy)
                .sorted(Comparator.comparingLong(Inventory::getNumber))
                .toList();
    }

    @Override
    public List<? extends Thing> listThings() {
        return registry.list().stream()
                .filter(x -> x instanceof Thing)
                .sorted(Comparator.comparingLong(Inventory::getNumber))
                .map(x -> (Thing) x)
                .toList();
    }

    @Override
    public Map<String, Integer> getFoodRequirements() {
        Map<String, Integer> res = new HashMap<>();
        registry.list().forEach(inv -> {
            if (inv instanceof Alive alive) {
                String typeName = alive.getClass().getSimpleName();
                res.merge(typeName, alive.getFood(), Integer::sum);
            }
        });
        return res;
    }

    @Override
    public List<? extends Employee> listEmployee() {
        return registry.list().stream()
                .filter(x -> x instanceof Employee)
                .sorted(Comparator.comparingLong(Inventory::getNumber))
                .map(x -> (Employee) x)
                .toList();
    }

    @Override
    public int generateInventoryNumber() {
        return registry.list().stream()
                .mapToInt(Inventory::getNumber)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public void save() {
        registry.save();
    }

    @Override
    public void load() {
        registry.load();
    }
}
