package ru.vse.zoo.impl.editor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.animal.Tiger;
import ru.vse.zoo.impl.ui.editor.TigerEditor;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TigerEditorTest {
    @InjectMocks
    private TigerEditor editor;
    @Mock
    private UI ui;

    @Test
    public void should_create_inventory() {
        int inventoryNumber = ZonedDateTime.now().getSecond() + 1;
        String nickname = UUID.randomUUID().toString();
        int food = ZonedDateTime.now().getSecond() + 2;

        when(ui.readString("Input tiger nickname")).thenReturn(nickname);
        when(ui.readInt("Input food count")).thenReturn(food);
        when(ui.readYesOrNo("Is tiger rare")).thenReturn(true);

        var inv = editor.create(inventoryNumber);

        assertNotNull(inv);
        assertEquals(inventoryNumber, inv.getNumber());
        assertEquals(nickname, inv.getName());
        assertEquals(food, inv.getFood());
        assertTrue(inv.isRare());
    }

    @Test
    public void should_update_inventory() {
        var inv = new Tiger(1);
        String nickname = UUID.randomUUID().toString();
        int food = ZonedDateTime.now().getSecond() + 2;
        when(ui.readString("Input tiger nickname")).thenReturn(nickname);
        when(ui.readInt("Input food count")).thenReturn(food);
        when(ui.readYesOrNo("Is tiger rare")).thenReturn(true);
        when(ui.selectOption(anyList()))
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4);

        editor.update(inv);

        assertEquals(nickname, inv.getName());
        assertEquals(food, inv.getFood());
        assertTrue(inv.isRare());
    }
}
