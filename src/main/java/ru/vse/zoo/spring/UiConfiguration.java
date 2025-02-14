package ru.vse.zoo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vse.zoo.impl.ui.ConsoleUI;

@Configuration
public class UiConfiguration {
    @Bean
    public ConsoleUI consoleUI() {
        return new ConsoleUI();
    }
}
