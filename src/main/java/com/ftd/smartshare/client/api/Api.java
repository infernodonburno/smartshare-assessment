package com.ftd.smartshare.client.api;

import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



import com.ftd.smartshare.utils.NotImplementedException;
import com.ftd.smartshare.dto.*;
import com.ftd.smartshare.dao.*;

import java.net.Socket;

public final class Api {

    private static final String HOST    = "localhost";
    private static final int    PORT    = 3000;

    private Api () {
        
    }

    /**
     * Send download request
     *
     * @param downloadRequestDto    JAXB annotated class representing the download request
     * @return true if request was successful and false if unsuccessful
     */
    public static boolean download(DownloadDto downloadRequestDto) {
     
        
        try (Socket socket = new Socket(HOST, PORT)) {
    		 JAXBContext context = JAXBContext.newInstance(DownloadRequestDto.class, DownloadDto.class);
    		 Marshaller marshaller = context.createMarshaller();
    		 
    		 StringWriter stringWriter = new StringWriter();
     		 marshaller.marshal(downloadRequestDto, stringWriter);
     		 
     		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    		out.write(stringWriter.toString());
    		out.newLine();
    		out.flush();
    		
    		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		StringReader stringReader = new StringReader(input.readLine());
    		
    		Unmarshaller unmarshaller = context.createUnmarshaller();
			DownloadDto downloadDto = (DownloadDto) unmarshaller.unmarshal(stringReader);
			
			return downloadDto.getFileSize() != null;
    		 
        }catch(IOException | JAXBException e) {
        	
        	e.printStackTrace();
        }
        
        return false;
    
    }

    /**
     * Send upload request
     *
     * @param uploadRequestDto      JAXB annotated class representing the upload request
     * @return true if request was successful and false if unsuccessful
     */
    public static boolean upload(UploadingDto uploadRequestDto) {
    	
    	try (Socket socket = new Socket(HOST, PORT)) {
   		 JAXBContext context = JAXBContext.newInstance(UploadingDto.class);
   		 Marshaller marshaller = context.createMarshaller();
   		 
   		 StringWriter stringWriter = new StringWriter();
         marshaller.marshal(uploadRequestDto, stringWriter);
    		 
         BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
   		 out.write(stringWriter.toString());
   		 out.newLine();
   		 out.flush();
   		 
   		 DataInputStream in = new DataInputStream(socket.getInputStream());
		
   		 //return if non zero (1)
		 return in.readBoolean();
   		 
         }catch(IOException | JAXBException e) {
       	
       	e.printStackTrace();
       }
       
       return false;
    }
    
    /** 
     * Send view Request
     * 
     * JAXB annotated representing the view request to be used in sub commands
     * 
     * @return creation time and expiring time, null otherwise
     * 
     */
    
    public static long[] view(ViewRequestDto viewing) {
    	
    	try (Socket socket = new Socket(HOST, PORT)) {
   		 JAXBContext context = JAXBContext.newInstance(ViewRequestDto.class);
   		 Marshaller marshaller = context.createMarshaller();
   		 
   		 StringWriter stringWriter = new StringWriter();
         marshaller.marshal(viewing, stringWriter);
    		 
    	 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
   		 out.write(stringWriter.toString());
   		 out.newLine();
   		 out.flush();
		
		 long[] time = {viewing.getCreationTime(), viewing.getExpireTime()};
		 return time;
   		 
       }catch(IOException | JAXBException e) {
       	
       	e.printStackTrace();
       }
       
       return null;
		
	}

}
