// Name: Sravya Gogineni    Date: 10/22/24

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class NQueens extends JPanel
{
   // Instance Variables: Encapsulated data for EACH NQueens problem
   private JButton[][] board;
   private int N;
   JSlider speedSlider;
   private int timerDelay;

   public NQueens(int n)
   {
      String boardSize = JOptionPane.showInputDialog("Enter the board size:");
      
      N = Integer.parseInt(boardSize);
      
      this.setLayout(new BorderLayout());
   
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      JLabel label = new JLabel( N + "Queens solution");
      north.add(label);
      
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(N,N));
      add(center, BorderLayout.CENTER);
      board = new JButton[N][N];
      for(int r = 0; r < N; r++)
         for(int c = 0; c < N; c++)
         {
            board[r][c] = new JButton();
            board[r][c].setOpaque(true);   //for Mac computers
            board[r][c].setBackground(Color.blue);
            center.add(board[r][c]);
         }
   
      speedSlider = new JSlider();   
      speedSlider.setInverted(true);   
      add(speedSlider, BorderLayout.SOUTH);   
   }

   /** Returns the number of queens to be placed on the board. **/
   public int numQueens()
   {
      return N;   
   }

    /** Solves (or attempts to solve) the N Queens Problem. **/
   public boolean solve()
   {
      return isPlaced(0,0);
   }

 /**
  * Iteratively try to place the queen in each column.
  * Recursively try the next row.
  **/       
   private boolean isPlaced(int row, int col)
   {
      if(row == N)   //matrix is filled
         return true;
      if(col == N)
         return false;
         
      if(locationIsOK(row,col)==true){
         addQueen(row,col);
         if(isPlaced(row+1,0))
            return true;
         removeQueen(row,col);
      }
      return isPlaced(row,col+1);
   }
      /*  enter code here  */
      
   /*
      for each col
         if location is ok?
            addQueen(row,col)
            res = isPlaced(row + 1)
            if res is false: removeQ(row,col)
            else return true
       return false      
   */
   
  /** Verify that another queen can't attack this location.
    * You only need to check the locations above, above to the right, and above to the left.
    * Iteration is fine here.
    **/
   private boolean locationIsOK(int r, int c)
   {
      /*  enter code here  */
      for(int i=0; i<N; i++){
         if(r-i >= 0 && board[r-i][c].getBackground() == Color.RED)
            return false;
         if(r-i >= 0 && c+i < N && board[r-i][c+i].getBackground() == Color.RED)
            return false;
         if(r-i >= 0 && c-i >= 0 && board[r-i][c-i].getBackground() == Color.RED)
            return false;
      }
      return true;
   }

    /** Adds a queen to the specified location.
       **/
   private void addQueen(int r, int c)
   {
      board[r][c].setBackground(Color.RED);
      pause();
   }

    /** Removes a queen from the specified location.
     **/
   private void removeQueen(int r, int c)
   {
      board[r][c].setBackground(Color.BLUE);
      pause();
   }
   private void pause()
   {
      int timerDelay = speedSlider.getValue(); 
      for(int i=1; i<=timerDelay*1E7; i++)  {}
   }
}