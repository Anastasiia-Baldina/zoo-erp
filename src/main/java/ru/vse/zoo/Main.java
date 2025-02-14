package ru.vse.zoo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vse.zoo.spring.EditorsConfiguration;
import ru.vse.zoo.spring.ServiceConfiguration;
import ru.vse.zoo.spring.UiConfiguration;

public class Main {

    public static void main(String[] args) {
        var diContext = new AnnotationConfigApplicationContext(
                EditorsConfiguration.class, UiConfiguration.class, ServiceConfiguration.class
        );
        diContext.getBean(ZooErp.class).start();
        diContext.close();
    }
}
