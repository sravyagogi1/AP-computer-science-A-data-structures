 //Name: Sravya Gogineni
 //Date: 4/2/25
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size()-1;
   }
   
   public boolean isEmpty()
   {
      if(myHeap.size()==1)
         return true;
      return false;
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
      if(isEmpty())
         return null;
      E t = myHeap.get(1);
      if(!isEmpty()){
         myHeap.set(1,myHeap.remove(lastIndex()));
         heapDown(1,lastIndex());
      }
      return t;
   }
   
   public E peek()
   {
      if(isEmpty())
         return null;
      return myHeap.get(1);
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
      while(k>1 && myHeap.get(k).compareTo(myHeap.get(k/2))<0){
         swap(k,k/2);
         k = k/2;
      }
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a,myHeap.get(b));
      myHeap.set(b,temp);
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {    
      while(2*k < myHeap.size()){
         int minC = 2*k;
         if(minC+1 < myHeap.size() && myHeap.get(minC+1).compareTo(myHeap.get(minC)) < 0)
            minC++;
         swap(k,minC);
         k = minC;
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}