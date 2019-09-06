package com.ftd.smartshare.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadingDto {
	
	@XmlElement(name = "filename")
	private String fileName;
	
	@XmlElement(name = "password")
	private String password;
	
	@XmlElement(name = "filesize")
	private byte[] fileSize;
	
	@XmlElement(name = "expiretime")
	private int expireTime;
	
	@XmlElement(name = "maxdownloads")
	private int maxDownloads;
	
	@XmlElement(name = "totaldownloads")
	private int totalDownloads;
	
	
	public UploadingDto() {}

	public UploadingDto(String fileName, String password, byte[] fileSize, int expireTime, int maxDownloads, int totalDownloads ) {
		super();
		this.fileName = fileName;
		this.password = password;
		this.fileSize = fileSize;
		this.expireTime = expireTime;
		this.maxDownloads = maxDownloads;
		this.totalDownloads = totalDownloads;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getFileSize() {
		return fileSize;
	}

	public void setFileSize(byte[] fileSize) {
		this.fileSize = fileSize;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	public int getMaxDownloads() {
		return maxDownloads;
	}

	public void setMaxDownloads(int maxDownloads) {
		this.maxDownloads = maxDownloads;
	}
	
	public int getTotalDownloads() {
		return totalDownloads;
	}

	public void setTotalDownloads(int totalDownloads) {
		this.totalDownloads = totalDownloads;
	}
	
	
	
	

}
