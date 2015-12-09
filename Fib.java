package fib;
import java.util.Scanner;
public class Fib 
{
    public static void main(String[] args) 
    {
        //User input is taken to get an Nth term up to which will be printed out
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Nth term of the Fibonacci Sequence you wish"
                + " to know: ");
        int Nth = scan.nextInt();
        int[] array;
        
        //Made an array with a large size for simplicity.
        //Each new index will be an entry from the fibcheck() method.
        array = new int[5000];

        //This prints out the sequence up to the Nth term using a For loop.
         System.out.println("The sequence up to the "+Nth+"th term is: ");
         
        for(int i = 1; i<=Nth; i++)
        {
            array[i]=fibcheck(i);
            System.out.print(fibcheck(i)+ " ");
            
        }
        //Somewhat redundant, prints what the Nth Term is.
        System.out.println("\nThe "+Nth+"th term is: "+array[Nth]);
        
        //Will Print results from Pisano Period Calculating Method below.
        System.out.println("The " + Nth + "th Pisano Period is:");
        
        //This calculates the Pisano period of the Nth given number
        //Only calculates up to the 9th Period, as the 10th is 60 digits long
        //Terminates when the next 4 numbers are 0112.
        //This indicates the start of a new cycle.
         for(int i = 1; i<=24; i++)
        {
            if (i == 1)
            {
            System.out.print("0 ");
            }
            //Each index in the array is inserted into the fibcheck function
            array[i]=fibcheck(i);
            
             if(fibcheck(i)%Nth==0 && fibcheck(i+1)%Nth==1 && fibcheck(i+2)%Nth==1
                     && fibcheck(i+3)%Nth==2)
            {
                //Exit if next 4 digits are 0112 indicating end of cycle.
                System.exit(0); 
            }
            System.out.print(fibcheck(i)% Nth +" ");
        }
    }
    // This prints each index of the Fibonacci Sequence.
    //The first two are always 1, so there's an if statement for 1 & 2 to print 1.
    public static int fibcheck(int i)
    {
        if(i== 1 || i==2)
        {
            return 1;
        }
        else
        {
            return fibcheck(i-1) + fibcheck(i-2);
        }
    }
}
