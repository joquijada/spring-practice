package com.exsoinn.spring.practice.injection.mapOfBeans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.exsoinn.spring.practice.injection.mapOfBeans.service.MyService;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {MyService.class, MyImplOne.class, MyImplTwo.class})
class MapOfBeansTest {
    @Autowired
    private MyService myService;

    @Test
    void beansAreInjectedIntoMapOfBeans() {
        final Map<String, MyInterface> beans = myService.getBeans();
        assertFalse(beans.isEmpty());
        assertEquals(2, beans.size());
        System.out.println("MapOfBeans beans are injected into MapOfBeans" + beans);
    }
}
