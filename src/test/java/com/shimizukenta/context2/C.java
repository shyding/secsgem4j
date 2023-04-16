package com.shimizukenta.context2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class C {

    public void test(){
        System.out.println("C say test");
    }
}
