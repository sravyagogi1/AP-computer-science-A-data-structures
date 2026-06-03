// Name: Sravya Gogineni
// Date: 10/8/24
  
import java.util.*;
public class Fibonacci
{
   public static void main(String[] args)
   {
      long start, end, fib; //why long?
      int lastFibNumber = 43;
      int[] fibNumber = {1};
      System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
      for(int n = fibNumber[0]; n <= lastFibNumber; n++)
      { 
         start = System.nanoTime();
         fib = fibIterate(n);
         end = System.nanoTime();
         System.out.print("\t\t" + n + "\t\t\t" + fib + "\t\t\t\t\t\t" + (end-start)/1000.);
         start = System.nanoTime();   	
         fib = fibRecur(n);      
         end = System.nanoTime();
         System.out.println("\t\t\t" + fib + "\t\t\t\t" + (end-start)/1000.);
      }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
      if(n==0)
         return 0;
      if(n==1)
         return 1;

      long num1 = 0, num2 = 1, fib = 0;
      
      for(int i=2; i<=n; i++){
         fib = num1+num2;
         num1 = num2;
         num2 = fib;
      }
      return num2;
   }

   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibRecur(int n)
   {
      if(n==0)
         return 0;
      if(n==1)
         return 1;
      return fibRecur(n-2) +fibRecur(n-1);  
         
   }
}