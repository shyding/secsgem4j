package com.shimizukenta.secs.ext.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.shimizukenta.secs.SecsCommunicator;
import com.shimizukenta.secs.ext.properties.SecsCommunicatorConfigProperties;
import com.shimizukenta.secs.ext.util.ConfigConstants;
import com.shimizukenta.secs.ext.util.SecsUtils;
import com.shimizukenta.secs.hsmsgs.HsmsGsCommunicator;
import com.shimizukenta.secs.hsmsgs.HsmsGsCommunicatorConfig;
import com.shimizukenta.secs.hsmsss.HsmsSsCommunicator;
import com.shimizukenta.secs.hsmsss.HsmsSsCommunicatorConfig;
import com.shimizukenta.secs.secs1ontcpip.Secs1OnTcpIpCommunicator;
import com.shimizukenta.secs.secs1ontcpip.Secs1OnTcpIpCommunicatorConfig;
import com.shimizukenta.secs.secs1ontcpip.Secs1OnTcpIpReceiverCommunicator;
import com.shimizukenta.secs.secs1ontcpip.Secs1OnTcpIpReceiverCommunicatorConfig;


@Configuration(proxyBeanMethods = true)
@Import({ SecsConfigurationRegistrar.class })
@EnableConfigurationProperties(SecsCommunicatorConfigProperties.class)
public class SecsAutoConfiguration  {

//	private final Logger logger = LoggerFactory.getLogger(SecsAutoConfiguration.class);


	@Autowired(required = false)
	private List<AbstractSecsMsgListener> list;


	@ConditionalOnBean( HsmsSsCommunicatorConfig.class)
	@Bean(ConfigConstants.HSMS_SS_COMMUNICATOR)
	public SecsCommunicator hsmsSsCommunicator(@Autowired HsmsSsCommunicatorConfig hsmsSsCommunicatorConfig) {


		HsmsSsCommunicator comm = HsmsSsCommunicator.newInstance( hsmsSsCommunicatorConfig);
		return (SecsCommunicator) SecsUtils.wrappSecsCommunicator(comm , list);
		  
		 }

	

	
	@ConditionalOnBean( HsmsGsCommunicatorConfig.class)
	@Bean
	public HsmsGsCommunicator hsmsGsCommunicator(@Autowired HsmsGsCommunicatorConfig hsmsGsCommunicatorConfig) {


		HsmsGsCommunicator comm = HsmsGsCommunicator.newInstance(hsmsGsCommunicatorConfig);
		return (HsmsGsCommunicator) SecsUtils.wrappSecsCommunicator(comm , list);
		  
		 }

	
	
	
	@ConditionalOnBean( Secs1OnTcpIpCommunicatorConfig.class)
	@Bean
	public SecsCommunicator secs1OnTcpIpCommunicator(@Autowired Secs1OnTcpIpCommunicatorConfig secs1OnTcpIpCommunicatorConfig) {

		Secs1OnTcpIpCommunicator comm = Secs1OnTcpIpCommunicator.newInstance( secs1OnTcpIpCommunicatorConfig) ;
		return  (SecsCommunicator) SecsUtils.wrappSecsCommunicator(comm , list);
		  
		 }
	
	
	@ConditionalOnBean( Secs1OnTcpIpReceiverCommunicatorConfig.class)
	@Bean
	public SecsCommunicator hsmsGsCommunicator(@Autowired Secs1OnTcpIpReceiverCommunicatorConfig secs1OnTcpIpReceiverCommunicatorConfig) {


		 Secs1OnTcpIpReceiverCommunicator comm = Secs1OnTcpIpReceiverCommunicator.newInstance(secs1OnTcpIpReceiverCommunicatorConfig);
		return (SecsCommunicator) SecsUtils.wrappSecsCommunicator(comm , list);
		  
		 }
	
	
	
	
	
	
	
	
	
	
	







}
