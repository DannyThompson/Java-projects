package assignment4;
import java.util.Scanner;
/**
 *
 * @author Danny
 */
public class Assignment4 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //**Simply testing out input, not necessarily how it will take input in the end**//
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the amount of shares bought,"
                + "followed by their cost");
        int in1 = scan.nextInt();
        int in2 = scan.nextInt();
        Deque deque = inputs(in1, in2);
        while(deque.start != null){
        System.out.println(deque.start);
        System.out.println(deque.end);
        }
    }
  
    //*Node Class. Has two elements*//
     public static class Node
     {
        public int element1;
        public int element2;
        public Node next, prev;

        //*Yay constructors8//
        public Node()
        {
            next = null;
            prev = null;
            element1 = 0;
            element2 = 0;
            
        }
        
        public Node(int e1, int e2, Node N, Node P)
        {
            element1 = e1;
            element2 = e2;
            next = N;
            prev = P;  
        }
    }
     
     //*Creating a Deque class*//
    public static class Deque
    {
      public Node start;
      public static Node end;
      public int size = 0;
      //*Empty Deque constuctor*//
        public Deque()
        {
            start = new Node();
            end = new Node();
            start.next = end;
            end.prev = start;
        }
        //*boolean if empty*//
        public boolean isEmpty()
        {
            return size == 0;
        }
        //*adding ints to the first node. Shares bought/Cost, respectively*//
        public void addFirst(int e1, int e2)
        {
            Node node = new Node();
            if (isEmpty()){
                start = node;
                end = node;
            }
            else{
                start = node;
            }
            size ++;   
        }
        //*adding to the last node*//
        public void addlast(int e1, int e2)
        {
            Node node = new Node();
            if (isEmpty()){
                start = node;
                end = node;
            }
            else{
                end.next = node;
            }
            size++;
        }
        //*removing first node*//
        public void removeFirst()
        {
            start = null;
            start= start. next;
            size --;
        }
        //*removing last node*//
        public void removeLast()
        {
        end = null;
        end = end.prev;
        size --;
        }         
    }
    
    //*Figuring out a method to take inputs and actually put them into the nodes
    public static Deque inputs(int in1, int in2)
    {
        Deque deque = new Deque();
        deque.addFirst(in1, in2);
        return deque;
    }
}
