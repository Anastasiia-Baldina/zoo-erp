package ru.vse.zoo.impl.editor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.thing.Table;
import ru.vse.zoo.impl.ui.editor.TableEditor;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TableEditorTest {
    @InjectMocks
    private TableEditor editor;
    @Mock
    private UI ui;

    @Test
    public void should_create_inventory() {
        int inventoryNumber = ZonedDateTime.now().getSecond() + 10;
        String name = UUID.randomUUID().toString();
        int width = ZonedDateTime.now().getHour() + 20;
        int height = ZonedDateTime.now().getHour() + 30;
        int length = ZonedDateTime.now().getHour() + 40;

        when(ui.readString("Input table name")).thenReturn(name);
        when(ui.readInt("Input table width")).thenReturn(width);
        when(ui.readInt("Input table height")).thenReturn(height);
        when(ui.readInt("Input table length")).thenReturn(length);

        var inv = editor.create(inventoryNumber);

        assertNotNull(inv);
        assertEquals(inventoryNumber, inv.getNumber());
        assertEquals(name, inv.getName());
        assertEquals(width, inv.getWidth());
        assertEquals(length, inv.getLength());
        assertEquals(height, inv.getHeight());
    }

    @Test
    public void should_update_inventory() {
        var inv = new Table(1);
        String name = UUID.randomUUID().toString();
        int width = ZonedDateTime.now().getHour() + 20;
        int height = ZonedDateTime.now().getHour() + 30;
        int length = ZonedDateTime.now().getHour() + 40;
        when(ui.readString("Input table name")).thenReturn(name);
        when(ui.readInt("Input table width")).thenReturn(width);
        when(ui.readInt("Input table height")).thenReturn(height);
        when(ui.readInt("Input table length")).thenReturn(length);
        when(ui.selectOption(anyList()))
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4)
                .thenReturn(5);

        editor.update(inv);

        assertEquals(name, inv.getName());
        assertEquals(width, inv.getWidth());
        assertEquals(length, inv.getLength());
        assertEquals(height, inv.getHeight());
    }
}
