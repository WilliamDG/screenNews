
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
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
    
    private ArrayList<News> news = new ArrayList<News>();
   
    
    
    public Board(BoardSettings bs){

        this.boardSettings = bs;
        
        // boardSettings.getB
       
        setBackground(boardSettings.getBackgroundColor()); 
        setPreferredSize(boardSettings.getBoardSize());
        
        
        boardThread = new Thread(this);
        boardThread.setPriority(Thread.MAX_PRIORITY);
        boardThread.start();

        
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt){
                //boardSize.setSize(getSize());
                
                boardSettings.setBoardSize(getSize());
                //System.out.println(boardSettings.getBoardSize());
            }
        });
        
        
        
        
        //LOAD NEWS here
        news.add(new News("ciaoooo", 700, 300, "#000000", "TimesRoman", Font.PLAIN, 60)); 
        news.add(new News("test", 700, 500, "#600ff0", "TimesRoman", Font.PLAIN, 60));
        
        
        
        

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
        
        if(news.isEmpty())
            return;
            
        for(int i = 0; i < news.size(); ++i){
            g.setColor(news.get(i).getColor());
            g.setFont(news.get(i).getFont());
            g.drawString(news.get(i).getText(), news.get(i).getX(), news.get(i).getY());
            news.get(i).setWidth(g.getFontMetrics().stringWidth(news.get(i).getText()));
            //int width = g.getFontMetrics().stringWidth(a);
            //System.out.println(news.get(i).getWidth());
        }
        
        
    }
    
    
    
    public void move(){
        if(news.isEmpty())
            return;
            
        for(int i = 0; i < news.size(); ++i){    
            news.get(i).setX(news.get(i).getX()-1);
            if(news.get(i).getX() + news.get(i).getWidth() <= 0){
                news.get(i).setEnd(true);
                //System.out.println("Stringa " + i + " FINITA");
                
            }
        }
        if(allNewsEnded())
            restartNews();
        //System.out.println(allNewsEnded());
        
   }
    
    
   
    private boolean allNewsEnded(){
        boolean result = true;
       
        for(int i = 0; i < news.size(); ++i){ 
            if(!news.get(i).isEnd())
               result = false; 
        }   
       
        return result;
    }
   
    private void restartNews(){
        for(int i = 0; i < news.size(); ++i){ 
            news.get(i).setEnd(false);
            news.get(i).setX(boardSettings.getB_WIDTH());
        }  
        
    }
    
}
