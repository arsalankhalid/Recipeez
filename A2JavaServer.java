package btp400.A2.javaserver;

import java.net.*;
import java.io.*;

public class A2JavaServer {
	
	public static void main(String[] args)
	{
		ServerSocket serverSocket;
		
		try
		{
			serverSocket = new ServerSocket(8000);
			
			Socket socketConnection = serverSocket.accept();
			
			DataOutputStream dosToClient = new DataOutputStream(socketConnection.getOutputStream());
			
			DataInputStream disFromClient = new DataInputStream(socketConnection.getInputStream());
			
			try
			{
				while(true)
				{
					String Receipe = disFromClient.readUTF();
				}
			}
			catch (EOFException eof)
			{
                System.out.println( "*** THE client has terminated connection ***" );
			}
			
		}
		catch (IOException e)
		{
		System.out.println("I/O error in data exchange");
			e.printStackTrace();
		}
	}

}
