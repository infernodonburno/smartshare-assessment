package com.ftd.smartshare.dao;

import java.sql.*;

import com.ftd.smartshare.dto.DownloadRequestDto;
import com.ftd.smartshare.dto.UploadingDto;

public class FileDao {
	
	private static String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	public static final String URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=smartshare";
	public static final String USER = "postgres";
	public static final String PASSWORD = "bondstone";
	
	//CREATE
	    public FileDao() {
	    	
	    	try {
	    		
	    		Class.forName(DRIVER_CLASS_NAME);
	    		
	    	}catch(ClassNotFoundException e) {
	    		
	    			System.out.println("Driver cannot be found");
	    	}
    }
	
	public boolean uploadFile(UploadingDto upload) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        		PreparedStatement stmt = connection.prepareStatement("INSERT INTO files" +
                    "(file_name, file, time_created, expiry_time, max_downloads, total_downloads, password)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, upload.getFileName());
            stmt.setBytes(2, upload.getFileSize());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis() + upload.getExpireTime() * 60000));
            stmt.setInt(5, upload.getMaxDownloads());
            stmt.setInt(6, upload.getTotalDownloads());
            stmt.setString(7, upload.getPassword());
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
        }
        return false;
    }
	
	//READ
	public long[] viewFile(Filing view) {
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
				
				Statement stmt = connection.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * from files;");
				
				if(result.getString("file_name").equals(view.getFileName()) && result.getString("password").contentEquals(view.getPassword())) 
				{
					long remainingTime = result.getTimestamp("expiry_time").getTime() - result.getTimestamp("time_created").getTime(); 
					long remainingDownloads = result.getInt("max_downloads") - result.getInt("total_downloads");
					long[] mArray = {remainingTime, remainingDownloads};
					return mArray;
				}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
				
	}
	
	//Update
	public byte[] downloadFile(DownloadRequestDto download) {
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
			
				Statement stmt = connection.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * from files;");
				
				if(result.getString("file_name").equals(download.getFileName()) && result.getString("password").contentEquals(download.getPassword())) 
				{
					PreparedStatement statement = connection.prepareStatement("UPDATE files SET total_downloads = total_downloads + 1 WHERE file_name = ?");
					statement.setString(1,  result.getString("file_name"));
					statement.executeUpdate();
				}
				
				return result.getBytes("file");
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Delete
	public void fileDeleter(ResultSet results, Connection connect) throws SQLException {
		
		long remainingTime = results.getTimestamp("expiry_time").getTime() - results.getTimestamp("time_created").getTime(); 
		long remainingDownloads = results.getInt("max_downloads") - results.getInt("total_downloads");
		
		if(remainingTime <= 0 || remainingDownloads <= 0) 
		{
			PreparedStatement statement = connect.prepareStatement("DELETE FROM files WHERE file_name = ?");
			statement.setString(1, results.getString("file_name"));
			statement.executeUpdate();
		}
		
		
		}
	//HasFile
	public boolean doesFileExist(String filename, Connection connect) throws SQLException {
		
		Statement stmt = connect.createStatement();
		ResultSet resultSet = stmt.executeQuery("SELECT * from smartshare.files;");
		while(resultSet.next())
		{
				
			if (resultSet.getString("file_name") == filename) 
			{
				return true;
			}
	
		}
		return false;
	}
		
}
	



