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
    public static void main(String[] args) 
    {
        //**Simply testing out input, not necessarily how it will take input in the end**//
       
        Deque deque = new Deque();
        deque.enqueueStart(22, 33);
        System.out.println(deque.peekFront().amount);
        System.out.println(deque.peekBack().cost);  
    }
    //*Node Class. Has two elements: Amount and cost*//
     public static class Node
     {
        int amount;
        int cost;
        public Node next, prev;

        public Node(int amount, int cost)
        {
            this.amount = amount;
            this.cost = cost;
        }
     }
     //*Creating a Deque class*//
    public static class Deque
    {
      public static Node start;
      public static Node end;
      public int size = 0;
      
      //*Empty Deque constuctor*//
        public Deque()
        {
            start = null;
            end = null;
        }
        //*boolean if empty*//
        public boolean isEmpty()
        {
            return size == 0;
        }
        //*Peek Front Method. Only returns front value*//
        public Node peekFront()
        {
            if (start == null)
            {
                return null;
            }
            else
            {
                return start;
            }
        }
        //*Peek back method. Only returns back value*//
        public Node peekBack()
        {
            if (end == null)
            {
                return null;
            }
            else
            {
                return end;
            }
        }
    //*adding ints to the first node. Shares bought/Cost, respectively*//
        public void enqueueStart(int amount, int cost)
        {
            Node newNode = new Node(amount, cost);
            newNode.next = start;
            if (isEmpty()){
                start = newNode;
                end = newNode;
            }
            else{
                start.prev = newNode; 
                start = newNode;
            }
            size ++;   
        }
        //*adding to the last node*//
        public void enqueueEnd(int amount, int cost)
        {
            Node newNode = new Node(amount, cost);
            if (isEmpty())
            {
                end = newNode;
                start = newNode;
            }
            else
            {
                end.next = newNode;
                newNode.prev = end;
                end = newNode;
            }
            size++;
        }
        //*removing first node*//
        public void dequeueFirst()
        {
         if (isEmpty())
        {
            System.out.println("Node empty, nothing to remove.");
                 
        }
         else if(size == 1)
         {
             start = null;
             end = null;
         }
         else
         {
           start = start.next;
           start.prev = null;
         }
         size --;
        }
        
        //*removing last node*//
        public void dequeueLast()
        {
            if (isEmpty())
            {
                System.out.println("Node empty, nothing to remove.");
            }
            else if(size == 1)
            {
                end = null;
                start = null;
            }
            else
            {
             end = end.prev;
             end.next = null;
            }
        }         
    }
}
