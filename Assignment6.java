package assignment6;
import java.util.Scanner;
import static javafx.beans.binding.Bindings.length;
public class Assignment6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        priorityQueue P = new priorityQueue();
        P.insertEnd("hi", 2, 3);
        P.insertEnd("hii", 4, 5);
        P.insertEnd("hiiy", 6, 7);
        
        System.out.println(P.removeMin());
        
        
        
        
       
        
        
    }
    

public static class node{
    public node next, prev;
    String job;
    int key;
    int length;
    
    public node(String job, int key, int length)
    {
        this.job = job;
        this.key = key;
        this.length = length;
    
    }
}
public static class priorityQueue{
    public static node end;
    public static node start;
    public String job;
    public int key;
    public int length;
    
    public int getKey(){
        return key;
    }
    public int getLength(){
        return length;
    }
    
       
    public String getJob(){
        return job;
    }
    
    public void insertEnd(String job, int length,int priority){
        node newNode = new node(job, length, priority);
        if(priorityQueue.end == null){
            priorityQueue.end = newNode;
        }
        else{
            priorityQueue.end.next = newNode;
            newNode.prev = priorityQueue.end;
            priorityQueue.end = newNode;
            
        }
    }
    public void recursive(){
        
    }
    public String removeMin(){
        int temp;
        int temp2;
        node current = priorityQueue.start;
        node currenttemp = priorityQueue.start;
        temp = current.key;
        temp2 = current.next.key;
        while(current.next != null)
        {
            temp2 = current.next.key;
            if(temp < temp2)
            {
                temp = temp;
                currenttemp = current; 
                current = current.next.next;
                temp2 = current.next.key;
            }
                else
                    {
                      if(temp2 < temp){
                          temp2 = temp2;
                          currenttemp = current.next;
                          current = current.next.next;
                          temp = current.key;
                        }
                    }
        }
        return currenttemp.job;
            
        }
        
           
        }
    }
