package ru.vse.zoo;

/**
 * Интерфейс описывающий работу клиники
 */
public interface Clinic {
    /**
     * Производит обследование состояния здоровья пациента
     * @param observable - пациент
     * @return true - если клиент здоров
     */
    boolean examine(Observable observable);
}
