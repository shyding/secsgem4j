/*
 * package com.shimizukenta.secs.ext.properties;
 * 
 * 
 * 
 * 
 * import java.io.Serializable;
 * 
 * import com.shimizukenta.secs.hsms.HsmsConnectionMode;
 * 
 * import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data;
 * import lombok.NoArgsConstructor;
 * 
 * 
 *//**
	 * hsms通讯参数
	 * 
	 * @ClassName: HsmsProps
	 * @Description: hsms 通讯
	 * @author dsy
	 * @date 2021年6月23日
	 *
	 */
/*
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Builder
 * 
 * @Data public class HsmsProps implements Serializable {
 * 
 * 
 * 
 * 
 * 
 *//**
	 * @Fields 序列化
	 */
/*
 * private static final long serialVersionUID = 1L;
 * 
 * 
 *//**
	 * 主机Ip
	 */
/*
 * @Builder.Default private String host="0.0.0.0";
 * 
 * 
 *//**
	 * 端口号
	 */
/*
 * @Builder.Default private int port = 10068;
 * 
 *//**
	 * 请求根地址,如果请求消息{@link HttpRequestMessage#getUrl()}中没有指定http://则使用此配置
	 */
/*
*//**
	 * 协议:PASSIVE, ACTIVE
	 */
/*
 * @Builder.Default private String protocol = HsmsConnectionMode.PASSIVE.name();
 * 
 * @Builder.Default private String logSubjectHeader = "";
 * 
 * 
 * 
 *//**
	 * 是否是设备
	 */
/*
 * @Builder.Default private Boolean isEquip = false ;
 * 
 *//**
	 * 打印详细的日志
	 */
/*
 * @Builder.Default private Boolean logDetail = false;
 * 
 * 
 *//**
	 * 循环侦测时间: 请求超时时间
	 */
/*
 * @Builder.Default private float linktest = 6.0F;
 * 
 * 
 *//**
	 * t3
	 */
/*
 * @Builder.Default private Float t3 = 45.0F;
 * 
 * @Builder.Default private Float t5 = 10.0F;
 * 
 *//**
	 * t6
	 */
/*
 * @Builder.Default private Float t6 = 5.0F;
 * 
 * @Builder.Default private Float t7 = 10F;
 * 
 * 
 *//**
	 * t8
	 *//*
		 * @Builder.Default private Float t8 = 5.0F;
		 * 
		 * @Builder.Default private Float rebindIfPassive = 6.0F;
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 */