package com.ftd.smartshare.dao;

import java.sql.Timestamp;

public class Filing {
	
	private String fileName;
	private int id;
	private byte[] fileSize;
	private Timestamp creationTime; 
	private int expireTime;
	private int maxDownload;
	private int totalDownload;
	private String password;
	
	public Filing() {}

	
	
	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public byte[] getFileSize() {
		return fileSize;
	}



	public void setFileSize(byte[] fileSize) {
		this.fileSize = fileSize;
	}



	public Timestamp getCreationTime() {
		return creationTime;
	}



	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}



	public int getExpireTime() {
		return expireTime;
	}



	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}



	public int getMaxDownload() {
		return maxDownload;
	}



	public void setMaxDownload(int maxDownload) {
		this.maxDownload = maxDownload;
	}



	public int getTotalDownload() {
		return totalDownload;
	}



	public void setTotalDownload(int totalDownload) {
		this.totalDownload = totalDownload;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
    public String toString() {
        return "FileInfo{" +
                "name =" + fileName + "id =" + id +
                ", file size ='" + fileSize + '\'' +
                ", created ='" + creationTime + '\'' +
                ", expires=" + expireTime +
                ", max downloads=" + maxDownload +
                ", password =" + password +
                '}';
    }

}
