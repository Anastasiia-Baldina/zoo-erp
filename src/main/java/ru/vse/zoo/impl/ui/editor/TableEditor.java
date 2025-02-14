package ru.vse.zoo.impl.ui.editor;

import ru.vse.zoo.Editor;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.thing.Table;

import java.util.List;

public class TableEditor implements Editor<Table> {
    private static final List<String> OPTIONS = List.of(
            "Change table name",
            "Change table height",
            "Change table width",
            "Change table length",
            "Exit"
    );
    private final UI ui;

    public TableEditor(UI ui) {
        this.ui = ui;
    }

    @Override
    public Table create(int inventoryNumber) {
        Table inventory = new Table(inventoryNumber);
        inputName(inventory);
        inputHeight(inventory);
        inputWidth(inventory);
        inputLength(inventory);

        return inventory;
    }

    @Override
    public void update(Table inventory) {
        boolean cancel = false;
        while (!cancel) {
            ui.printLine(inventory.toString());
            switch (ui.selectOption(OPTIONS)) {
                case 1 -> inputName(inventory);
                case 2 -> inputHeight(inventory);
                case 3 -> inputWidth(inventory);
                case 4 -> inputLength(inventory);
                default -> cancel = true;
            }
        }
    }

    private void inputName(Table t) {
        t.setName(ui.readString("Input table name"));
    }

    private void inputHeight(Table t) {
        t.setHeight(ui.readInt("Input table height"));
    }

    private void inputWidth(Table t) {
        t.setWidth(ui.readInt("Input table width"));
    }

    private void inputLength(Table t) {
        t.setLength(ui.readInt("Input table length"));
    }

    @Override
    public Class<Table> getType() {
        return Table.class;
    }
}
