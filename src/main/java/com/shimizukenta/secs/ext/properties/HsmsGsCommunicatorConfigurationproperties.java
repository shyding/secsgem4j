package com.shimizukenta.secs.ext.properties;


import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import com.shimizukenta.secs.ext.util.ConfigConstants;
import com.shimizukenta.secs.hsms.HsmsConnectionMode;


/** hsms通讯参数
 * @ClassName: HsmsProps
 * @Description: hsms 通讯
 * @author dsy
 * @date 2021年6月23日
 *
 */
@Role( BeanDefinition.ROLE_INFRASTRUCTURE )
@ConditionalOnProperty(prefix = ConfigConstants.ADDITIONS_SECS_HSMS_GS  , name = ConfigConstants.ENABLED  , havingValue = ConfigConstants.TRUE  )
@Configuration
@ConfigurationProperties(prefix = ConfigConstants.ADDITIONS_SECS_HSMS_GS , ignoreUnknownFields = true)
public class HsmsGsCommunicatorConfigurationproperties extends AbstractSecsConfigProperties implements Serializable {



  



	/**
	 * @Fields 序列化
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 回话id
	 */
	private Set<Integer> sessionIds ;

	
	/**
     * 请求根地址,如果请求消息{@link HttpRequestMessage#getUrl()}中没有指定http://则使用此配置
     */
	/**
	 * 协议:PASSIVE, 	ACTIVE
	 */
	
	private String connectionMode = HsmsConnectionMode.PASSIVE.name();
	
	
	

  
	
	/**
	 * 打印详细的日志
	 */
	
	private boolean logDetail = false;
	
	
	private boolean isTrySelectRequest = false;
	
	
	
	

	private float retrySelectRequestTimeout =5.0F ;
	

    /**
     * 循环侦测时间: 请求超时时间
     */
	
    private float linktest = 6.0F;
	

	
	private Float t5 = 10.0F;
	
	/**
	 * t6
	 */
	
	private Float t6 = 5.0F;
	
	
	private Float t7 = 10F;
	


  
	/**
	 * t8
	 */
	
	private Float t8 = 5.0F;
	
	
	private Float rebindIfPassive = 6.0F;


	public Set<Integer> getSessionIds() {
		return sessionIds;
	}


	public void setSessionIds(Set<Integer> sessionIds) {
		this.sessionIds = sessionIds;
	}



	public String getConnectionMode() {
		
		return connectionMode;
	}


	public void setConnectionMode(String connectionMode) {
		this.connectionMode = connectionMode;
	}






	public boolean isLogDetail() {
		return logDetail;
	}


	public void setLogDetail(boolean logDetail) {
		this.logDetail = logDetail;
	}


	public boolean isTrySelectRequest() {
		return isTrySelectRequest;
	}


	public void setTrySelectRequest(boolean isTrySelectRequest) {
		this.isTrySelectRequest = isTrySelectRequest;
	}





	public float getRetrySelectRequestTimeout() {
		return retrySelectRequestTimeout;
	}


	public void setRetrySelectRequestTimeout(float retrySelectRequestTimeout) {
		this.retrySelectRequestTimeout = retrySelectRequestTimeout;
	}


	public float getLinktest() {
		return linktest;
	}


	public void setLinktest(float linktest) {
		this.linktest = linktest;
	}




	public Float getT5() {
		return t5;
	}


	public void setT5(Float t5) {
		this.t5 = t5;
	}


	public Float getT6() {
		return t6;
	}


	public void setT6(Float t6) {
		this.t6 = t6;
	}


	public Float getT7() {
		return t7;
	}


	public void setT7(Float t7) {
		this.t7 = t7;
	}


	public Float getT8() {
		return t8;
	}


	public void setT8(Float t8) {
		this.t8 = t8;
	}


	public Float getRebindIfPassive() {
		return rebindIfPassive;
	}


	public void setRebindIfPassive(Float rebindIfPassive) {
		this.rebindIfPassive = rebindIfPassive;
	}


	public boolean  isPassive() {
		
		return  HsmsConnectionMode.PASSIVE.name().equals(connectionMode);
	}
	
	
	
	
	

}
