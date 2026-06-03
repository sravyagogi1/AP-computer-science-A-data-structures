// Name: Sravya Gogineni
// Date: 2/11/25
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;
 
public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   
   {
      return root;
   }
    
   public void buildTree(String str)
   {
     	Stack<TreeNode> stk = new Stack<>();
      String[] array = str.split(" ");
      
      for(String i:array){
         TreeNode temp = new TreeNode(i);
         if(isOperator(i)){
            if(i.equals("!"))
               temp.setLeft(stk.pop());
            else{
               temp.setRight(stk.pop());
               temp.setLeft(stk.pop());
            }
         }
         stk.push(temp);
      }
      root = stk.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t==null)
         return 0;
         
      double l = evaluateNode(t.getLeft());
      double r = evaluateNode(t.getRight());
      String tval = (String)t.getValue();
      
      if(!isOperator(tval)){
         return Double.parseDouble(tval);
      }
      if(isOperator(tval))
         if(tval.equals("!")){
            double sum = 1;
            for(int j=1; j<=l; j++)
               sum *= j;
            return sum;
         }
         return computeTerm(tval,l,r); 
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+"))
         return a + b;
      
      if(s.equals("-"))
         return a - b;
         
      if(s.equals("*"))
         return a * b;
         
      if(s.equals("/"))
         return a / b;
         
      if(s.equals("%"))
         return a % b;
         
      if(s.equals("^"))
         return Math.pow(a,b); 
         
      return 1; //for compile   
   }
   
   private boolean isOperator(String s)
   {
      if(operators.contains(s))
         return true;
      else
         return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t==null)
         return "";
      toReturn += inorderTraverse(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";             //process root
      toReturn += inorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //process root
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
   public String inorderTraverseWithParentheses()
   {
      return inorderTraverseWithParentheses(root);
   }
      
   private String inorderTraverseWithParentheses(TreeNode t)
   {
      //String toReturn = "";
      if(t==null)
         return "";
         
      String tval = (String)t.getValue();
      if(!isOperator(tval))
         return tval;
      
      String l = inorderTraverseWithParentheses(t.getLeft());
      String r = inorderTraverseWithParentheses(t.getRight());
      
      return "(" + l + " " + tval + " " + r + ")";
   }
}