package ru.vse.zoo.impl.ui.editor;

import ru.vse.zoo.Editor;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.animal.Rabbit;

import java.util.List;

public class RabbitEditor implements Editor<Rabbit> {
    private static final List<String> OPTIONS = List.of(
            "Change rabbit nickname",
            "Change food count",
            "Change friendly property",
            "Change color property",
            "Exit"
    );
    private final UI ui;

    public RabbitEditor(UI ui) {
        this.ui = ui;
    }

    @Override
    public Rabbit create(int inventoryNumber) {
        Rabbit inventory = new Rabbit(inventoryNumber);
        inputName(inventory);
        inputFood(inventory);
        inputFriendly(inventory);
        inputColor(inventory);

        return inventory;
    }

    @Override
    public void update(Rabbit inventory) {
        boolean cancel = false;
        while (!cancel) {
            ui.printLine(inventory.toString());
            switch (ui.selectOption(OPTIONS)) {
                case 1 -> inputName(inventory);
                case 2 -> inputFood(inventory);
                case 3 -> inputFriendly(inventory);
                case 4 -> inputColor(inventory);
                default -> cancel = true;
            }
        }
    }

    private void inputName(Rabbit inventory) {
        inventory.setName(ui.readString("Input rabbit nickname"));
    }

    private void inputFood(Rabbit inventory) {
        inventory.setFood(ui.readInt("Input food count"));
    }

    private void inputFriendly(Rabbit inventory) {
        inventory.setFriendly(ui.readYesOrNo("Is rabbit friendly"));
    }

    private void inputColor(Rabbit inventory) {
        inventory.setColor(ui.readString("Input rabbit color"));
    }

    @Override
    public Class<Rabbit> getType() {
        return Rabbit.class;
    }
}
