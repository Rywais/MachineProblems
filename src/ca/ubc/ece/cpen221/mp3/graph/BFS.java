package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class BFS {
    public BFS(Vertex u){
        
        Vertex v;
        
        this.setDepth(null); // do hashmap shit instead of this.
        this.setParent();    // do hashmap shit instead of this.
        this.visited = true; // do hashmap shit instead of this.
        
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(u);
        
        while(q.isEmpty()){
            v = q.remove();
            
            for(){
                
            }
        }
        
    }
    
    private void setParent(){
        
    }
    private void setDepth(Vertex origin){
        
    }
    
    
}
