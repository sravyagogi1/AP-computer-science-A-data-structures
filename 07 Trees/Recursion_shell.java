// Sravya Gogineni 
// 3/24/25

public class Recursion_shell
{
   public static void main(String[] args)
   {
      String abc = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
      char[] let = abc.toCharArray();
      int n = let.length;
      for(int j=0 ;j<let.length-1; j++){
         int k = (int)(Math.random() * n + j);
         if(k>j){
            char temp = let[j] ;
            let[j] = let[k] ;
            let[k] = temp ;
         }
         n-- ;
      }
      TreeNode root = null ;
      for(int j=0 ;j<10 ;j++){
         root = add(root, "" + let[j]);
      }
      System.out.println("Tree\n" + display(root,0));
      System.out.println("List");
      DLNode head = tree2list(root);
      DLNode p = head;
      do
      {
         System.out.print(p.getValue() + " ");
         p = p.getNext();
      } while(p != head);
      System.out.println();
   }

   public static DLNode tree2list(TreeNode t){
      if(t == null) 
         return null ;
      //
      // Cases to check
      // ==============
      // 1. t is a leaf
      // 2. t has only a left child
      // 3. t has only a right child
      // 4. t has both a left child and a right child
      //
      
      DLNode head = new DLNode(t.getValue(),null,null);
      //
      head.setNext(head);
      head.setPrev(head);
      //
      DLNode lList = tree2list(t.getLeft());
      DLNode rList = tree2list(t.getRight());
      DLNode lLast = leftList.getPrev();
      DlNode rLast = rightList.getPrev();
      
      if(lList!=null){
         lLast.setNext(head);
         head.setPrev(lLast);
         lList.getPrev().setNext(head);
         head.setPrev(lList.getPrev());
         head = lList;
      }
      
      if(rList!=null){
         head.getPrev().setNext(rLast);
         rList.setPrev(head.getPrev());
         rLast.setNext(head);
         head = setPrev(rLast);
      }
   
      return head;
   }
   
   public static TreeNode add(TreeNode t,String s){
      if(t==null) 
         return new TreeNode(s,null,null);
      if(s.compareTo("" + t.getValue())< 0){
         t.setLeft(add(t.getLeft(),s));
      }
      else {
         t.setRight(add(t.getRight(),s));
      }
      return t;
   }

   public static String display(TreeNode t,int m)
   {
      if(t==null) 
         return "";
      String s = "";
      s += display(t.getRight(),m+1);
      for(int j=0; j<m; j++) s += " ";
      s += t.getValue() + "\n";
      s += display(t.getLeft(), m + 1);
      return s ;
   }
}
//
// end
//