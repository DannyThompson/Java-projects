package assignment7;
import java.util.Scanner;


/**
 *
 * @author Danny
 */
public class Assignment7 {

    public static void main(String[] args) {
        // TODO code application logic here

    }
    int[] vertexList = {1, 2, 3, 4, 5, 6, 7, 8};
    
    int current = vertexList[0];
    public int weightList(int i, int j , int d){
        
        
        
    }
    public class edge{
            String startV;
            String endV;
            int weight;
        }
        
        


public class graph{
    
    public boolean adjacency[][];
    public int vertexCount;
    
    public graph (int vertexCount){
        this.vertexCount = vertexCount;
        adjacency = new boolean[vertexCount][vertexCount];
    }
    public void addEdge(int i, int j, int weight){
        if( i>=0 && i<vertexCount && j>=0 && j<vertexCount && weight >0){
            adjacency[i][j]=true;
            adjacency[j][i]=true;
        }
    }
    
    public void removeEdge(int i, int j){
        if (//Still working on this part ){
            adjacency[i][j] = false;
            adjacency[j][i] = false;
        }
    }
  
}
