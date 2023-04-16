package com.shimizukenta.secs.ext.properties;




import java.net.InetSocketAddress;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.shimizukenta.secs.ext.util.ConfigConstants;
import com.shimizukenta.secs.gem.ClockType;
import com.shimizukenta.secs.hsms.HsmsConnectionMode;
import com.shimizukenta.secs.hsmsgs.HsmsGsCommunicatorConfig;
import com.shimizukenta.secs.hsmsss.HsmsSsCommunicatorConfig;
import com.shimizukenta.secs.secs1ontcpip.Secs1OnTcpIpCommunicatorConfig;
import com.shimizukenta.secs.secs1ontcpip.Secs1OnTcpIpReceiverCommunicatorConfig;




/**
 * @author shyding
 *
 */
@Component
@ConfigurationProperties(ignoreInvalidFields = true, prefix = ConfigConstants.ADDITIONS_SECS)
public class SecsCommunicatorConfigProperties  {





	@ConditionalOnBean(HsmsGsCommunicatorConfigurationproperties.class)
	@Bean
	public HsmsGsCommunicatorConfig hsmsGsCommunicatorConfig( @Autowired HsmsGsCommunicatorConfigurationproperties hsmsGsCommunicatorConfigurationproperties) {
		

		HsmsGsCommunicatorConfig config = new HsmsGsCommunicatorConfig();
		
		Set<Integer> sessionIds = hsmsGsCommunicatorConfigurationproperties.getSessionIds();
		if( CollectionUtils.isNotEmpty(sessionIds)) {
			sessionIds.stream().forEach( id ->{
				config.addSessionId(id);
			});
		}
		config.socketAddress( new InetSocketAddress( hsmsGsCommunicatorConfigurationproperties.getHost() , hsmsGsCommunicatorConfigurationproperties.getPort() ));
		config.isEquip( hsmsGsCommunicatorConfigurationproperties.isEquip() );
		
		config.connectionMode( HsmsConnectionMode.valueOf( hsmsGsCommunicatorConfigurationproperties.getConnectionMode()) );
		config.isTrySelectRequest( hsmsGsCommunicatorConfigurationproperties.isTrySelectRequest() );
		config.retrySelectRequestTimeout( hsmsGsCommunicatorConfigurationproperties.getRetrySelectRequestTimeout());
		config.timeout().t3( hsmsGsCommunicatorConfigurationproperties.getT3());
		config.timeout().t5( hsmsGsCommunicatorConfigurationproperties.getT5());
		config.timeout().t6(hsmsGsCommunicatorConfigurationproperties.getT6());
		config.timeout().t8( hsmsGsCommunicatorConfigurationproperties.getT8() );
		config.logSubjectHeader( hsmsGsCommunicatorConfigurationproperties.getLogSubjectHeader());
		config.notLinktest();
		config.linktest( hsmsGsCommunicatorConfigurationproperties.getLinktest() );
		if( hsmsGsCommunicatorConfigurationproperties.isPassive()) {
			config.notLinktest();
		}
		return config;
	}
	
	@ConditionalOnBean(HsmsSsCommunicatorConfigurationproperties.class)
	@Bean
	public HsmsSsCommunicatorConfig hsmsSsCommunicatorConfig( @Autowired HsmsSsCommunicatorConfigurationproperties hsmsSsCommunicatorConfigurationproperties) {

        HsmsSsCommunicatorConfig config = new HsmsSsCommunicatorConfig();
		
		config.socketAddress(new InetSocketAddress( hsmsSsCommunicatorConfigurationproperties.getHost() , hsmsSsCommunicatorConfigurationproperties.getPort() ));
		config.connectionMode(HsmsConnectionMode.valueOf( hsmsSsCommunicatorConfigurationproperties.getConnectionMode()));
		config.sessionId( hsmsSsCommunicatorConfigurationproperties.getSessionId());
		config.isEquip( hsmsSsCommunicatorConfigurationproperties.getIsEquip());
		config.notLinktest();
		config.timeout().t3( hsmsSsCommunicatorConfigurationproperties.getT3());
		config.timeout().t6(hsmsSsCommunicatorConfigurationproperties.getT6() );
		config.timeout().t7(hsmsSsCommunicatorConfigurationproperties.getT7());
		config.timeout().t8( hsmsSsCommunicatorConfigurationproperties.getT8());
		config.gem().mdln( hsmsSsCommunicatorConfigurationproperties.getMdln() );
		config.gem().softrev( hsmsSsCommunicatorConfigurationproperties.getSoftrev());
		config.logSubjectHeader(hsmsSsCommunicatorConfigurationproperties.getLogSubjectHeader());
		config.linktest(hsmsSsCommunicatorConfigurationproperties.getLinktest());
		

		
		return config;
	}
	
	
	@ConditionalOnBean(Secs1OnTcpIpSenderCommunicatorConfigProperties.class)
	@Bean
	public Secs1OnTcpIpCommunicatorConfig secs1OnTcpIpCommunicatorConfig( @Autowired Secs1OnTcpIpSenderCommunicatorConfigProperties secs1OnTcpIpSenderCommunicatorConfigProperties) {
		Secs1OnTcpIpCommunicatorConfig secs1Side = new Secs1OnTcpIpCommunicatorConfig();
		secs1Side.deviceId( secs1OnTcpIpSenderCommunicatorConfigProperties.getDeviceId());
		secs1Side.isEquip( secs1OnTcpIpSenderCommunicatorConfigProperties.isEquip());
		secs1Side.isMaster( secs1OnTcpIpSenderCommunicatorConfigProperties.isMaster());
		secs1Side.socketAddress( new InetSocketAddress( secs1OnTcpIpSenderCommunicatorConfigProperties.getHost() , secs1OnTcpIpSenderCommunicatorConfigProperties.getPort() ));
		secs1Side.timeout().t1(  secs1OnTcpIpSenderCommunicatorConfigProperties.getT1());
		secs1Side.timeout().t2( secs1OnTcpIpSenderCommunicatorConfigProperties.getT2());
		secs1Side.timeout().t3( secs1OnTcpIpSenderCommunicatorConfigProperties.getT3());
		secs1Side.timeout().t4( secs1OnTcpIpSenderCommunicatorConfigProperties.getT4());
		secs1Side.retry( secs1OnTcpIpSenderCommunicatorConfigProperties.getRetry());
		secs1Side.reconnectSeconds( secs1OnTcpIpSenderCommunicatorConfigProperties.getReconnectSeconds());
		secs1Side.logSubjectHeader(secs1OnTcpIpSenderCommunicatorConfigProperties.getLogSubjectHeader() );
		secs1Side.name( secs1OnTcpIpSenderCommunicatorConfigProperties.getName() );
		
		return null;
	}
	
	@ConditionalOnBean( Secs1OnTcpIpReceiverCommunicatorConfigProperties.class)
	@Bean
	public Secs1OnTcpIpReceiverCommunicatorConfig secs1OnTcpIpReceiverCommunicatorConfig(  @Autowired Secs1OnTcpIpReceiverCommunicatorConfigProperties secs1OnTcpIpReceiverCommunicatorConfigProperties) {
		
		final Secs1OnTcpIpReceiverCommunicatorConfig equipConfig = new Secs1OnTcpIpReceiverCommunicatorConfig();

		equipConfig.deviceId( secs1OnTcpIpReceiverCommunicatorConfigProperties.getDeviceId());
		equipConfig.isEquip( secs1OnTcpIpReceiverCommunicatorConfigProperties.isEquip());
		equipConfig.isMaster( secs1OnTcpIpReceiverCommunicatorConfigProperties.isMaster());
		equipConfig.socketAddress(  new InetSocketAddress( secs1OnTcpIpReceiverCommunicatorConfigProperties.getHost() , secs1OnTcpIpReceiverCommunicatorConfigProperties.getPort() ));
		equipConfig.timeout().t1( secs1OnTcpIpReceiverCommunicatorConfigProperties.getT1());
		equipConfig.timeout().t2( secs1OnTcpIpReceiverCommunicatorConfigProperties.getT2());
		equipConfig.timeout().t3( secs1OnTcpIpReceiverCommunicatorConfigProperties.getT3());
		equipConfig.timeout().t4( secs1OnTcpIpReceiverCommunicatorConfigProperties.getT4());
		equipConfig.retry( secs1OnTcpIpReceiverCommunicatorConfigProperties.getRetry());
		equipConfig.rebindSeconds( secs1OnTcpIpReceiverCommunicatorConfigProperties.getRebindSeconds() );
		equipConfig.gem().mdln( secs1OnTcpIpReceiverCommunicatorConfigProperties.getMdln() );
		equipConfig.gem().softrev( secs1OnTcpIpReceiverCommunicatorConfigProperties.getSoftrev());
		equipConfig.gem().clockType(ClockType.valueOf(  secs1OnTcpIpReceiverCommunicatorConfigProperties.getClockType()));
		equipConfig.logSubjectHeader( secs1OnTcpIpReceiverCommunicatorConfigProperties.getLogSubjectHeader());
		equipConfig.name( secs1OnTcpIpReceiverCommunicatorConfigProperties.getName());
		return equipConfig;
	}


	

}
