package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.*;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

public class RestaurantDBServer {

	private final RestaurantDB db;
	private ServerSocket mySocket;
	private Socket clientSocket;
	
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
				/*BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				String inputLine;
				while((inputLine = in.readLine()) != null){
					System.out.println(inputLine);
					
				}
				*/
				Thread thread = new Thread(new ServerThread(clientSocket));
				thread.start();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
