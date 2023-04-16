package com.shimizukenta.context;

import com.shimizukenta.secs.ext.annotation.SecsMsgListener;
import org.springframework.stereotype.Component;

@SecsMsgListener
public class Bean {


    public void test() {

        System.out.println("beanTest");
    }

}
