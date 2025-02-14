package ru.vse.zoo.impl.editor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.inventory.animal.Monkey;
import ru.vse.zoo.impl.ui.editor.MonkeyEditor;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MonkeyEditorTest {
    @InjectMocks
    private MonkeyEditor editor;
    @Mock
    private UI ui;

    @Test
    public void should_create_inventory() {
        int inventoryNumber = ZonedDateTime.now().getSecond() + 10;
        String nickname = UUID.randomUUID().toString();
        int food = ZonedDateTime.now().getSecond() + 20;

        when(ui.readString("Input monkey nickname")).thenReturn(nickname);
        when(ui.readInt("Input food count")).thenReturn(food);
        when(ui.readYesOrNo("Is monkey friendly")).thenReturn(true);
        when(ui.readYesOrNo("Is monkey smart")).thenReturn(true);

        var inv = editor.create(inventoryNumber);

        assertNotNull(inv);
        assertEquals(inventoryNumber, inv.getNumber());
        assertEquals(nickname, inv.getName());
        assertEquals(food, inv.getFood());
        assertTrue(inv.isSmart());
        assertTrue(inv.isFriendly());
    }

    @Test
    public void should_update_inventory() {
        var inv = new Monkey(1);
        String nickname = UUID.randomUUID().toString();
        int food = ZonedDateTime.now().getSecond() + 20;

        when(ui.readString("Input monkey nickname")).thenReturn(nickname);
        when(ui.readInt("Input food count")).thenReturn(food);
        when(ui.readYesOrNo("Is monkey friendly")).thenReturn(true);
        when(ui.readYesOrNo("Is monkey smart")).thenReturn(true);
        when(ui.selectOption(anyList()))
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4)
                .thenReturn(5);

        editor.update(inv);
        assertEquals(nickname, inv.getName());
        assertEquals(food, inv.getFood());
        assertTrue(inv.isSmart());
        assertTrue(inv.isFriendly());
    }
}