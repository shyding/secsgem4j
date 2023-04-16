package com.shimizukenta.context;

import com.shimizukenta.secs.ext.annotation.SecsMsgListener;
import com.shimizukenta.secs.ext.config.AbstractSecsMsgListener;


public class Bean2  extends AbstractSecsMsgListener {


    public void test() {

        System.out.println("beanTest22");
    }

}
