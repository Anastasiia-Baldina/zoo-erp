package ru.vse.zoo.impl.editor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.person.Employee;
import ru.vse.zoo.impl.ui.editor.EmployeeEditor;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonalEditorTest {
    @InjectMocks
    private EmployeeEditor editor;
    @Mock
    private UI ui;

    @Test
    public void should_create_inventory() {
        int inventoryNumber = ZonedDateTime.now().getSecond() + 10;
        String name = UUID.randomUUID().toString();
        String position = UUID.randomUUID().toString();
        int food = ZonedDateTime.now().getHour();

        when(ui.readString("Input personal name")).thenReturn(name);
        when(ui.readString("Input personal position")).thenReturn(position);
        when(ui.readInt("Input food count")).thenReturn(food);

        var inv = editor.create(inventoryNumber);

        assertNotNull(inv);
        assertEquals(inventoryNumber, inv.getNumber());
        assertEquals(name, inv.getName());
        assertEquals(position, inv.getPosition());
        assertEquals(food, inv.getFood());
    }

    @Test
    public void should_update_inventory() {
        var inv = new Employee(1);

        String name = UUID.randomUUID().toString();
        String position = UUID.randomUUID().toString();
        int food = ZonedDateTime.now().getHour();

        when(ui.readString("Input personal name")).thenReturn(name);
        when(ui.readString("Input personal position")).thenReturn(position);
        when(ui.readInt("Input food count")).thenReturn(food);
        when(ui.selectOption(anyList()))
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4);

        editor.update(inv);

        assertEquals(name, inv.getName());
        assertEquals(position, inv.getPosition());
        assertEquals(food, inv.getFood());
    }
}