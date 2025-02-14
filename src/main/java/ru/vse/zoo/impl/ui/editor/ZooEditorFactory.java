package ru.vse.zoo.impl.ui.editor;

import ru.vse.zoo.Editor;
import ru.vse.zoo.EditorFactory;
import ru.vse.zoo.Inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZooEditorFactory implements EditorFactory {
    private final Map<Class<? extends Inventory>, Editor<? extends Inventory>> editors;
    private final List<Class<? extends Inventory>> types;

    public ZooEditorFactory(List<Editor<? extends Inventory>> editors) {
        this.editors = new HashMap<>(editors.size());
        var types = new ArrayList<Class<? extends Inventory>>(editors.size());
        for (var editor : editors) {
            var editorType = editor.getType();
            this.editors.put(editorType, editor);
            types.add(editorType);
        }
        this.types = Collections.unmodifiableList(types);
    }

    /**
     * Получить редактор для инвентаризационного средства
     * @param inventoryType - тип инвентаризационного средства
     * @param <T> тип инвентаризационного средства
     * @return редактор
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Inventory> Editor<T> getEditor(Class<T> inventoryType) {
        var editor = editors.get(inventoryType);
        if (editor == null) {
            throw new IllegalArgumentException("Unknown inventory type " + inventoryType.getSimpleName());
        }
        return (Editor<T>) editor;
    }

    /**
     * Возвращает типы инвентаризационных средств, доступных в UI
     * @return
     */
    @Override
    public Collection<Class<? extends Inventory>> getKnownTypes() {
        return types;
    }
}
