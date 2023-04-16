package com.shimizukenta.secs.ext.properties;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import com.shimizukenta.secs.ext.util.ConfigConstants;
import com.shimizukenta.secs.gem.ClockType;

@Role( BeanDefinition.ROLE_INFRASTRUCTURE )
@ConditionalOnProperty(  prefix = ConfigConstants.ADDITIONS_SECS_SECS1_RECEIVER , havingValue = ConfigConstants.TRUE  )
@Configuration
@ConfigurationProperties(prefix = ConfigConstants.ADDITIONS_SECS_SECS1_RECEIVER , ignoreUnknownFields = true)
public class Secs1OnTcpIpReceiverCommunicatorConfigProperties extends AbstractSecsConfigProperties {

	private float rebindSeconds = 5.0F ;

	private String clockType = ClockType.A16.name()  ;
	
	private String name ;
	
	public float getRebindSeconds() {
		return rebindSeconds;
	}

	public void setRebindSeconds(float rebindSeconds) {
		this.rebindSeconds = rebindSeconds;
	}

	public String getClockType() {
		return clockType;
	}

	public void setClockType(String clockType) {
		this.clockType = clockType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
