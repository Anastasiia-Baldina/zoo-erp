package ru.vse.zoo;

import ru.vse.zoo.impl.inventory.base.Animal;
import ru.vse.zoo.impl.inventory.base.Thing;
import ru.vse.zoo.impl.inventory.person.Employee;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс объединяющий логику работы зоопарка
 */
public interface ZooApi {

    /**
     * Принять инвентаризационное средство на учет
     * @param inventory - инвентаризационное средство
     * @return true если принято
     */
    boolean accept(Inventory inventory);

    /**
     * Обследовать животное
     * @param inventoryNumber инвентарный номер
     * @return обследованное животное или null, если животное не найдено
     */
    Animal examineAnimal(int inventoryNumber);

    /**
     * Поиск инвентаризационного средства
     * @param inventoryNumber инвентарный номер
     * @return инвентаризационное средство или null, если не найдено
     */
    Inventory search(int inventoryNumber);

    /**
     * Снять с учета инвентаризационное средство
     * @param inventoryNumber инвентарный номер
     * @return удаленное средтсво или null, если не найдено
     */
    Inventory remove(int inventoryNumber);

    /**
     * Отдает список животных
     * @return список животных
     */
    List<? extends Animal> listAnimals();

    /**
     * Отдает список животных для контактного зоопарка
     * @return список животных
     */
    List<? extends Animal> listContactableAnimals();

    /**
     * Отдает список предметов
     * @return список предметов
     */
    List<? extends Thing> listThings();

    /**
     * Отдает сгруппированную по видам животных таблицу потребностей в пище
     * @return таблица потребностей в пище
     */
    Map<String, Integer> getFoodRequirements();

    /**
     * Отдает список сотрудников
     * @return список сотрудников
     */
    List<? extends Employee> listEmployee();

    /**
     * Генерирует инвентаризационный номер
     * @return инвентаризационный номер
     */
    int generateInventoryNumber();

    /**
     * Сохраняет состояние данных
     */
    void save();

    /**
     * Восстанавливает состояние данных
     */
    void load();
}
