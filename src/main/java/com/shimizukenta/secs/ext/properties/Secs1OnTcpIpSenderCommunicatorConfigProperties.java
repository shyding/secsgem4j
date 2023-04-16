package com.shimizukenta.secs.ext.properties;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import com.shimizukenta.secs.ext.util.ConfigConstants;

@Role( BeanDefinition.ROLE_INFRASTRUCTURE )
@Configuration
@ConditionalOnProperty(prefix = ConfigConstants.ADDITIONS_SECS_SECS1_SENDER , havingValue = ConfigConstants.TRUE )
@ConfigurationProperties(prefix = ConfigConstants.ADDITIONS_SECS_SECS1_SENDER , ignoreUnknownFields = true)
public class Secs1OnTcpIpSenderCommunicatorConfigProperties extends AbstractSecsConfigProperties {

	private float reconnectSeconds = 5.0F;
	
	private String name ;

	public float getReconnectSeconds() {
		return reconnectSeconds;
	}

	public void setReconnectSeconds(float reconnectSeconds) {
		this.reconnectSeconds = reconnectSeconds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
