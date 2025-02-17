package com.exsoinn.spring.practice.lifecycle;

import org.springframework.stereotype.Service;

@Service
public class MyService extends AbstractService {

    public MyService() {
        super();
    }
}
