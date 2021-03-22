
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
    private BoardSettings boardSettings;
    
    
    private Thread boardThread;
    
    private int B_WIDTH;
    private int B_HEIGHT;
    
    private Dimension boardSize;
    
    private int x = 700;
    private int y = 300;
    
    
    public Board(){
        boardSettings = new BoardSettings();
        this.B_WIDTH = boardSettings.getB_WIDTH();
        this.B_HEIGHT = boardSettings.getB_HEIGHT();
        boardSize = new Dimension(this.B_WIDTH, this.B_HEIGHT);
        
        setBackground(Color.gray);
        
        setPreferredSize(boardSize);
        
        
        
        boardThread = new Thread(this);
        boardThread.setPriority(Thread.MAX_PRIORITY);
        
        
        boardThread.start();
        
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt){
                boardSize.setSize(getSize());
            }
        });
        
        
        
    }
    
    
    
    
    
    @Override
    public void run() {
        while(true){
            move();
            
            try{
                Thread.sleep(boardSettings.getSpeed());
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
        f = new Font("TimesRoman", Font.PLAIN, 60);
        g.setFont(f);
        String a = "ciao";
        g.drawString(a, this.x, this.y);
        
        int width = g.getFontMetrics().stringWidth(a);
        //System.out.println(width);
    }
    
    
    
   public void move(){
       x--;    
   }
    
    
}
