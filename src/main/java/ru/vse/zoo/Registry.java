package ru.vse.zoo;

import java.util.List;

/**
 * Интерфейс описывающий работу хранилища инвентаризационных средств
 */
public interface Registry {
    /**
     * Поставить на учет инвентаризационное средство
     * @param inventory инвентаризационное средство
     */
    void add(Inventory inventory);

    /**
     * Снять с учета инвентаризационное средство
     * @param inventory инвентаризационное средство
     */
    void remove(Inventory inventory);

    /**
     * Поставить на учет инвентаризационное средство
     * @param number инвентаризационный номер {@link Inventory#getNumber()}
     */
    Inventory getByNumber(int number);

    /**
     * Вывести список инвентаризационных средств, стоящих на учете
     * @return список инвентаризационных средств
     */
    List<Inventory> list();

    /**
     * Сохранить состояние хранилища
     */
    void save();

    /**
     * Восстановить состояние хранилища
     */
    void load();
}
