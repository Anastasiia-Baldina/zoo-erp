package ru.vse.zoo;

import java.time.Instant;

/**
 * Обследуемое в клинике животное
 */
public interface Observable {

    /**
     * Устанавливает состояние здоровья животного
     * @param healthy - true животное здорово
     */
    void setHealthy(boolean healthy);

    /**
     * Устанавливает состояние здоровья животного
     * @return true животное здорово
     */
    Boolean isHealthy();

    /**
     * Возвращает дату обследования животного в клинике
     * @return дата обследования
     */
    Instant getSurveyDate();

    /**
     * Устанавливает дату обследования животного в клинике
     * @param surveyDate дата обследования
     */
    void setSurveyDate(Instant surveyDate);
}
