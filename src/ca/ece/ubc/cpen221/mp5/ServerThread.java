package ca.ece.ubc.cpen221.mp5;

import java.net.*;
import java.io.*;

public class ServerThread implements Runnable {
	
	private Socket clientSocket;
	private static int nextThreadID = 0;
	private int threadID;
	
	ServerThread(Socket clientSocket){
		this.clientSocket = clientSocket;
		threadID = nextThreadID++;
	}
	
	public void run(){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				//TODO: Add actual action & Delete this test...
				System.out.println(threadID + ": " + inputLine);
				
				//TODO: Insert ending condition
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
