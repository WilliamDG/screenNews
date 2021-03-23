
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
public class Start extends JFrame {
    
    private BoardSettings boardSettings;
    
    public Start(){
        
        boardSettings = new BoardSettings();
        
        Board b = new Board(boardSettings);
        this.add(b);
  
    }
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        JFrame jf = new Start();
        //jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //jf.setLocationRelativeTo(null); 
        //jf.setUndecorated(true);
        
        //set size for the system
        jf.pack();
        
        jf.setVisible(true);     
        jf.setTitle("NEWS");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //System.out.println("TEST");
        //System.out.println("TEST");
    }
    
}
