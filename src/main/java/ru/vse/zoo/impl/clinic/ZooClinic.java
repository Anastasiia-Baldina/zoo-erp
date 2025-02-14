package ru.vse.zoo.impl.clinic;

import ru.vse.zoo.Clinic;
import ru.vse.zoo.UI;
import ru.vse.zoo.Observable;

import java.time.Instant;

/**
 * Реализация интерфейса {@link ru.vse.zoo.Clinic}
 * Решение о состоянии здоровья пациента запрашивается у пользователя через {@link ru.vse.zoo.UI}
 */
public class ZooClinic implements Clinic {
    private final UI gui;

    public ZooClinic(UI gui) {
        this.gui = gui;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean examine(Observable observable) {
        var res = gui.readYesOrNo("Is animal healthy");
        observable.setHealthy(res);
        observable.setSurveyDate(Instant.now());

        return res;
    }
}
