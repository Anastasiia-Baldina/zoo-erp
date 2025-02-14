package ru.vse.zoo.impl.inventory.person;

import ru.vse.zoo.Alive;
import ru.vse.zoo.Inventory;

/**
 * Содрутдник зоопарка
 */
public class Employee implements Alive, Inventory {
    private final int number;
    private int food;
    private String name;
    private String position;

    public Employee(int number) {
        this.number = number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFood() {
        return food;
    }

    /**
     * Установить количество потребляемой еды
     * @param food количество потребляемой еды
     */
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * ФИО сотрудника
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Установить ФИО сотрудника
     * @param name ФИО сотрудника
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * Получить должность сотрудника
     * @return  должность сотрудника
     */
    public String getPosition() {
        return position;
    }

    /**
     * Установить должность сотрудника
     * @param position должность сотрудника
     */
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "N:" + getNumber() + ", Employee: " + getName() +
                "\n\tposition: " + getPosition() +
                "\n\tfood count: " + getFood();
    }
}
