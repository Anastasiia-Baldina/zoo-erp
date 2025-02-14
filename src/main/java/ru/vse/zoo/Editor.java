package ru.vse.zoo;

/**
 * Интерфейс взаимодействия с пользователем для создания и редактирования инвентаризационного средства
 * @param <T> Тип инвентаризационного средства
 */
public interface Editor<T extends Inventory> {

    /**
     * Создать инвентаризационное средство
     * @param inventoryNumber - инвентаризационный номер {@link Inventory#getNumber()}
     * @return инвентаризационное средство
     */
    T create(int inventoryNumber);

    /**
     * Редактировать инвентаризационное средство
     * @param inventory инвентаризационное средство
     */
    void update(T inventory);

    /**
     * Возвращает java-class инвентаризационного средства, для которого предназначен редактор
     * @return java-class инвентаризационного средства
     */
    Class<T> getType();
}
