package com.exsoinn.spring.practice.injection.injectionAmbiguity.service;

import com.exsoinn.spring.practice.injection.injectionAmbiguity.repository.MyRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("myServiceInjectionAmbiguity")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MyService {
    /*
     * If I name my instance variable like 'MyRepo repository', it gives below error:
     * Could not autowire. There is more than one bean of 'MyRepo' type.
     * Beans:
     * myRepoOne   (MyRepoOne.java) myRepoTwo   (MyRepoTwo.java)
     * To solve have two options:
     * 1. Rename variable to match the bean (aka Spring component) name, E.g.
     *    MyRepo myRepoOne;
     *    [REF|https://stackoverflow.com/questions/60871824/how-to-use-spring-objectprovider-with-more-than-one-bean-definition|"when injecting a bean that is not specified by @Qualifier("NAME"), Spring takes the injected variable as the name, if that don't exists or is not unique"]
     * 2. Use '@Qualifier("<bean name>")', E.g.,
     *    @Qualifier("myRepoOne")
     *    MyRepo repository
     */
    MyRepo myRepoOne;
}
