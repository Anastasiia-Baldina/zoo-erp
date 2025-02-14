package ru.vse.zoo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vse.zoo.Clinic;
import ru.vse.zoo.EditorFactory;
import ru.vse.zoo.Registry;
import ru.vse.zoo.UI;
import ru.vse.zoo.ZooApi;
import ru.vse.zoo.ZooErp;
import ru.vse.zoo.impl.ZooApiImpl;
import ru.vse.zoo.impl.clinic.ZooClinic;
import ru.vse.zoo.impl.inventory.ZooRegistry;

@Configuration
public class ServiceConfiguration {
    @Autowired
    private EditorFactory editorFactory;
    @Autowired
    private UI ui;

    @Bean
    Registry zooRegistry() {
        return new ZooRegistry();
    }

    @Bean
    Clinic zooClinic() {
        return new ZooClinic(ui);
    }

    @Bean
    ZooApi zooApi(Registry registry, Clinic clinic) {
        return new ZooApiImpl(registry, clinic);
    }

    @Bean
    public ZooErp zooErp(ZooApi zooApi) {
        return new ZooErp(ui, zooApi, editorFactory);
    }
}
