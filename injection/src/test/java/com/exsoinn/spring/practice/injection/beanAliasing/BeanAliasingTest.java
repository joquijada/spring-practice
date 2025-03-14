package com.exsoinn.spring.practice.injection.beanAliasing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.exsoinn.spring.practice.injection.beanAliasing.config.BeanAliasingConfig;
import com.exsoinn.spring.practice.injection.beanAliasing.service.MyService;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Play around with how Spring bean aliasing works, in case I want to associate the sam eBean instance but
 * with different name handles. Use case on 03/13/2025 in Solventum (my last two weeks, last day 03/31/2025)
 * is to associate the same GriffinServiceXyz with different message types from Orchestration for purposes of
 * maintaining D2b and OPCDI pipelines separate.
 * Bean aliasing only works when Bean is added to Spring context via @Bean annotation. I tried @Component
 * but alas that feature is not supported yet, [REF|https://medium.com/@haominglfs/the-bean-alias-of-springboot-4833834d948f|"the @Component annotation can only configure one name, while the @Bean annotation can configure aliases"],
 * [REF|https://stackoverflow.com/questions/39916574/names-and-aliases-using-bean-and-component-in-spring|"This is still a feature request https://jira.spring.io/browse/SPR-6736"]
 */
@SpringBootTest
@ContextConfiguration(classes = {MyService.class, BeanAliasingConfig.class})
public class BeanAliasingTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    MyService myService;

    @Test
    void beansAreInjectedIntoMapOfBeans() {
        final Map<String, MyInterface> beans = myService.getBeans();
        assertFalse(beans.isEmpty());
        assertEquals(2, beans.size());
    }

    @Test
    void canGetBeanByAlias() {
        MyInterface myClassOneBean = context.getBean("myClassOneAliasOne", MyInterface.class);
        assertNotNull(myClassOneBean);
    }
}
