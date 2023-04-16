package com.shimizukenta;

import com.shimizukenta.context.Bean;
import com.shimizukenta.context.Bean2;
import com.shimizukenta.context.Bean3;
import com.shimizukenta.context2.A;
import com.shimizukenta.context2.B;
import com.shimizukenta.context2.C;
import com.shimizukenta.secs.ext.annotation.EnableSecs;
import com.shimizukenta.secs.ext.config.AbstractSecsMsgListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableSecs
/*@Import( {A.class   , Bean3.class})*/
@EnableScheduling
@SpringBootApplication
public class Application {


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        Bean3 contextBean   = context.getBean(Bean3.class ) ;

         contextBean.test();

        Bean2 contextBean2   = context.getBean(Bean2.class ) ;

        contextBean2.test();


        B b= context.getBean(B.class);

        b.test();

        C c= context.getBean(C.class);

        c.test();
    }

}
