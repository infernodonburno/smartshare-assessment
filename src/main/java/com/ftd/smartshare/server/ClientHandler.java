package com.ftd.smartshare.server;


import com.ftd.smartshare.dao.*;

import com.ftd.smartshare.dto.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;
import java.io.IOException;

public class ClientHandler implements Runnable {
	
	private Socket socket;
	
	public ClientHandler(Socket socket) {
		
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		FileDao filer = new FileDao();
		
		try {
			JAXBContext context = JAXBContext.newInstance(DownloadDto.class, 
														  DownloadRequestDto.class, 
														  UploadingDto.class
														  );
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			StringReader stringReader = new StringReader(in.readLine());
			Object r = unmarshaller.unmarshal(stringReader);
		    
			
			if(r instanceof UploadingDto)
			{
				UploadingDto upload = (UploadingDto) r;
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeBoolean(filer.uploadFile(upload));
			}
			
			if(r instanceof DownloadRequestDto)
			{
				DownloadRequestDto download = (DownloadRequestDto) r;
				DownloadDto down = new DownloadDto(filer.downloadFile(download));
				
				Marshaller marshaller = context.createMarshaller();
				StringWriter stringWriter = new StringWriter();
	    		marshaller.marshal(down, stringWriter);
	    		
	    		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	    		
	    		out.write(stringWriter.toString());
	    		out.newLine();
	    		out.flush();
			}
			
		}catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		
	}

}
