package com.shimizukenta.secs.ext.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecsMsgListener {
	
	/** 标识某个通讯器
	 * 优先级高于 {@link  com.shimizukenta.secs.ext.annotation.SecsMsgListener.global()}
	 * @return
	 */
	String id() default  "";
	
	

	/** 全局,会添加多所有通讯器
	 * @return
	 */
	boolean global() default true;
	
	 @AliasFor(annotation = Component.class)
	 String value() default "";
	
}
