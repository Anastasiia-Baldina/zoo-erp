package ru.vse.zoo;

/**
 * Инвентаризационное средство. Ставится на учет в {@link Registry}
 */
public interface Inventory {
    /**
     * Возвращает инвентарный номер
     * @return инвентарный номер
     */
    int getNumber();
}
