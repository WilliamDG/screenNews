
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
    
    public Start(){
        Board b = new Board();
        add(b);

        //set size for the system
        pack();

        setTitle("SCREEN");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        JFrame jf = new Start();
        jf.setVisible(true);
        
        System.out.println("TEST");
        System.out.println("TEST");
    }
    
}
