import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;

public class TicTacToeTwoD extends JPanel
{
   JButton buttons[][] = new JButton[3][3]; 
   int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O

   public TicTacToeTwoD()
   {
     setLayout(new GridLayout(3,3));
     initializeButtons(); 
   }

   public void initializeButtons()
   {
       for(int i = 0; i < 3; i++)
       {
         for(int j = 0; j < 3; j++){
           buttons[i][j] = new JButton();
           buttons[i][j].setText("");
           buttons[i][j].addActionListener(new buttonListener());
           buttons[i][j].setFont(new Font ("Arial", Font.BOLD, 60));

           add(buttons[i][j]); //adds this button to JPanel (note: no need for JPanel.add(...)
                               //because this whole class is a JPanel already  
         }
       }
   }
   public void resetButtons()
   {
     alternate = 0;
       for(int i = 0; i < 3; i++)
       {
         for(int j = 0; j < 3; j++)
           buttons[i][j].setText("");
       }
   }

// when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
   private class buttonListener implements ActionListener
   {

       public void actionPerformed(ActionEvent e) 
       {
           JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
           if (buttonClicked.getText().length() > 0)
           {
             JOptionPane.showMessageDialog(null, "You can't do that!", 
                                             "Yo, Doofus!!", JOptionPane.WARNING_MESSAGE);
           }
           else if (alternate%2 == 0)
           {
               buttonClicked.setText("X");
               buttonClicked.setForeground(Color.BLUE);
               alternate++;
           }
           else
           {
               buttonClicked.setText("O");
               buttonClicked.setForeground(Color.GREEN);
               alternate++;
           }


           if(checkForWin() == true && alternate % 2 != 0)
           {
               JOptionPane.showConfirmDialog(null, "\'X\' Wins!", 
                                             "Click OK", JOptionPane.DEFAULT_OPTION);
               resetButtons();
           }
           
           else if(checkForWin() == true && alternate % 2 == 0)
           {
               JOptionPane.showConfirmDialog(null, "\'O\' Wins!", 
                                             "Click OK", JOptionPane.DEFAULT_OPTION);
               resetButtons();
           }
           
           else if (checkForTie() == true) {
             JOptionPane.showConfirmDialog(null, "Game Over. Tie Game.", 
                                             "Click OK", JOptionPane.DEFAULT_OPTION);
               resetButtons();
           }
       }

       public boolean checkForWin()
       {
           /**   Reference: the button array is arranged like this as the board
            *      0 | 1 | 2
            *      3 | 4 | 5
            *      6 | 7 | 8
            */
           //horizontal win check
           if( checkAdjacent(0,0,0,1) && checkAdjacent(0,1,0,2))  //no need to put " == true" because the default check is for true
               return true;
           else if( checkAdjacent(1,0,1,1) && checkAdjacent(1,1,1,2) )
               return true;
           else if ( checkAdjacent(2,0,2,1) && checkAdjacent(2,1,2,2))
               return true;

           //vertical win check
           else if ( checkAdjacent(0,0,1,0) && checkAdjacent(1,0,2,0))
               return true;  
           else if ( checkAdjacent(0,1,1,1) && checkAdjacent(1,1,2,1))
               return true;
           else if ( checkAdjacent(0,2,1,2) && checkAdjacent(1,2,2,2))
               return true;

           //diagonal win check
           else if ( checkAdjacent(0,0,1,1) && checkAdjacent(1,1,2,2))
               return true;  
           else if ( checkAdjacent(0,2,1,1) && checkAdjacent(1,1,2,0))
               return true;
           else 
               return false;


       }
       
       public boolean checkForTie() {
         if(checkForWin() == false && alternate == 9)
           return true;
         return false;
       }

       public boolean checkAdjacent(int a, int c, int b, int d)
       {
           if ( buttons[a][c].getText().equals(buttons[b][d].getText()) && !buttons[a][c].getText().equals("") )
               return true;
           else
               return false;
       }

   }

   public static void main(String[] args) 
   {
       JFrame window = new JFrame("Tic-Tac-Toe");
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       window.getContentPane().add(new TicTacToeTwoD());
       window.setBounds(300,200,300,300);
       window.setVisible(true);
   }
}