package com.ftd.smartshare.dto;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DownloadRequestDto {
	
	@XmlElement(name = "filename")
	private String fileName;
	
	@XmlElement(name = "password")
	private String password;
	
	public DownloadRequestDto() {}
	
	public DownloadRequestDto(String fileName, String password) {
		
		super();
		this.fileName = fileName;
		this.password = password;
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

}
