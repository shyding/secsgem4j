package com.shimizukenta.secs.ext.util;

/**
  * @ClassName: ConfigConstants
  * @Description: 配置相关的常量
  * @author dsy
  * @date 2021年12月30日 上午11:14:59
  *
  */
public interface ConfigConstants {
	
	/**
	  * @Fields HSMS_SS_COMMUNICATOR : 单个会话器
	  */
	public static final String HSMS_SS_COMMUNICATOR = "hsmsSsCommunicator";
	
	/**
	  * @Fields HSMS_SS_COMMUNICATORS : 多个会话器
	  */
	public static final String HSMS_SS_COMMUNICATORS = "hsmsSsCommunicators";

	
	
	public static final String TRUE = "true";
	
	public static final String ENABLED = "enabled";
    
	public static final String ADDITIONS_SECS = "additions.secs";

	
	public static final String ADDITIONS_SECS_SECS1_SENDER = ADDITIONS_SECS +".secs1.sender";
	
	public static final String ADDITIONS_SECS_SECS1_RECEIVER= ADDITIONS_SECS +".secs1.receiver";

	public static final String ADDITIONS_SECS_HSMS_SS = ADDITIONS_SECS +".hsms.ss";
	
	public static final String ADDITIONS_SECS_HSMS_GS = ADDITIONS_SECS +".hsms.gs";
	
}
