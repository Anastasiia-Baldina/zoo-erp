package ru.vse.zoo;

import java.util.Collection;
import java.util.List;

public interface EditorFactory {
    <T extends Inventory> Editor<T> getEditor(Class<T> inventoryType);

    Collection<Class<? extends Inventory>> getKnownTypes();
}
