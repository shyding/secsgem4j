package com.shimizukenta.context;


import com.shimizukenta.secs.ext.config.AbstractSecsMsgListener;

public class Bean3  extends AbstractSecsMsgListener {

    public String test(){
        System.out.println( "test3");
        return "test3";
    }
}
