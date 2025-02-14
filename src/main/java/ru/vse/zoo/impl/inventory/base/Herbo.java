package ru.vse.zoo.impl.inventory.base;

import ru.vse.zoo.Observable;

/**
 * Базовый класс 'Травоядное животное'. Может находиться в контактном зоопарке
 * при условии {@link Herbo#isHealthy()} и {@link Herbo#isFriendly()}
 */
public abstract class Herbo extends Animal {
    private boolean friendly;

    protected Herbo(int number) {
        super(number);
    }

    /**
     * Признак дружелюбности
     * @return true - дружелюбно
     */
    public boolean isFriendly() {
        return friendly;
    }

    /**
     * Установить признак дружелюбности
     * @param friendly true - дружелюбно
     */
    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }
}
