import java.util.*;
public class Queue
{
   private ListNode first;
   private ListNode last;
   
   public Queue(){
      first = null;
      last = null;
   }
   
   public void add(Object obj){ //enqueues obj at the end of the queue
      ListNode returnNode = new ListNode(obj,null);
      if(isEmpty()){
         first = returnNode;
         last = returnNode;
      }   
      
      else{
         last.setNext(returnNode);
         last = returnNode;
      }
   }
      
   public Object remove(){  // dequeues and returns the element at the front; returns null if the queue is empty
      if(isEmpty())
         return null;
      Object temp = first.getValue();
      first = first.getNext();
      if(first==null)
         last = null;
      return temp;
   }
      
   public Object peek(){  // returns the element at the front of the queue; returns null if the queue is empty
      if(isEmpty())
         return null;
      return first.getValue();
   }
      
   public boolean isEmpty(){
      if(first==null)
         return true;
      return false;
   }
      
   public String toString(){ // returns a string of the elements enclosed in square brackets, separated by commas
      ListNode temp = first;
      String str = "[";
      if(isEmpty())
         return "[]";
      else{
         while(temp.getNext() != null){
            str += temp.getValue() + ", ";
            temp = temp.getNext();
         }
         return str + temp.getValue() + "]";
      }
   }
   
}