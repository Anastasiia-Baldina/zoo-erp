package ru.vse.zoo.impl.ui.editor;

import ru.vse.zoo.Editor;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.animal.Wolf;

import java.util.List;

public class WolfEditor implements Editor<Wolf> {
    private static final List<String> OPTIONS = List.of(
            "Change wolf nickname",
            "Change food count",
            "Change alfa property",
            "Exit"
    );
    private final UI ui;

    public WolfEditor(UI ui) {
        this.ui = ui;
    }

    @Override
    public Wolf create(int inventoryNumber) {
        Wolf inventory = new Wolf(inventoryNumber);
        inputName(inventory);
        inputFood(inventory);
        inputAlfa(inventory);

        return inventory;
    }

    @Override
    public void update(Wolf inventory) {
        boolean cancel = false;
        while (!cancel) {
            ui.printLine(inventory.toString());
            switch (ui.selectOption(OPTIONS)) {
                case 1 -> inputName(inventory);
                case 2 -> inputFood(inventory);
                case 3 -> inputAlfa(inventory);
                default -> cancel = true;
            }
        }
    }

    private void inputName(Wolf inventory) {
        inventory.setName(ui.readString("Input wolf nickname"));
    }


    private void inputFood(Wolf inventory) {
        inventory.setFood(ui.readInt("Input food count"));
    }

    private void inputAlfa(Wolf inventory) {
        inventory.setAlfa(ui.readYesOrNo("Is wolf alfa"));
    }

    @Override
    public Class<Wolf> getType() {
        return Wolf.class;
    }
}
