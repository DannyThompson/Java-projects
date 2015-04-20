package assignment6;
import java.util.Scanner;
import static javafx.beans.binding.Bindings.length;
public class Assignment6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        priorityQueue P = new priorityQueue();
        jobScheduler(P);
 
    }
public static void jobScheduler(priorityQueue pqueue){
    Scanner s = new Scanner(System.in);
    node currentJob = null;
    String userInput = " ";
    
    while (!userInput.equals("done")){
        if(currentJob == null){
            currentJob = pqueue.removeMin();
        }
        if(currentJob != null){
            for(int i = 0; i <= currentJob.length; i++){
            System.out.println("RUNNING JOB:" + currentJob.job);
            currentJob.length--;
            }
            if(currentJob.length == 0){
                currentJob = null;
            }
        }
        
    
    System.out.print("| ");
    userInput = s.nextLine();
    String delim = "[ ]+";
    if(userInput.equals("done")){
        System.exit(0);
    }
    if(!userInput.equals("done")){
        String[] tokens = userInput.split(delim);
        int priority = Integer.parseInt(tokens[8]);
        String jobName = tokens[2];
        int length = Integer.parseInt(tokens[5]);
        pqueue.insert(priority, jobName, length);  
    }
    }
}

public static class node{
    public node next;
    String job;
    int key;
    int length;
    
    public node(int key, String job, int length)
    {
        this.job = job;
        this.key = key;
        this.length = length;
    
    }
}
public static class priorityQueue{
    public static node min;
    public String job;
    public int key;
    public int length = 0;
    
    public priorityQueue(){
        min = null;
    }
   public void increment(){
       length++;
   }
   public void decrement(){
       length--;
   }
   public boolean isEmpty(){
       return(min==null);
   }
       
    public void printStr(){
        String output = "";
        node current = min;
        while (current!= null){
            output+=current.job;
            current = current.next;
        }
        System.out.println(output);
    }

    
    public void insert(int key, String job,int length){
        node newNode = new node(key, job, length);
        if(isEmpty()){
            min = newNode;
        }
        else{
            node current = min;
            if(current.key >= key){
                newNode.next = current;
                min = newNode;
            }
            else{
                while(current.next != null && current.next.key <= key){
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
        }
        increment();
    }
    public node removeMin(){
        if(isEmpty()){
            return null;
        }
        else{
            node temp = min;
            min = min.next;
            decrement();
            return temp;
        }
    }

}
}
