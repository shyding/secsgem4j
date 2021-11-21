package com.shimizukenta.secs.hsms;

import java.util.Optional;
import java.util.function.Consumer;

import com.shimizukenta.secs.AbstractSecsLog;
import com.shimizukenta.secs.SecsMessage;
import com.shimizukenta.secs.secs2.Secs2;

/**
 * Inner interface.
 * 
 * @author 1000210
 *
 */
public interface HsmsAsyncSocketChannel {
	
	public void reading() throws HsmsException, InterruptedException;
	public void linktesting() throws HsmsSendMessageException, HsmsWaitReplyMessageException, HsmsException, InterruptedException;
	
	public boolean addHsmsMessageReceiveListener(HsmsMessageReceiveListener l);
	public boolean removeHsmsMessageReceiveListener(HsmsMessageReceiveListener l);
	
	public boolean addTrySendHsmsMessagePassThroughListener(HsmsMessagePassThroughListener lstnr);
	public boolean removeTrySendHsmsMessagePassThroughListener(HsmsMessagePassThroughListener lstnr);	
	
	public boolean addSendedHsmsMessagePassThroughListener(HsmsMessagePassThroughListener lstnr);
	public boolean removeSendedHsmsMessagePassThroughListener(HsmsMessagePassThroughListener lstnr);	
	
	public boolean addReceiveHsmsMessagePassThroughListener(HsmsMessagePassThroughListener lstnr);
	public boolean removeReceiveHsmsMessagePassThroughListener(HsmsMessagePassThroughListener lstnr);
	
	public boolean addSecsLogListener(Consumer<? super AbstractSecsLog> lstnr);
	public boolean removeSecsLogListener(Consumer<? super AbstractSecsLog> lstnr);
	
	public Optional<HsmsMessage> sendSelectRequest(
			AbstractHsmsSession session)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> sendSelectResponse(
			HsmsMessage primaryMsg,
			HsmsMessageSelectStatus status)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> sendDeselectRequest(
			AbstractHsmsSession session)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> sendDeselectResponse(
			HsmsMessage primaryMsg,
			HsmsMessageDeselectStatus status)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> sendLinktestRequest(
			AbstractHsmsSession session)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> sendLinktestResponse(
			HsmsMessage primaryMsg)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> sendRejectRequest(
			HsmsMessage referenceMsg,
			HsmsMessageRejectReason reason)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> sendSeparateRequest(
			AbstractHsmsSession session)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> send(
			AbstractHsmsSession session,
			int strm,
			int func,
			boolean wbit,
			Secs2 secs2)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> send(
			SecsMessage primaryMsg,
			int strm,
			int func,
			boolean wbit,
			Secs2 secs2)
					throws HsmsSendMessageException,
					HsmsWaitReplyMessageException,
					HsmsException,
					InterruptedException;
	
	public Optional<HsmsMessage> sendHsmsMessage(HsmsMessage msg)
			throws HsmsSendMessageException,
			HsmsWaitReplyMessageException,
			HsmsException,
			InterruptedException;
	
}
