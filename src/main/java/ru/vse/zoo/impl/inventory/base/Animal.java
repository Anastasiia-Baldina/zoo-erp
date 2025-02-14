package ru.vse.zoo.impl.inventory.base;

import ru.vse.zoo.Alive;
import ru.vse.zoo.Inventory;
import ru.vse.zoo.Observable;

import java.time.Instant;

/**
 * Базовый класс 'Животное'
 */
public abstract class Animal implements Alive, Inventory, Observable {
    private int food;
    private String nickname;
    private final int number;
    private Instant surveyDate;
    private Boolean healthy;

    protected Animal(int number) {
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
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return nickname;
    }

    /**
     * Устанавливает количество потребляемой еды
     * @param food количество потребляемой еды
     */
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * Устанавливает кличку животного
     * @param nickname кличка животного
     */
    public void setName(String nickname) {
        this.nickname = nickname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
        this.surveyDate = Instant.now();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isHealthy() {
        return healthy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Instant getSurveyDate() {
        return surveyDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSurveyDate(Instant surveyDate) {
        this.surveyDate = surveyDate;
    }
}
