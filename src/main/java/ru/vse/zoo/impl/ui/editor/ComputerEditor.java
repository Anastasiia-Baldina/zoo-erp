package ru.vse.zoo.impl.ui.editor;

import ru.vse.zoo.Editor;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.thing.Computer;

import java.util.List;

public class ComputerEditor implements Editor<Computer> {
    private static final List<String> OPTIONS = List.of(
            "Change computer name",
            "Change processor type",
            "Change RAM size",
            "Exit"
    );
    private final UI ui;

    public ComputerEditor(UI ui) {
        this.ui = ui;
    }

    @Override
    public Computer create(int inventoryNumber) {
        Computer c = new Computer(inventoryNumber);
        inputName(c);
        inputProcessor(c);
        inputRamSize(c);

        return c;
    }

    @Override
    public void update(Computer inventory) {
        boolean cancel = false;
        while (!cancel) {
            ui.printLine(inventory.toString());
            switch (ui.selectOption(OPTIONS)) {
                case 1 -> inputName(inventory);
                case 2 -> inputProcessor(inventory);
                case 3 -> inputRamSize(inventory);
                default -> cancel = true;
            }
        }
    }

    private void inputName(Computer c) {
        c.setName(ui.readString("Input computer name"));
    }

    private void inputProcessor(Computer c) {
        c.setProcessor(ui.readString("Input processor type"));
    }

    private void inputRamSize(Computer c) {
        c.setRamMemoryGb(ui.readInt("Input RAM size (Gb)"));
    }

    @Override
    public Class<Computer> getType() {
        return Computer.class;
    }
}
