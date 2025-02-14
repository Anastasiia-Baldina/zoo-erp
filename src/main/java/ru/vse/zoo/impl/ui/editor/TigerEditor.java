package ru.vse.zoo.impl.ui.editor;

import ru.vse.zoo.Editor;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.animal.Tiger;

import java.util.List;

public class TigerEditor implements Editor<Tiger> {
    private static final List<String> OPTIONS = List.of(
            "Change tiger nickname",
            "Change food count",
            "Change rare property",
            "Exit"
    );
    private final UI ui;

    public TigerEditor(UI ui) {
        this.ui = ui;
    }

    @Override
    public Tiger create(int inventoryNumber) {
        Tiger inventory = new Tiger(inventoryNumber);
        inputName(inventory);
        inputFood(inventory);
        inputRare(inventory);

        return inventory;
    }

    @Override
    public void update(Tiger inventory) {
        boolean cancel = false;
        while (!cancel) {
            ui.printLine(inventory.toString());
            switch (ui.selectOption(OPTIONS)) {
                case 1 -> inputName(inventory);
                case 2 -> inputFood(inventory);
                case 3 -> inputRare(inventory);
                default -> cancel = true;
            }
        }
    }

    private void inputName(Tiger inventory) {
        inventory.setName(ui.readString("Input tiger nickname"));
    }

    private void inputFood(Tiger inventory) {
        inventory.setFood(ui.readInt("Input food count"));
    }

    private void inputRare(Tiger inventory) {
        inventory.setRare(ui.readYesOrNo("Is tiger rare"));
    }

    @Override
    public Class<Tiger> getType() {
        return Tiger.class;
    }
}
