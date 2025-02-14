package ru.vse.zoo;

/**
 * Спецификация животного
 */
public interface Alive {
    /**
     * Возвращает количество потребляемой еды
     *
     * @return количество потребляемой еды
     */
    int getFood();

    /**
     * Возвращает кличку/имя
     *
     * @return кличка животного
     */
    String getName();
}
