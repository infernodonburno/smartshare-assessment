package com.ftd.smartshare.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ViewRequestDto {
	
	@XmlElement(name = "filename")
	private String fileName;
	
	@XmlElement(name = "password")
	private String password;
	
	@XmlElement(name = "expiretime")
	private long expireTime;
	
	@XmlElement(name = "expiretime")
	private long remainingTime;
	
	@XmlElement(name = "timecreated")
	private int creationTime;

	public ViewRequestDto(String fileName, String password, long expireTime, int remainingTime, int creationTime) {
		super();
		this.fileName = fileName;
		this.password = password;
		this.expireTime = expireTime;
		this.remainingTime = remainingTime;
		this.creationTime = creationTime;
	}

	public String getFileName() {
		return fileName;
	}

	public String getPassword() {
		return password;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public long getRemainingTime() {
		return remainingTime;
	}

	public int getCreationTime() {
		return creationTime;
	}
	
	
}
