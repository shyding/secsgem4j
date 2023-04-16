package com.shimizukenta.secs.ext.properties;

public class AbstractSecsConfigProperties {
	
	protected  String logSubjectHeader;
	
	protected  int deviceId;
	
	protected  boolean isEquip = true;
	
	protected  boolean isMaster;
	
	protected String host = "localhost";;
	
	protected int port = 10068 ;
	
	
	
	protected  float t1 = 1.0F;
	
	protected  float t2 = 15.0F;
	
	protected  float t3 = 45.0F;
	
	protected  float t4 = 45.0F;
	
	protected int retry = 3 ;
	
	protected String mdln ="MDLN-A";
	
	protected String softrev="000001" ;

	public String getLogSubjectHeader() {
		return logSubjectHeader;
	}

	public void setLogSubjectHeader(String logSubjectHeader) {
		this.logSubjectHeader = logSubjectHeader;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public boolean isEquip() {
		return isEquip;
	}

	public void setEquip(boolean isEquip) {
		this.isEquip = isEquip;
	}

	public boolean isMaster() {
		return isMaster;
	}

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}

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

	public float getT1() {
		return t1;
	}

	public void setT1(float t1) {
		this.t1 = t1;
	}

	public float getT2() {
		return t2;
	}

	public void setT2(float t2) {
		this.t2 = t2;
	}

	public float getT3() {
		return t3;
	}

	public void setT3(float t3) {
		this.t3 = t3;
	}

	public float getT4() {
		return t4;
	}

	public void setT4(float t4) {
		this.t4 = t4;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public String getMdln() {
		return mdln;
	}

	public void setMdln(String mdln) {
		this.mdln = mdln;
	}

	public String getSoftrev() {
		return softrev;
	}

	public void setSoftrev(String softrev) {
		this.softrev = softrev;
	}
	
	
	


}
