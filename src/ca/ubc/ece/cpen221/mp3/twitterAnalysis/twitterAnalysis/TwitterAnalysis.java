package ca.ubc.ece.cpen221.mp3.twitterAnalysis.twitterAnalysis;

import ca.ubc.ece.cpen221.mp3.staff.*;
import ca.ubc.ece.cpen221.mp3.graph.*;
import java.io.*;
import java.util.*;

public class TwitterAnalysis {
	
	private static List<String> pastQueries = new ArrayList<String>();
	public static Graph amg;
	public static Graph alg;
	
	/**
	 * Main method
	 * @param args contains an input file and an output file,
	 *  input file must already exist
	 */
	public static void main(String[] args){
		
		amg = new AdjacencyMatrixGraph();
		alg = new AdjacencyListGraph();
		
		System.out.println("Creating Graphs based on twitter data...");
		try{
			createGraphs(amg,alg);
		}catch(Exception E){
			System.out.println("Error: Data file not found, Terminating...");
			return;
		}
		System.out.println("Graphs Created!");
		
		passQueries(args[0],args[1]);
		
	}
	
	/**
	 * Creates two graphs (One for each implementation)
	 * @param amg One of the graphs to create (Will be modified)
	 * @param alg One of the graphs to create (Will be modified)
	 * @throws FileNotFoundException Indicates that the twitterdata could not be found
	 */
	public static void createGraphs(Graph amg, Graph alg) throws FileNotFoundException{
		
		Set<Vertex> vSet = new HashSet<Vertex>();
		
		//Create buffered reader for twitter file
		BufferedReader br;
		br = new BufferedReader(new FileReader(
				"datasets/twitter.txt"));
		
		//go through each line
		String cmd;
		String[] data;
		int i = 0;
		try{
			while(true){
				cmd = br.readLine(); //read next line
				i++;
				if(i % 100 == 0){
					System.out.println(i);
					System.out.println(cmd);
					
				}
				if(cmd.isEmpty()) //If no more data, end
					break;
				data = cmd.split(" -> ");
				
				//if the vertices haven't already been added to the
				//graphs, then add them
				if(!vSet.contains(new Vertex(data[0]))){
					amg.addVertex(new Vertex(data[0]));
					alg.addVertex(new Vertex(data[0]));
					vSet.add(new Vertex(data[0]));
				}
				if(!vSet.contains(new Vertex(data[1]))){
					amg.addVertex(new Vertex(data[1]));
					alg.addVertex(new Vertex(data[1]));
					vSet.add(new Vertex(data[1]));
				}
				
				//Add the data specified edge
				amg.addEdge(new Vertex(data[0]), new Vertex(data[1]));
				alg.addEdge(new Vertex(data[0]), new Vertex(data[1]));
			}
		} catch(Exception E){}
		
		try{br.close();}
		catch(Exception E){}
	}
	
	/**
	 * Reads the queries of an input file and outputs the results to another file
	 * @param inputFile is the input file which must already exist
	 * @param outputFile is the output file for query data
	 */
	public static void passQueries(String inputFile, String outputFile){
		
		//Open buffered reader
		BufferedReader br;
		try{
		br = new BufferedReader(new FileReader(inputFile));
		} catch(FileNotFoundException E){
			System.out.println("Input File not found, Terminating...");
			return;
		}
		
		
		String cmd;
		String[] data;
		boolean endflag = false;
		
		while(!endflag){
			try{
				//Read next line
				cmd = br.readLine();
				
				if(cmd.isEmpty()){//if end of file, stop
					endflag = true;
					break;
				}
				
				data = cmd.split(" ");
				if(!data[3].equals("?")) //if query is invalid, don't carry through
					throw new ArrayIndexOutOfBoundsException();
				
				if(pastQueries.contains(cmd))//if query is repeat, don't carry through
					throw new ArrayIndexOutOfBoundsException();
				
				pastQueries.add(cmd);
				
				printQueryResult(data,outputFile);
				
			} catch(ArrayIndexOutOfBoundsException E){
				//Do nothing as query is invalid
			} catch(IOException E){
				endflag = true; //Error reading file, stop
			}
		}
		
		try{
			br.close();
		} catch(Exception E){
			//Do nothing, the program is done
		}
		
	}
	
	
	/**
	 * Prints the results of a query to an output file
	 * @param query is the split query string where the first value is
	 * the type of query, the second and third value are the relevant users
	 * and the fourth value is "?"
	 * @param OutputFile is the file for results to be output to
	 */
	public static void printQueryResult(String[] query,String OutputFile){
		
		if(query[0].equals("commonInfluencers")){
			
			System.out.println("A");
			List<Vertex> vList = Algorithms.commonUpstreamVertices(amg,
					new Vertex(query[1]),new Vertex(query[2]));
		
		
			PrintWriter writer;
			
			try{
			writer = new PrintWriter(new BufferedWriter(new FileWriter(OutputFile, true)));
			writer.println("<result>");
			for(Vertex i : vList){
				writer.println(i.getLabel());
			}
			writer.println("</result>");
			writer.close();
			
			}catch(Exception E){
				System.out.println("Error with output file");
			}
		}
		else if(query[0].equals("numRetweets")){
			
			//TODO set value once algorithm is finished
			//int numRetweets = ALGORITHM
			
			PrintWriter writer;
			
			try{
				writer = new PrintWriter(new BufferedWriter(new FileWriter(OutputFile, true)));
				writer.println("<result>");
				//TODO: Add once algorithm is present
				//writer.println(numRetweets); OR print That there is no such #
				writer.println("</result>");
				writer.close();
				
				}catch(Exception E){
					System.out.println("Error with output file");
				}
		}
		
		else{
			//do nothing because query is invalid
		}
	}
	
}

//This effectively finds the twitter Data File and reads it line by line
/*
BufferedReader br;
try{
br = new BufferedReader(new FileReader(
		"datasets/twitter.txt"));
} catch(FileNotFoundException E){
	System.out.println("File not found, Terminating...");
	return;
}
System.out.println("File found :D");

try{
	int i = 0;
	while(true){
		System.out.println(br.readLine());
		Thread.sleep(500);
		i++;
		if(i > 10)
			break;
	}
} catch(Exception E){
	
}
*/
