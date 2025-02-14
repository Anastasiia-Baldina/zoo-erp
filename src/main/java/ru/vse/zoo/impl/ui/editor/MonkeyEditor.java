package ru.vse.zoo.impl.ui.editor;

import ru.vse.zoo.Editor;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.animal.Monkey;

import java.util.List;

public class MonkeyEditor implements Editor<Monkey> {
    private static final List<String> OPTIONS = List.of(
            "Change monkey nickname",
            "Change food count",
            "Change friendly property",
            "Change smart property",
            "Exit"
    );
    private final UI ui;

    public MonkeyEditor(UI ui) {
        this.ui = ui;
    }

    @Override
    public Monkey create(int inventoryNumber) {
        Monkey inventory = new Monkey(inventoryNumber);
        inputName(inventory);
        inputFood(inventory);
        inputFriendly(inventory);
        inputSmart(inventory);

        return inventory;
    }

    @Override
    public void update(Monkey inventory) {
        boolean cancel = false;
        while (!cancel) {
            ui.printLine(inventory.toString());
            switch (ui.selectOption(OPTIONS)) {
                case 1 -> inputName(inventory);
                case 2 -> inputFood(inventory);
                case 3 -> inputFriendly(inventory);
                case 4 -> inputSmart(inventory);
                default -> cancel = true;
            }
        }
    }

    private void inputName(Monkey inventory) {
        inventory.setName(ui.readString("Input monkey nickname"));
    }

    private void inputFood(Monkey inventory) {
        inventory.setFood(ui.readInt("Input food count"));
    }

    private void inputFriendly(Monkey inventory) {
        inventory.setFriendly(ui.readYesOrNo("Is monkey friendly"));
    }

    private void inputSmart(Monkey inventory) {
        inventory.setSmart(ui.readYesOrNo("Is monkey smart"));
    }

    @Override
    public Class<Monkey> getType() {
        return Monkey.class;
    }
}
