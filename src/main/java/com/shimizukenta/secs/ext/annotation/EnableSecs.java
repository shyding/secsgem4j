package com.shimizukenta.secs.ext.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.shimizukenta.secs.ext.config.AbstractSecsMsgListener;
import com.shimizukenta.secs.ext.config.SecsConfigurationRegistrar;
import com.shimizukenta.secs.ext.properties.HsmsGsCommunicatorConfigurationproperties;
import com.shimizukenta.secs.ext.properties.HsmsSsCommunicatorConfigurationproperties;
import com.shimizukenta.secs.ext.properties.SecsCommunicatorConfigProperties;
import com.shimizukenta.secs.ext.util.SpringUtils;

/** secs handler 处理类引入
 * @author dsy
 *
 */
@ComponentScan( includeFilters = {  @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {AbstractSecsMsgListener.class} )})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {SpringUtils.class ,  SecsConfigurationRegistrar.class , HsmsSsCommunicatorConfigurationproperties.class ,HsmsGsCommunicatorConfigurationproperties.class , SecsCommunicatorConfigProperties.class  } )
public @interface EnableSecs {

}
