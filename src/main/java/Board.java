
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
    
    private int x = 700;
    private int y = 300;
    
    
    public Board(){
        setBackground(Color.gray);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        
        
        boardThread = new Thread(this);
        boardThread.setPriority(Thread.MAX_PRIORITY);
        
        
        boardThread.start();
        
    }
    
    
    
    
    
    @Override
    public void run() {
        while(true){
            move();
            
            try{
                Thread.sleep(5);
            }catch(InterruptedException ex){
                
            }
            repaint();       
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        Font f;
        f = new Font("TimesRoman", Font.PLAIN, 40);
        g.setFont(f);
        g.drawString("ciao", this.x, this.y);
    }
    
    
    
   public void move(){
       x--;
       
       
   }
    
    
}
