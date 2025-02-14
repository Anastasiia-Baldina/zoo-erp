package ru.vse.zoo.impl.inventory.animal;

import ru.vse.zoo.impl.inventory.base.Herbo;
import ru.vse.zoo.util.Times;

/**
 * Обезьяна, травоядное. Может находиться в контактном зоопарке
 */
public class Monkey extends Herbo {
    private boolean smart;

    public Monkey(int number) {
        super(number);
    }

    /**
     * Получить признак интеллекта
     * @return true - интеллект высокий
     */
    public boolean isSmart() {
        return smart;
    }

    /**
     * Установить признак интеллекта
     * @param smart true - интеллект высокий
     */
    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    @Override
    public String toString() {
        return "N:" + getNumber() + ", Monkey(herbo): " + getName() +
                "\n\tfood count: " + getFood() +
                "\n\tis healthy: " + (isHealthy() == Boolean.TRUE ? "yes" : "no") +
                "\n\tis friendly: " + (isFriendly() ? "yes" : "no") +
                "\n\tis smart: " + (smart ? "yes" : "no") +
                "\n\texamine at: " + Times.format(getSurveyDate());
    }
}
