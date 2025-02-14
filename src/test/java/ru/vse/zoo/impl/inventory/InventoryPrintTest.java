package ru.vse.zoo.impl.inventory;

import org.junit.jupiter.api.Test;
import ru.vse.zoo.impl.inventory.animal.Monkey;
import ru.vse.zoo.impl.inventory.animal.Rabbit;
import ru.vse.zoo.impl.inventory.animal.Tiger;
import ru.vse.zoo.impl.inventory.animal.Wolf;
import ru.vse.zoo.impl.inventory.person.Employee;
import ru.vse.zoo.impl.inventory.thing.Computer;
import ru.vse.zoo.impl.inventory.thing.Table;
import ru.vse.zoo.util.Times;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryPrintTest {

    @Test
    public void validate_employee_print() {
        Employee i = new Employee(Integer.MAX_VALUE);
        i.setName(UUID.randomUUID().toString());
        i.setFood(Integer.MAX_VALUE);
        i.setPosition(UUID.randomUUID().toString());

        String expected = "N:" + i.getNumber() + ", Employee: " + i.getName() +
                "\n\tposition: " + i.getPosition() +
                "\n\tfood count: " + i.getFood();

        assertEquals(expected, i.toString());
    }

    @Test
    public void validate_computer_print() {
        Computer i = new Computer(Integer.MAX_VALUE);
        i.setName(UUID.randomUUID().toString());
        i.setProcessor(UUID.randomUUID().toString());
        i.setRamMemoryGb(Integer.MAX_VALUE);

        String expected = "N:" + i.getNumber() + ", Computer: " + i.getName() +
                "\n\tinventory number: " + i.getNumber() +
                "\n\tram memory: " + i.getRamMemoryGb() + "(Gb)" +
                "\n\tprocessor: " + i.getProcessor();

        assertEquals(expected, i.toString());
    }

    @Test
    public void validate_table_print() {
        Table i = new Table(Integer.MAX_VALUE);
        i.setName(UUID.randomUUID().toString());
        i.setHeight(Integer.MAX_VALUE - 10);
        i.setWidth(Integer.MAX_VALUE - 20);
        i.setLength(Integer.MAX_VALUE - 30);

        String expected = "N:" + i.getNumber() + ", Table: " + i.getName() +
                "\n\theight: " + i.getHeight() +
                "\n\twidth: " + i.getWidth() +
                "\n\tlength: " + i.getLength();

        assertEquals(expected, i.toString());
    }

    @Test
    public void validate_monkey_print() {
        Monkey i = new Monkey(Integer.MAX_VALUE);
        i.setName(UUID.randomUUID().toString());
        i.setFood(Integer.MAX_VALUE);
        i.setSmart(true);
        i.setFriendly(true);
        i.setSurveyDate(Instant.now());
        i.setHealthy(true);

        String expected = "N:" + i.getNumber() + ", Monkey(herbo): " + i.getName() +
                "\n\tfood count: " + i.getFood() +
                "\n\tis healthy: " + (i.isHealthy() ? "yes" : "no") +
                "\n\tis friendly: " + (i.isFriendly() ? "yes" : "no") +
                "\n\tis smart: " + (i.isSmart() ? "yes" : "no") +
                "\n\texamine at: " + Times.format(i.getSurveyDate());

        assertEquals(expected, i.toString());
    }

    @Test
    public void validate_rabbit_print() {
        Rabbit i = new Rabbit(Integer.MAX_VALUE);
        i.setName(UUID.randomUUID().toString());
        i.setFood(Integer.MAX_VALUE);
        i.setColor(UUID.randomUUID().toString());
        i.setFriendly(true);
        i.setSurveyDate(Instant.now());
        i.setHealthy(true);

        String expected = "N:" + i.getNumber() + ", Monkey(herbo): " + i.getName() +
                "\n\tfood count: " + i.getFood() +
                "\n\tis healthy: " + (i.isHealthy() ? "yes" : "no") +
                "\n\tis friendly: " + (i.isFriendly() ? "yes" : "no") +
                "\n\tcolor: " + i.getColor() +
                "\n\texamine at: " + Times.format(i.getSurveyDate());

        assertEquals(expected, i.toString());
    }

    @Test
    public void validate_wolf_print() {
        Wolf i = new Wolf(Integer.MAX_VALUE);
        i.setName(UUID.randomUUID().toString());
        i.setFood(Integer.MAX_VALUE);
        i.setAlfa(true);
        i.setSurveyDate(Instant.now());
        i.setHealthy(true);

        String expected = "N:" + i.getNumber() + ", Wolf(predator): " + i.getName() +
                "\n\tis alfa: " + (i.isAlfa() ? "yes" : "no") +
                "\n\tfood count: " + i.getFood() +
                "\n\tis healthy: " + (i.isHealthy() ? "yes" : "no") +
                "\n\texamine at: " + Times.format(i.getSurveyDate());

        assertEquals(expected, i.toString());
    }

    @Test
    public void validate_tiger_print() {
        Tiger i = new Tiger(Integer.MAX_VALUE);
        i.setName(UUID.randomUUID().toString());
        i.setFood(Integer.MAX_VALUE);
        i.setRare(true);
        i.setSurveyDate(Instant.now());
        i.setHealthy(true);

        String expected = "N:" + i.getNumber() + ", Tiger(predator): " + i.getName() +
                "\n\tis rare: " + (i.isRare() ? "yes" : "no") +
                "\n\tfood count: " + i.getFood() +
                "\n\tis healthy: " + (i.isHealthy() ? "yes" : "no") +
                "\n\texamine at: " + Times.format(i.getSurveyDate());

        assertEquals(expected, i.toString());
    }
}
