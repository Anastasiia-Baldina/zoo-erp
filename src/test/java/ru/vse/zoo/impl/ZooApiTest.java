package ru.vse.zoo.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.vse.zoo.Inventory;
import ru.vse.zoo.UI;
import ru.vse.zoo.ZooApi;
import ru.vse.zoo.impl.inventory.base.Animal;
import ru.vse.zoo.impl.inventory.base.Thing;
import ru.vse.zoo.impl.inventory.person.Employee;
import ru.vse.zoo.impl.inventory.thing.Computer;
import ru.vse.zoo.spring.EditorsConfiguration;
import ru.vse.zoo.spring.ServiceConfiguration;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@SpringJUnitConfig(
        classes = {
                EditorsConfiguration.class, ZooApiTest.MockUI.class, ServiceConfiguration.class
        },
        loader = AnnotationConfigContextLoader.class)
public class ZooApiTest {
    @Autowired
    private ZooApi zooApi;

    @BeforeEach
    public void beforeEach() {
        zooApi.load();
    }

    @Test
    public void validate_list_animals() {
        Set<Integer> expected = Set.of(1, 3, 4, 5, 6, 7, 8);
        List<? extends Animal> animals = zooApi.listAnimals();

        assertNotNull(animals);
        var numbers = animals.stream()
                .map(Inventory::getNumber)
                .toList();
        assertEquals(expected.size(), numbers.size());
        assertTrue(numbers.containsAll(expected));
    }

    @Test
    public void validate_list_animals_of_contact_zoo() {
        Set<Integer> expected = Set.of(1, 4);
        List<? extends Animal> animals = zooApi.listContactableAnimals();

        assertNotNull(animals);
        var numbers = animals.stream()
                .map(Inventory::getNumber)
                .toList();
        assertEquals(expected.size(), numbers.size());
        assertTrue(numbers.containsAll(expected));
    }

    @Test
    public void validate_list_things() {
        Set<Integer> expected = Set.of(2, 9);
        List<? extends Thing> things = zooApi.listThings();

        assertNotNull(things);
        var numbers = things.stream()
                .map(Inventory::getNumber)
                .toList();
        assertEquals(expected.size(), numbers.size());
        assertTrue(numbers.containsAll(expected));
    }

    @Test
    public void validate_search_and_remove() {
        Computer comp = new Computer(1001);
        assertTrue(zooApi.accept(comp));
        var res = zooApi.search(comp.getNumber());
        assertNotNull(res);
        assertEquals(comp.getNumber(), res.getNumber());
        zooApi.remove(comp.getNumber());
        assertNull(zooApi.search(comp.getNumber()));
    }

    @Test
    public void validate_list_food_requirements() {
        var foodMap = zooApi.getFoodRequirements();

        assertEquals(5, foodMap.size());
        assertEquals(5, foodMap.get("Monkey"));
        assertEquals(2, foodMap.get("Rabbit"));
        assertEquals(9, foodMap.get("Wolf"));
        assertEquals(15, foodMap.get("Tiger"));
        assertEquals(3, foodMap.get("Employee"));
    }

    @Test
    public void validate_list_employees() {
        Set<Integer> expected = Set.of(10, 11, 12);
        List<? extends Employee> things = zooApi.listEmployee();

        assertNotNull(things);
        var numbers = things.stream()
                .map(Inventory::getNumber)
                .toList();
        assertEquals(expected.size(), numbers.size());
        assertTrue(numbers.containsAll(expected));
    }

    public static class MockUI {
        @Bean
        public UI ui() {
            return mock(UI.class);
        }
    }
}
