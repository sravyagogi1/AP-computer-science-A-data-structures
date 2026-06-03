//Name: Sravya Gogineni
//Date: 1/28/25

import java.util.*;
import java.io.*;
public class CustomersInQueue
{
   public static final double CHANCE_OF_CUSTOMER = 0.3; // play with this value

   public static PrintWriter setUpFile() // file to display the queue information
   {
      PrintWriter outfile = null; 
      try
      {
         outfile = new PrintWriter(new FileWriter("customersSimulation.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      return outfile;
   }
   
   public static void outfileCustomerServed(PrintWriter outfile, Customer c, int wait, int total)
   {   
      outfile.println("           Depart  " + c + ", wait time was "+ wait + " minutes.  Total time so far: "+total+"\n");                    	
   }
   
   public static void outfileQueue(PrintWriter outfile, int min, Queue<Customer> queue)
   { 
      outfile.println("minute " + min + ": Serving " + queue.peek() + ". Queue " + queue.toString() );
   }
       
   public static double calculateAverage(int totalMinutes, int customers)
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
            
   public static void main(String[] args)
   {     
      PrintWriter outfile = setUpFile();      
      System.out.println("Customers in a Queue Simulation! ");
      Scanner kb = new Scanner(System.in);
      System.out.print("How long, in minutes, should the simulation run? ");
      int duration = kb.nextInt();
      
      serveTheCustomers(duration, outfile);  //run the simulation
      System.out.print("Simulation done.");
   }
   
   public static void serveTheCustomers(int duration, PrintWriter outfile)
   {
      //cumulative variables
      int customersServed = 0;
      int totalMinutes = 0;
      int longestWaitTime = 0;
      int longestQueue = 0;
          
     /***************************************
           Write your code for the simulation.
           call outfileCustomerServed() to record the departure of a Customer.
           call outfileQueue() to record the queue of Customers.  
      **********************************/   
     
      Queue<Customer> queue = new LinkedList<>();
      Customer person = null; //queue
     
      int count = 0;
      while(totalMinutes<duration){
         if(Math.random()<CHANCE_OF_CUSTOMER){
            Customer newC = new Customer(count);
            queue.add(newC); //add newC
         }
      
         if(person==null && !queue.isEmpty()){ //check if empty
            person = queue.remove();
         }
      
         if(person != null){
            person.countDown(); //counting down
            if(person.getTimeAtFront()<=0){
               int waitTime = totalMinutes - person.getArrivalTime();
               totalMinutes += waitTime;
               longestWaitTime = Math.max(waitTime,longestWaitTime);
               customersServed++;
               outfileCustomerServed(outfile,person,waitTime,totalMinutes);
               person = null;
            }
         }
      
         longestQueue = Math.max(longestQueue, queue.size());
         outfileQueue(outfile,totalMinutes,queue);
         totalMinutes++;
         count++;
      } 
     
      /*   output the calculations to the file   */  
      outfile.println("\nTotal customers served = " + customersServed);
      outfile.println("Average wait time = " + calculateAverage(totalMinutes, customersServed));
      outfile.println("Longest wait time = " + longestWaitTime);
      outfile.println("Longest queue = " + longestQueue);
      outfile.close();
   }
   
   static class Customer      
   {
      private int arrivedAt;     //time the customer joined the queue
      private int timeAtFront;   //the time spent at the cashier 
      public Customer(int time)
      {
         arrivedAt = time;
         timeAtFront = (int)(Math.random()*7+2);
      }
      public int getArrivalTime()
      {
         return arrivedAt;
      }
      public int getTimeAtFront()
      {
         return timeAtFront;  
      }
      public void countDown()
      {
         timeAtFront--;
      }
      public String toString()
      {
         return timeAtFront +"-Customer:"+arrivedAt;
      }
   }
}

/*************** Sample Run, saved to the file ****************************
minute 1: Serving null. Queue []
minute 2: Serving null. Queue []
minute 3: Serving 4-Customer:3. Queue [4-Customer:3]
minute 4: Serving 3-Customer:3. Queue [3-Customer:3]
minute 5: Serving 2-Customer:3. Queue [2-Customer:3]
minute 6: Serving 1-Customer:3. Queue [1-Customer:3]
           Depart  0-Customer:3, wait time was 4 minutes.  Total time so far: 4

minute 7: Serving null. Queue []
minute 8: Serving null. Queue []
minute 9: Serving 2-Customer:9. Queue [2-Customer:9]
minute 10: Serving 1-Customer:9. Queue [1-Customer:9]
           Depart  0-Customer:9, wait time was 2 minutes.  Total time so far: 6

minute 11: Serving 3-Customer:11. Queue [3-Customer:11]
minute 12: Serving 2-Customer:11. Queue [2-Customer:11, 2-Customer:12]
minute 13: Serving 1-Customer:11. Queue [1-Customer:11, 2-Customer:12, 3-Customer:13]
           Depart  0-Customer:11, wait time was 3 minutes.  Total time so far: 9

minute 14: Serving 2-Customer:12. Queue [2-Customer:12, 3-Customer:13]
minute 15: Serving 1-Customer:12. Queue [1-Customer:12, 3-Customer:13, 5-Customer:15]
           Depart  0-Customer:12, wait time was 5 minutes.  Total time so far: 14

minute 17: Serving 3-Customer:13. Queue [3-Customer:13, 5-Customer:15]
minute 18: Serving 2-Customer:13. Queue [2-Customer:13, 5-Customer:15]
minute 19: Serving 1-Customer:13. Queue [1-Customer:13, 5-Customer:15]
           Depart  0-Customer:13, wait time was 7 minutes.  Total time so far: 21

minute 20: Serving 5-Customer:15. Queue [5-Customer:15]
minute 21: Serving 4-Customer:15. Queue [4-Customer:15]
minute 22: Serving 3-Customer:15. Queue [3-Customer:15]
minute 23: Serving 2-Customer:15. Queue [2-Customer:15]
minute 24: Serving 1-Customer:15. Queue [1-Customer:15]
           Depart  0-Customer:15, wait time was 10 minutes.  Total time so far: 31

minute 25: Serving null. Queue []

Total customers served = 6
Average wait time = 5.1
Longest wait time = 10
Longest queue = 3   *************************************/
