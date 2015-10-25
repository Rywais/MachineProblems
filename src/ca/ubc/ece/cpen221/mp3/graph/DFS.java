package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class DFS {
    
    public Set<List<Vertex>> dfs(Graph graph) {
        Set<List<Vertex>> DFS = depthFirstSearch(graph);
        return DFS;
    }
    public Set<List<Vertex>> dfsAlgo (Graph graph){
        
        //Declarations
        //'visited' to know if a vertex has been visited or not
        HashMap<Vertex, Boolean> vertexVisited = new HashMap<Vertex,Boolean>(); 
        //listOfVertices would contain the list of vertices visited
        List<Vertex> listOfVertices = graph.getVertices();
        //Set of listofVertices
        Set<List<Vertex>> setOfLists = new LinkedHashSet<List<Vertex>>();
        
        
        for(Vertex vertex : listOfVertices) {
            setOfLists.add(dfsAlgo(graph, vertex, vertexVisited)); //get the tree for the current vertex
            vertexVisited.put(vertex, true); //indicate that the vertex was used for a top node
        }
        return setOfLists;
        
    }
    
    private void setParent(){
        
    }
    private void setDepth(Vertex origin){
        
    }
    
    
}
