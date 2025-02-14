package ru.vse.zoo.impl.inventory.base;

import ru.vse.zoo.Inventory;

/**
 * Базовый класс 'Предмет'
 */
public abstract class Thing implements Inventory {
    protected final int number;
    private String name;

    protected Thing(int number) {
        this.number = number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * Получить наименование
     * @return наименование
     */
    public String getName() {
        return name;
    }

    /**
     * Установить наименование
     * @param name наименование
     */
    public void setName(String name) {
        this.name = name;
    }
}
