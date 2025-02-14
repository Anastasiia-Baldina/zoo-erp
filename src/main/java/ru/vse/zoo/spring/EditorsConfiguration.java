package ru.vse.zoo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vse.zoo.Editor;
import ru.vse.zoo.EditorFactory;
import ru.vse.zoo.Inventory;
import ru.vse.zoo.UI;
import ru.vse.zoo.impl.ui.editor.ComputerEditor;
import ru.vse.zoo.impl.ui.editor.EmployeeEditor;
import ru.vse.zoo.impl.ui.editor.MonkeyEditor;
import ru.vse.zoo.impl.ui.editor.RabbitEditor;
import ru.vse.zoo.impl.ui.editor.TableEditor;
import ru.vse.zoo.impl.ui.editor.TigerEditor;
import ru.vse.zoo.impl.ui.editor.WolfEditor;
import ru.vse.zoo.impl.ui.editor.ZooEditorFactory;

import java.util.List;

@Configuration
public class EditorsConfiguration {
    @Autowired
    private UI ui;

    @Bean
    EmployeeEditor employeeEditor() {
        return new EmployeeEditor(ui);
    }

    @Bean
    ComputerEditor computerEditor() {
        return new ComputerEditor(ui);
    }

    @Bean
    TableEditor tableEditor() {
        return new TableEditor(ui);
    }

    @Bean
    MonkeyEditor monkeyEditor() {
        return new MonkeyEditor(ui);
    }

    @Bean
    RabbitEditor rabbitEditor() {
        return new RabbitEditor(ui);
    }

    @Bean
    TigerEditor tigerEditor() {
        return new TigerEditor(ui);
    }

    @Bean
    WolfEditor wolfEditor() {
        return new WolfEditor(ui);
    }

    @Bean
    public EditorFactory editorFactory(@Autowired List<Editor<? extends Inventory>> editors) {
        return new ZooEditorFactory(editors);
    }
}
