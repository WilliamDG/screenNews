
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
public class Board extends JPanel implements Runnable {
    private Thread boardThread;
    
    private int B_WIDTH = 800;
    private int B_HEIGHT = 600;
    
    
    public Board(){
        setBackground(Color.gray);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        
        
        boardThread = new Thread(this);
        boardThread.setPriority(Thread.MAX_PRIORITY);
        
        
        boardThread.start();
        
    }
    
    
    
    
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
