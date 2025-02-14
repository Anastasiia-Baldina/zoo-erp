package ru.vse.zoo.impl.inventory.animal;

import ru.vse.zoo.impl.inventory.base.Predator;
import ru.vse.zoo.util.Times;

/**
 * Волк, хищник
 */
public class Wolf extends Predator {
    private boolean isAlfa;

    public Wolf(int number) {
        super(number);
    }

    /**
     * Получить признак вожака стаи
     *
     * @return true - вожак стаи
     */
    public boolean isAlfa() {
        return isAlfa;
    }

    /**
     * Установить признак вожака стаи
     *
     * @param alfa true - вожак стаи
     */
    public void setAlfa(boolean alfa) {
        isAlfa = alfa;
    }

    @Override
    public String toString() {
        return "N:" + getNumber() + ", Wolf(predator): " + getName() +
                "\n\tis alfa: " + (isAlfa ? "yes" : "no") +
                "\n\tfood count: " + getFood() +
                "\n\tis healthy: " + (isHealthy() == Boolean.TRUE ? "yes" : "no") +
                "\n\texamine at: " + Times.format(getSurveyDate());
    }
}
