package ca.ece.ubc.cpen221.mp5;

import java.net.*;
import java.io.*;

public class ServerThread implements Runnable {
	
	private Socket clientSocket;
	private static int nextThreadID = 0;
	private int threadID;
	private RestaurantDB db;
	
	ServerThread(Socket clientSocket, RestaurantDB db){
		this.clientSocket = clientSocket;
		this.db = db;
		threadID = nextThreadID++;
	}
	
	public void run(){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 PrintWriter out =
				        new PrintWriter(clientSocket.getOutputStream(), true);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				//inputLine is the input from the Client
				
				out.println(RestaurantDB.toJSON(db.query(inputLine)));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
