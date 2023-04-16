package com.shimizukenta.secs.ext.properties;




import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import com.shimizukenta.secs.ext.util.ConfigConstants;
import com.shimizukenta.secs.hsms.HsmsConnectionMode;



@Role( BeanDefinition.ROLE_INFRASTRUCTURE )
@Configuration
@ConditionalOnProperty(prefix = ConfigConstants.ADDITIONS_SECS_HSMS_SS ,name = ConfigConstants.ENABLED  ,  havingValue =  ConfigConstants.TRUE  )
@ConfigurationProperties(prefix = ConfigConstants.ADDITIONS_SECS_HSMS_SS , ignoreUnknownFields = true)
public class HsmsSsCommunicatorConfigurationproperties extends AbstractSecsConfigProperties  implements Serializable {



  
	
    /**
	 * @Fields 序列化
	 */
	private static final long serialVersionUID = 1L;

	

	
	/**
     * 请求根地址,如果请求消息{@link HttpRequestMessage#getUrl()}中没有指定http://则使用此配置
     */
	/**
	 * 协议:PASSIVE, 	ACTIVE
	 */
	
	private String connectionMode = HsmsConnectionMode.PASSIVE.name();
	
	
	private String logSubjectHeader = "";

  
	private int sessionId = 0 ;

	/**
	 * 是否是设备
	 */
	
    private Boolean isEquip = false ;
	
	/**
	 * 打印详细的日志
	 */
	
	private Boolean logDetail = false;
	

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


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}





	public String getConnectionMode() {
		return connectionMode;
	}


	public void setConnectionMode(String connectionMode) {
		this.connectionMode = connectionMode;
	}


	public String getLogSubjectHeader() {
		return logSubjectHeader;
	}


	public void setLogSubjectHeader(String logSubjectHeader) {
		this.logSubjectHeader = logSubjectHeader;
	}


	public Boolean getIsEquip() {
		return isEquip;
	}


	public void setIsEquip(Boolean isEquip) {
		this.isEquip = isEquip;
	}


	public Boolean getLogDetail() {
		return logDetail;
	}


	public void setLogDetail(Boolean logDetail) {
		this.logDetail = logDetail;
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


	public int getSessionId() {
		return sessionId;
	}


	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}


	
	
	

}
