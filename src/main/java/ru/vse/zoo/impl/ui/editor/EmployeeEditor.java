package ru.vse.zoo.impl.ui.editor;

import ru.vse.zoo.Editor;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.person.Employee;

import java.util.List;

public class EmployeeEditor implements Editor<Employee> {
    private static final List<String> OPTIONS = List.of(
            "Change person name",
            "Change food count",
            "Change position property",
            "Exit"
    );
    private final UI ui;

    public EmployeeEditor(UI ui) {
        this.ui = ui;
    }

    @Override
    public Employee create(int inventoryNumber) {
        Employee inventory = new Employee(inventoryNumber);
        inputName(inventory);
        inputFood(inventory);
        inputPosition(inventory);

        return inventory;
    }

    @Override
    public void update(Employee inventory) {
        boolean cancel = false;
        while (!cancel) {
            ui.printLine(inventory.toString());
            switch (ui.selectOption(OPTIONS)) {
                case 1 -> inputName(inventory);
                case 2 -> inputFood(inventory);
                case 3 -> inputPosition(inventory);
                default -> cancel = true;
            }
        }
    }

    private void inputName(Employee inventory) {
        inventory.setName(ui.readString("Input personal name"));
    }

    private void inputPosition(Employee inventory) {
        inventory.setPosition(ui.readString("Input personal position"));
    }

    private void inputFood(Employee inventory) {
        inventory.setFood(ui.readInt("Input food count"));
    }


    @Override
    public Class<Employee> getType() {
        return Employee.class;
    }
}
