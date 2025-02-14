package ru.vse.zoo.impl.inventory.animal;

import ru.vse.zoo.impl.inventory.base.Predator;
import ru.vse.zoo.util.Times;

/**
 * Тигр, хищник.
 */
public class Tiger extends Predator {
    private boolean rare;

    public Tiger(int number) {
        super(number);
    }

    /**
     * Получить признак принадлежности к исчезающему виду
     *
     * @return true - относится к исчезающему виду
     */
    public boolean isRare() {
        return rare;
    }

    /**
     * Установить признак принадлежности к исчезающему виду
     *
     * @param rare true - относится к исчезающему виду
     */
    public void setRare(boolean rare) {
        this.rare = rare;
    }

    @Override
    public String toString() {
        return "N:" + getNumber() + ", Tiger(predator): " + getName() +
                "\n\tis rare: " + (rare ? "yes" : "no") +
                "\n\tfood count: " + getFood() +
                "\n\tis healthy: " + (isHealthy() == Boolean.TRUE ? "yes" : "no") +
                "\n\texamine at: " + Times.format(getSurveyDate());
    }
}
