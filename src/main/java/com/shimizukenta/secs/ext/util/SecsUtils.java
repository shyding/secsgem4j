package com.shimizukenta.secs.ext.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.util.StringUtils;

import com.shimizukenta.secs.ByteArrayProperty;
import com.shimizukenta.secs.OpenAndCloseable;
import com.shimizukenta.secs.SecsCommunicator;
import com.shimizukenta.secs.ext.annotation.SecsMsgListener;
import com.shimizukenta.secs.ext.config.AbstractSecsMsgListener;
import com.shimizukenta.secs.ext.config.SecsAutoConfiguration;
import com.shimizukenta.secs.hsms.HsmsMessage;
import com.shimizukenta.secs.hsms.HsmsMessageType;
import com.shimizukenta.secs.hsmsgs.HsmsGsCommunicator;
import com.shimizukenta.secs.hsmsss.HsmsSsCommunicator;



public class SecsUtils {



	
	private final static Logger logger = LoggerFactory.getLogger(SecsUtils.class);
	private static final OpenAndCloseable HsmsSsCommunicator = null;


	
	/**随否是数据报文
	 * @param msg
	 * @return
	 */
	public static final boolean dataMessage( HsmsMessage msg) {
		return HsmsMessageType.get(msg) == HsmsMessageType.DATA;
	}
	
	
	/**
	 * 通过设备id 来获取请求头
	 * 
	 * @param deviceId
	 * @return
	 */
	public final static ByteArrayProperty changeDeviceid(int deviceId) {
		ByteArrayProperty sessionIdBytes = ByteArrayProperty.newInstance(new byte[] { 0, 0 });

		int v = deviceId;
		byte[] bs = new byte[] { (byte) (v >> 8), (byte) v };
		sessionIdBytes.set(bs);

		return sessionIdBytes;

	}

	/**
	 * 将设备id 进行md5 混淆取得数字作为回话id
	 * 
	 * @param str
	 * @return
	 */
	public static int deviceIdStrToInt(String str) {

		String md5 = md5(str);
		
		return getNumbers(md5);
	}

	private static String md5(String input) {
		if (input == null || input.length() == 0) {
			input = Objects.toString(input);
		}
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(input.getBytes());
			byte[] byteArray = md5.digest();

			StringBuilder sb = new StringBuilder();
			for (byte b : byteArray) {
				// 一个byte格式化成两位的16进制，不足两位高位补零
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 截取数字
	public static int getNumbers(String str) {
		if (str == null || str.length() == 0)
			return 0;

		String regEx = "[^0-9]";

		Pattern p = Pattern.compile(regEx);

		Matcher m = p.matcher(str);
		String trim = m.replaceAll("").trim();
		Long parseLong = Long.valueOf(trim.substring(0, 8));
		if(parseLong < 0) {
			parseLong = parseLong * (-1);
		}
		return  parseLong.intValue();

	}


	



	
	/** 添加listener监听
	 * @author dsy
	 * @param comm
	 * @return
	 */
	public final static OpenAndCloseable wrappSecsCommunicator(OpenAndCloseable comm , List<AbstractSecsMsgListener> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			boolean gs = isGs(comm);
			HsmsGsCommunicator com  = null ;
			SecsCommunicator com2 = null ;
			if(gs) {
				 com = (HsmsGsCommunicator)comm;
			}else {
				com2 = (SecsCommunicator) comm;
			}
			for  ( AbstractSecsMsgListener item :list ) {


				try {
					final Class<?> ultimateTargetClass = AopProxyUtils.ultimateTargetClass( item);
					SecsMsgListener annotation = ultimateTargetClass.getAnnotation(SecsMsgListener.class);
					if (Objects.nonNull(annotation) && StringUtils.isEmpty(annotation.id()) && annotation.global()) {
						addLogListener(com, com2, item);
						
					} else if (Objects.nonNull(annotation) ) {
						addLogListener(com, com2, item);
					} else {
						addLogListener(com, com2, item);
					}
				} catch (Exception e) {

					logger.error("error:{}", e);
				}

			
				
			};
			

		}


		Runnable run = () -> {
			try {
				comm.open();
			} catch (Exception e) {
				logger.debug("open_error==>:");
			}
		};

		/**
		 * 单独起一个线程，避免hsms 打开失败导致应用无法启动
		 */

		Thread thread = new Thread(run, "hsms-commonunite");
		thread.setDaemon(true);
		thread.start();
		/**
		 * 设置监听器
		 */

		setList(list, comm);
		return comm;
	}


	private static void addLogListener(HsmsGsCommunicator com, SecsCommunicator com2, AbstractSecsMsgListener lsn) {
		if(Objects.nonNull(com)) {
			com.addSecsLogListener(lsn);
		}
		if(Objects.nonNull(com2)) {
			com2.addSecsLogListener(lsn);
		}
	}

	/** 判断是否是ss
	 * @param comm
	 * @return
	 */
	public final static boolean isSs( OpenAndCloseable comm) {
		
		return comm instanceof HsmsSsCommunicator ;
	}

	

	
    /** 判断是否是Gs
     * @param comm
     * @return
     */
    public final static boolean isGs( OpenAndCloseable comm) {
		
		return comm instanceof HsmsGsCommunicator ;
	}

	@SuppressWarnings("static-access")
	private static void setList(List<AbstractSecsMsgListener> list2, OpenAndCloseable comm) {

		if (CollectionUtils.isEmpty(list2)) {
			return;
		}
		list2.parallelStream().forEach(lsn -> {
			lsn.setSecsCommunicator(comm);

		});

	}



	

}
