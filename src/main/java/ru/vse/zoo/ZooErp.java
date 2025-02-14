package ru.vse.zoo;

import ru.vse.zoo.impl.inventory.base.Animal;
import ru.vse.zoo.impl.inventory.base.Thing;
import ru.vse.zoo.impl.inventory.person.Employee;
import ru.vse.zoo.util.Ensure;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ZooErp {
    private static final List<String> MAIN_MENU = List.of(
            "Accept animals",
            "Print animals",
            "Print friendly & healthy animals (for contact zoo)",
            "Examine animal in clinic",
            "Print food requirements",
            "Accept thing",
            "Print things",
            "Search inventory",
            "Edit inventory",
            "Remove inventory",
            "Accept employee",
            "Print employees",
            "Exit");
    private final UI ui;
    private final ZooApi zooApi;
    private final EditorFactory editorFactory;

    public ZooErp(UI ui, ZooApi zooApi, EditorFactory editorFactory) {
        this.ui = ui;
        this.zooApi = zooApi;
        this.editorFactory = editorFactory;
    }

    public void start() {
        restore();
        boolean exit = false;
        while (!exit) {
            try {
                int choose = ui.selectOption(MAIN_MENU);
                switch (choose) {
                    case 1 -> acceptAnimal();
                    case 2 -> printAnimals();
                    case 3 -> printFriendlyAnimals();
                    case 4 -> examineAnimal();
                    case 5 -> printFoodRequirements();
                    case 6 -> acceptThing();
                    case 7 -> printThings();
                    case 8 -> searchInventory();
                    case 9 -> editInventory();
                    case 10 -> removeInventory();
                    case 11 -> acceptEmployee();
                    case 12 -> printEmployees();
                    default -> exit = true;
                }
            } catch (Exception e) {
                ui.printLine("Error: " + e.getMessage());
            }
        }
        save();
    }

    private void restore() {
        try {
            zooApi.load();
        } catch (Exception e) {
            ui.printLine("Couldn't restore application: " + e.getMessage());
        }
    }

    private void save() {
        try {
            zooApi.save();
        } catch (Exception e) {
            ui.printLine("Couldn't save application: " + e.getMessage());
        }
    }

    private void acceptAnimal() {
        ui.printLine("=== accept animal ===");
        Map<String, Class<? extends Inventory>> animalTypes = editorFactory.getKnownTypes().stream()
                .filter(Animal.class::isAssignableFrom)
                .collect(Collectors.toMap(Class::getSimpleName, x -> x));

        List<String> options = animalTypes.keySet().stream()
                .sorted()
                .collect(Collectors.toList());
        options.add("Exit");

        int idx = ui.selectOption(options) - 1;
        if (idx > -1 && idx < options.size()) {
            var animalType = animalTypes.get(options.get(idx));
            Inventory inv = editorFactory.getEditor(animalType)
                    .create(zooApi.generateInventoryNumber());
            if (inv != null && zooApi.accept(inv)) {
                ui.printLine("Animal accepted: " + inv);
            }
        }
        ui.printLine("=====================");
    }

    private void acceptThing() {
        ui.printLine("=== accept thing ===");
        Map<String, Class<? extends Inventory>> thingTypes = editorFactory.getKnownTypes().stream()
                .filter(Thing.class::isAssignableFrom)
                .collect(Collectors.toMap(Class::getSimpleName, x -> x));

        List<String> options = thingTypes.keySet().stream()
                .sorted()
                .collect(Collectors.toList());
        options.add("Exit");

        int idx = ui.selectOption(options) - 1;
        if (idx > -1 && idx < options.size()) {
            var animalType = thingTypes.get(options.get(idx));
            Inventory inv = editorFactory.getEditor(animalType)
                    .create(zooApi.generateInventoryNumber());
            if (inv != null && zooApi.accept(inv)) {
                ui.printLine("Thing accepted: " + inv);
            }
        }
        ui.printLine("====================");
    }

    private void acceptEmployee() {
        ui.printLine("=== accept employee ===");
        Inventory inv = Ensure.notNull(editorFactory.getEditor(Employee.class), "EmployeeEditor")
                .create(zooApi.generateInventoryNumber());
        if (inv != null && zooApi.accept(inv)) {
            ui.printLine("Employee accepted: " + inv);
        }
        ui.printLine("=======================");
    }

    private void printThings() {
        ui.printLine("==== list of things ===");
        zooApi.listThings().forEach(x -> {
            var info = "N:" + x.getNumber() + ", " + x.getClass().getSimpleName() + ": " + x.getName();
            ui.printLine(info);
        });
        ui.printLine("=======================");
    }

    private void printAnimals() {
        ui.printLine("==== list of animals ====");
        zooApi.listAnimals().forEach(x -> {
            var info = "N:" + x.getNumber() + ", " + x.getClass().getSimpleName() + ": " + x.getName();
            ui.printLine(info);
        });
        ui.printLine("=========================");
    }

    private void printFriendlyAnimals() {
        ui.printLine("==== list of friendly & healthy animals ====");
        zooApi.listContactableAnimals().forEach(x -> {
            var info = "N:" + x.getNumber() + ", " + x.getClass().getSimpleName() + ": " + x.getName();
            ui.printLine(info);
        });
        ui.printLine("============================================");
    }

    private void examineAnimal() {
        ui.printLine("=== examine animal in clinic ====");
        int number = ui.readInt("Input animal inventory number");
        var animal = zooApi.examineAnimal(number);
        if (animal == null) {
            ui.printLine("Animal not found by number " + number);
        } else {
            ui.printLine(animal.toString());
        }
        ui.printLine("=================================");
    }

    private void searchInventory() {
        ui.printLine("=== search inventory ===");
        int number = ui.readInt("Input inventory number");
        Inventory inv = zooApi.search(number);
        if (inv == null) {
            ui.printLine("Inventory not found by number " + number);
        } else {
            ui.printLine(inv.toString());
        }
        ui.printLine("========================");
    }

    @SuppressWarnings("unchecked")
    private void editInventory() {
        ui.printLine("=== search inventory ===");
        int number = ui.readInt("Input inventory number");
        Inventory inv = zooApi.search(number);
        if (inv == null) {
            ui.printLine("Inventory not found by number " + number);
        } else {
            var editor = (Editor<Inventory>) Ensure.notNull(editorFactory.getEditor(inv.getClass()), inv.getClass().getSimpleName() + "editor");
            editor.update(inv);
        }
        ui.printLine("========================");
    }


    private void removeInventory() {
        ui.printLine("=== remove inventory ===");
        int number = ui.readInt("Input inventory number");
        Inventory inv = zooApi.remove(number);
        if (inv == null) {
            ui.printLine("Inventory not found by number " + number);
        } else {
            ui.printLine("Inventory removed " + inv.toString());
        }
        ui.printLine("========================");
    }

    private void printFoodRequirements() {
        ui.printLine("==== food requirements ===");
        zooApi.getFoodRequirements().forEach((k, v) -> {
            ui.printLine(k + ": " + v);
        });
        ui.printLine("==========================");
    }

    private void printEmployees() {
        ui.printLine("=== list of employee ===");
        zooApi.listEmployee().forEach(x -> {
            var info = "N:" + x.getNumber() + ", " + x.getPosition() + ": " + x.getName();
            ui.printLine(info);
        });
        ui.printLine("========================");
    }

}
