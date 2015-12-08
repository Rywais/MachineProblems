package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.*;


public class RestaurantDBServer {

	private final RestaurantDB db;
	
	/**
	 * Constructor
	 * 
	 * @param port
	 * @param filename1
	 * @param filename2
	 * @param filename3
	 */
	public RestaurantDBServer(int port, String restaurantFile, String reviewFile, String userFile) {
		this.db = new RestaurantDB(restaurantFile, reviewFile, userFile);
		
		try (ServerSocket mySocket = new ServerSocket(port);

		) {
			
			
			while(true){
				Socket clientSocket = mySocket.accept();
				
				Thread thread = new Thread(new ServerThread(clientSocket, db));
				thread.start();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
