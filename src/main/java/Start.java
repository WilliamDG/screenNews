
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class Start extends JFrame implements KeyListener{
    
    private BoardSettings boardSettings;
    
    public Start(){
        
        boardSettings = new BoardSettings();
        
        Board b = new Board(boardSettings);
        this.add(b);
        
        
        //For key listener
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
    }
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String fileName = "settings.json";
        GSon json = new GSon(fileName);
        json.createOrLoadFile();
        if(!json.isFileOk){
            System.err.print("Error with " + fileName);
            return;
        }
        
        

        
        
        
        
        
        JFrame jf = new Start();
        //jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //jf.setLocationRelativeTo(null); 
        //jf.setUndecorated(true);

        
        //set size for the system
        jf.pack();
        
        jf.setVisible(true);     
        jf.setTitle("NEWS");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
   
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F){
            //System.out.println("FULL SCREEN");
            
            this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            //this.setLocationRelativeTo(null);
            this.dispose();
            this.setUndecorated(true);
            this.setVisible(true); 
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            //System.out.println("Normal SCREEN");
            
            this.setExtendedState(JFrame.NORMAL); 
            //this.setLocationRelativeTo(null);
            this.dispose();
            this.setUndecorated(false);
            this.pack();
            this.setVisible(true); 
        }
    }
    
}
