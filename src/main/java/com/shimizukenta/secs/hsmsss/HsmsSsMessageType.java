package com.shimizukenta.secs.hsmsss;

public enum HsmsSsMessageType {
	
	UNDEFINED( (byte)0x80, (byte)0x80 ),
	
	DATA( (byte)0, (byte)0 ),
	SELECT_REQ( (byte)0, (byte)1 ),
	SELECT_RSP( (byte)0, (byte)2 ),
	LINKTEST_REQ( (byte)0, (byte)5 ),
	LINKTEST_RSP( (byte)0, (byte)6 ),
	REJECT_REQ( (byte)0, (byte)7 ),
	SEPARATE_REQ( (byte)0, (byte)9 ),
	
	;
	
	private final byte p;
	private final byte s;
	
	private HsmsSsMessageType(byte p, byte s) {
		this.p = p;
		this.s = s;
	}

	public byte pType() {
		return p;
	}
	
	public byte sType() {
		return s;
	}
	
	public static HsmsSsMessageType get(byte p, byte s) {
		
		for ( HsmsSsMessageType t : values() ) {
			if ( t != UNDEFINED && t.p == p && t.s == s ) {
				return t;
			}
		}
		
		return UNDEFINED;
	}
	
	public static HsmsSsMessageType get(HsmsSsMessage msg) {
		
		byte[] head = msg.header10Bytes();
		byte p = head[4];
		byte s = head[5];
		
		return get(p, s);
	}
	
	public static boolean supportPType(byte p) {
		
		for ( HsmsSsMessageType t : values() ) {
			if ( t != UNDEFINED && t.p == p ) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean supportPType(HsmsSsMessage msg) {
		byte[] head = msg.header10Bytes();
		byte p = head[4];
		return supportPType(p);
	}
	
	public static boolean supportSType(byte s) {
		
		for ( HsmsSsMessageType t : values() ) {
			if ( t != UNDEFINED && t.s == s ) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean supportSType(HsmsSsMessage msg) {
		byte[] head = msg.header10Bytes();
		byte s = head[5];
		return supportPType(s);
	}
	
}
