package com.shimizukenta.secs.ext.config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.management.RuntimeErrorException;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.shimizukenta.secs.OpenAndCloseable;
import com.shimizukenta.secs.SecsCommunicator;
import com.shimizukenta.secs.SecsException;
import com.shimizukenta.secs.SecsLog;
import com.shimizukenta.secs.SecsLogListener;
import com.shimizukenta.secs.SecsMessage;
import com.shimizukenta.secs.SecsSendMessageException;
import com.shimizukenta.secs.SecsWaitReplyMessageException;
import com.shimizukenta.secs.ext.annotation.SecsMsgListener;
import com.shimizukenta.secs.ext.util.SecsUtils;
import com.shimizukenta.secs.ext.util.SpringUtils;
import com.shimizukenta.secs.hsms.HsmsMessage;
import com.shimizukenta.secs.hsms.HsmsReceiveMessageLog;
import com.shimizukenta.secs.hsms.HsmsSession;
import com.shimizukenta.secs.hsmsgs.HsmsGsCommunicator;
import com.shimizukenta.secs.hsmsgs.HsmsGsUnknownSessionIdException;
import com.shimizukenta.secs.secs2.Secs2;



/**
 * 抽象消息接收者
 *  SecsMessageReceiveListener 出现异常无法接收消息 , 具体原因后续排查
 * @author dsy
 *
 */

@Order( value = Ordered.LOWEST_PRECEDENCE )
@SecsMsgListener
public abstract class AbstractSecsMsgListener implements SecsLogListener  {


	private final Logger logger = LoggerFactory.getLogger(AbstractSecsMsgListener.class);


	/**
	 * 
	 */
	public final  static ExecutorService EXEC = Executors.newCachedThreadPool();
	
	public  static final String HANDLERS_STR = "HANDLERS";
	
	
	public  static OpenAndCloseable  secsCommunicator; ;
	
	
	/**
	 *  本类处理类暂存
	 */
	private final MultiKeyMap<Integer, Consumer<HsmsMessage>> HANDLERS = new MultiKeyMap<>();
	
	
	
	/** 打印所有日志
	 * @return
	 */
	public boolean logAll() {
		
		return false;
	}


	@Override
	public void received(SecsLog event) {
		if (event instanceof HsmsReceiveMessageLog) {
			HsmsReceiveMessageLog logInfo = (HsmsReceiveMessageLog) event;

			Optional<Object> value = logInfo.value();
			if(value.isPresent()) {
				Object object = value.get();
				HsmsMessage msg = (HsmsMessage) object;
				if(SecsUtils.dataMessage(msg)) {
					

					Consumer<HsmsMessage> consumer = HANDLERS.get(msg.getStream(), msg.getFunction());
					if (Objects.nonNull(consumer)) {
						
						EXEC.submit(() -> consumer.accept( msg )) ;
					} else {
						/**
						 * 没有对应的处理类
						 */
						if(this.logAll()) {
							logger.error("inhandler:{}, received_ignore_data_msg:{}",this.getClass().getName(),   msg );

						}

//						try {
//							hsmsSsCommunicator.send(9, 9 , false) ;
//						} catch (SecsException | InterruptedException e) {
//							log.error("error:{}" , e) ;
//						}
					}
				}

			}

		}else {
			logger.warn("data:{}", event);
		}
		
	
		
	}


	
	
	/**获得通讯器
	 * @return
	 */
	public static SecsCommunicator getSecsCommunicator(int sessionId) {
		
		 OpenAndCloseable openAndCloseable = SpringUtils.getBean( OpenAndCloseable.class) ;
		 boolean gs = SecsUtils.isGs(openAndCloseable);
		 if( !gs) {
			 return (SecsCommunicator)openAndCloseable;
		 }else {
			 HsmsGsCommunicator hsmsGsCommunicator = (HsmsGsCommunicator)openAndCloseable;
			 
			try {
				HsmsSession hsmsSession = hsmsGsCommunicator.getSession(sessionId);
				return hsmsSession ;
			} catch (HsmsGsUnknownSessionIdException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
			 
			
		 }
		 
		
	}
	
	
	
	public static void setSecsCommunicator(OpenAndCloseable secsCommunicator) {
		AbstractSecsMsgListener.secsCommunicator = secsCommunicator;
	}


	/** 通过容器中的通讯器回复消息
	 * @param primary
	 * @param wbit
	 * @param secs2
	 * @return
	 * @throws SecsSendMessageException
	 * @throws SecsWaitReplyMessageException
	 * @throws SecsException
	 * @throws InterruptedException
	 */
	public static Optional<SecsMessage>  reply(SecsMessage primary,  boolean wbit, Secs2 secs2) throws SecsSendMessageException, SecsWaitReplyMessageException, SecsException, InterruptedException {
		int stream = primary.getStream();
		int function = primary.getFunction();
		int sessionId = primary.sessionId();
		return send( sessionId, stream , function +1 , wbit, secs2);
	}
	

	/** 指定回话器
	 * @param secsCommunicator
	 * @param primary
	 * @param wbit
	 * @param secs2
	 * @return
	 * @throws SecsSendMessageException
	 * @throws SecsWaitReplyMessageException
	 * @throws SecsException
	 * @throws InterruptedException
	 */
	public static Optional<SecsMessage>  reply(SecsCommunicator secsCommunicator ,SecsMessage primary,  boolean wbit, Secs2 secs2) throws SecsSendMessageException, SecsWaitReplyMessageException, SecsException, InterruptedException {
		int stream = primary.getStream();
		int function = primary.getFunction();
		return secsCommunicator.send( stream , function + 1 , wbit, secs2);
	}
	

	
	/**
	  * @Title: send
	  * @Description: 限定stream function 请求
	  * @param @param strm
	  * @param @param func
	  * @param @param wbit
	  * @param @param secs2
	  * @param @return
	  * @param @throws SecsSendMessageException
	  * @param @throws SecsWaitReplyMessageException
	  * @param @throws SecsException
	  * @param @throws InterruptedException    设定文件
	  * @return Optional<SecsMessage>    返回类型
	  * @throws
	  */
	public  static Optional<SecsMessage> send( int sessionId , int strm, int func, boolean wbit, Secs2 secs2)
			throws SecsSendMessageException, SecsWaitReplyMessageException, SecsException, InterruptedException {
		
		return getSecsCommunicator( sessionId ).send(strm, func, wbit, secs2);
	}
	
	
	public static Optional<SecsMessage> send(SecsMessage primaryMsg, int strm, int func, boolean wbit) throws SecsSendMessageException, SecsWaitReplyMessageException, SecsException, InterruptedException{
		
		return getSecsCommunicator( primaryMsg.sessionId() ).send(primaryMsg, strm, func, wbit);
	}
	

}
