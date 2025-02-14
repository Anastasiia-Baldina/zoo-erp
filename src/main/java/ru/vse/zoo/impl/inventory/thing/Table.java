package ru.vse.zoo.impl.inventory.thing;

import ru.vse.zoo.impl.inventory.base.Thing;

public class Table extends Thing {
    private int height;
    private int width;
    private int length;

    public Table(int number) {
        super(number);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "N:" + getNumber() + ", Table: " + getName() +
                "\n\theight: " + getHeight() +
                "\n\twidth: " + getWidth() +
                "\n\tlength: " + getLength();
    }
}
