package ru.vse.zoo.impl.inventory.animal;

import ru.vse.zoo.impl.inventory.base.Herbo;
import ru.vse.zoo.util.Times;

/**
 * Кролик, травоядное. Может находиться в контактном зоопарке
 */
public class Rabbit extends Herbo {
    private String color;

    public Rabbit(int number) {
        super(number);
    }

    /**
     * Возвращает цвет шерсти
     * @return цвет шерсти
     */
    public String getColor() {
        return color;
    }

    /**
     * Устанавливает цвет шерсти
     * @param color цвет шерсти
     */
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "N:" + getNumber() + ", Monkey(herbo): " + getName() +
                "\n\tfood count: " + getFood() +
                "\n\tis healthy: " + (isHealthy() == Boolean.TRUE ? "yes" : "no") +
                "\n\tis friendly: " + (isFriendly() ? "yes" : "no") +
                "\n\tcolor: " + color +
                "\n\texamine at: " + Times.format(getSurveyDate());
    }
}
