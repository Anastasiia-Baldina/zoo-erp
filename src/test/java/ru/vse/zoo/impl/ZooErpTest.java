package ru.vse.zoo.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.vse.zoo.UI;
import ru.vse.zoo.ZooErp;
import ru.vse.zoo.spring.EditorsConfiguration;
import ru.vse.zoo.spring.ServiceConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ZooErpTest {
    private static final UI ui = mock(UI.class);
    private AnnotationConfigApplicationContext diContext;

    @BeforeEach
    public void beforeEach() {
        diContext = new AnnotationConfigApplicationContext(
                EditorsConfiguration.class, MockUI.class, ServiceConfiguration.class
        );
        ZooErp zooErp = diContext.getBean(ZooErp.class);
        zooErp.start();
    }

    @AfterEach
    public void afterEach() {
        diContext.close();
        reset(ui);
    }

    @Test
    public void validate_menu() {
        ArgumentCaptor<List<String>> menuCptr = ArgumentCaptor.forClass(List.class);

        verify(ui).selectOption(menuCptr.capture());
        var res = menuCptr.getValue();
        assertEquals(MENU.size(), res.size());
        for (int i = 0; i < MENU.size(); i++) {
            assertEquals(MENU.get(i), res.get(i));
        }
    }

    public static class MockUI {
        @Bean
        public UI ui() {
            return ui;
        }
    }

    private static final List<String> MENU = List.of(
            "Accept animals",
            "Print animals",
            "Print friendly & healthy animals (for contact zoo)",
            "Examine animal in clinic",
            "Print food requirements",
            "Accept thing",
            "Print things",
            "Search inventory",
            "Edit inventory",
            "Remove inventory",
            "Accept employee",
            "Print employees",
            "Exit"
    );
}
