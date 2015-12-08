package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.*;


public class RestaurantDBServer {

	private final RestaurantDB db;
	private static final String restaurantData = "data/restaurants.json";
	private static final String reviewData = "data/reviews.json";
	private static final String userData = "data/users.json";
	
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
	
	/**
	 * Launches the Database Server as it currently exists
	 * @param args
	 */
	public static void main(String args[]){
		RestaurantDBServer myServer = new RestaurantDBServer(7979,restaurantData,reviewData,userData);
		
	}

}
