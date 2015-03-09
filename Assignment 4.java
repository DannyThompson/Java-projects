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
        Deque stocks = new Deque();
        Scanner scan = new Scanner(System.in);
        String stocktrades = "";
        
        System.out.println("Enter the amount of stocks, their cost, and B for "
                + "buy, or S for sell. "
                + "Ex: 99 25 B");
        System.out.println("Enter 'quit' to quit");

        //While loop, taking the input.
        //If quit 
        while (stocktrades!= "quit"){
            System.out.println(">> ");
            stocktrades = scan.nextLine();
            if (stocktrades == "quit"){
                System.exit(0);
            }
         //delimiter that will eliminate spaces in the string
            String delim = "[ ]+";
        //the string is parsed into seperate "tokens"
            String[] tokens = stocktrades.split(delim);
            int amount = Integer.parseInt(tokens[0]);
            int cost = Integer.parseInt(tokens[1]);
            if (tokens[2].charAt(0) == 'B'){
                stocks.enqueueStart(amount, cost);
            }
            else
            {
               if (tokens[2].charAt(0)=='S')
               {
                    stocks = sellStocks(amount, cost, stocks);
               }
            }  
        }    
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
        public int stockTotal(){
            int total = 0;
            Node current = start;
            while(current!= null){
                total += current.amount;
                current = current.next;
            }
            return total;
        }
    }
    //Sell Method. Sells X amount of stocks for X cost.
    public static Deque sellStocks(int sellAmount, int sellCost, Deque stocks){
        //Determines the total amount of money made or lost from that days
        // sold stocks.
        int saleTotal = sellAmount * sellCost;
        
        //Initial amount stocks were bought for
        int Iamount = 0;
        
        //Capital gain is total money gained or lost. The difference between
        //saleTotal - Iamount
        int capitalGain = 0;
        
        //Determines if a sale was made
        boolean sale = true;
        
        //Sets a limit that only the amount of shares sold are calculated
        int limit = sellAmount;
        
        //If stocks are attempted to be sold, but none are owned
        if (stocks.isEmpty()){
            System.out.println("No stocks left to sell.");
            limit = 0;
            sale = false;
        }
        else if (stocks.stockTotal() < sellAmount){
            System.out.println("You don't own enough stocks! "
                    + "you currently have " +
                    stocks.stockTotal() + " left.");
            System.out.println("Choose a number in this range.");
            limit = 0;
            sale = false;
                    
        }
        while (limit > 0){
        if (stocks.start.amount-limit <=0){
            Iamount += stocks.start.amount * stocks.start.cost;
            stocks.start.amount -= limit;
            limit = Math.abs(stocks.start.amount);
            stocks.dequeueFirst();
        }
        else{
            Iamount += limit * stocks.start.cost;
            stocks.start.amount -= limit;
            limit = 0;
        }
        }
        if (sale){
            capitalGain = saleTotal - Iamount;
            if (capitalGain > 0){
                System.out.println("You made $" + capitalGain + "!");
            }
            else{
                System.out.println("You lost $"+ Math.abs(capitalGain));
            }
            
        }
        return stocks;
        
    }
     
}
