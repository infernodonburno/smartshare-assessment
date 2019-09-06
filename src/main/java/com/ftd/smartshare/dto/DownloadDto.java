package com.ftd.smartshare.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DownloadDto {
	
	public DownloadDto() {}
	
	@XmlElement(name = "filesize")
	private byte[] fileSize;

	public DownloadDto(byte[] fileSize) {
		super();
		this.fileSize = fileSize;
	}

	public byte[] getFileSize() {
		return fileSize;
	}

	public void setFileSize(byte[] fileSize) {
		this.fileSize = fileSize;
	}
	
	

}
