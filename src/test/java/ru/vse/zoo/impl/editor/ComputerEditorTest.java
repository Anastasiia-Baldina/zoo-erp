package ru.vse.zoo.impl.editor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.thing.Computer;
import ru.vse.zoo.impl.ui.editor.ComputerEditor;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComputerEditorTest {
    @InjectMocks
    private ComputerEditor editor;
    @Mock
    private UI ui;

    @Test
    public void should_create_inventory() {
        int inventoryNumber = ZonedDateTime.now().getSecond() + 10;
        String name = UUID.randomUUID().toString();
        String processor = UUID.randomUUID().toString();
        int ramSize = ZonedDateTime.now().getHour();

        when(ui.readString("Input computer name")).thenReturn(name);
        when(ui.readString("Input processor type")).thenReturn(processor);
        when(ui.readInt("Input RAM size (Gb)")).thenReturn(ramSize);

        var inv = editor.create(inventoryNumber);

        assertNotNull(inv);
        assertEquals(inventoryNumber, inv.getNumber());
        assertEquals(name, inv.getName());
        assertEquals(processor, inv.getProcessor());
        assertEquals(ramSize, inv.getRamMemoryGb());
    }

    @Test
    public void should_update_inventory() {
        Computer inv = new Computer(1);

        String name = UUID.randomUUID().toString();
        String processor = UUID.randomUUID().toString();
        int ramSize = ZonedDateTime.now().getHour();

        when(ui.readString("Input computer name")).thenReturn(name);
        when(ui.readString("Input processor type")).thenReturn(processor);
        when(ui.readInt("Input RAM size (Gb)")).thenReturn(ramSize);
        when(ui.selectOption(anyList()))
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4);

        editor.update(inv);
        assertEquals(name, inv.getName());
        assertEquals(processor, inv.getProcessor());
        assertEquals(ramSize, inv.getRamMemoryGb());
    }
}
