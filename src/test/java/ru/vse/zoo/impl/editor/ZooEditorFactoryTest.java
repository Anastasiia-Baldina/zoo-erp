package ru.vse.zoo.impl.editor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vse.zoo.Editor;
import ru.vse.zoo.Inventory;
import ru.vse.zoo.impl.ui.editor.ZooEditorFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ZooEditorFactoryTest {
    private ZooEditorFactory editorFactory;
    @Mock
    private AEditor aEditor;
    @Mock
    private BEditor bEditor;

    @BeforeEach
    public void beforeEach() {
        when(aEditor.getType()).thenReturn(A.class);
        when(bEditor.getType()).thenReturn(B.class);
        editorFactory = new ZooEditorFactory(List.of(aEditor, bEditor));
    }

    @Test
    public void should_return_editor() {
        var resA = editorFactory.getEditor(A.class);
        assertTrue(resA instanceof AEditor);
        var resB = editorFactory.getEditor(B.class);
        assertTrue(resB instanceof BEditor);
    }

    @Test
    public void should_return_known_types() {
        var res = editorFactory.getKnownTypes();
        assertEquals(2, res.size());
        assertTrue(res.containsAll(List.of(A.class, B.class)));
    }

    private interface A extends Inventory {
    }

    private interface B extends Inventory {
    }

    private interface AEditor extends Editor<A> {
    }

    private interface BEditor extends Editor<B> {

    }
}
