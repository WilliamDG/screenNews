
import com.google.gson.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
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
    private int newsNumber;
    private int newsCenter;
    
    private GSon json;
    
    public Board(BoardSettings bs, GSon json){

        this.boardSettings = bs;
        this.json = json;
        
        // boardSettings.getB
       
        setBackground(boardSettings.getBackgroundColor1()); 
        setPreferredSize(boardSettings.getBoardSize());
        

        
        boardThread = new Thread(this);
        boardThread.setPriority(Thread.MAX_PRIORITY);
        boardThread.start();

        
        //Board Size listner 
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt){
                //boardSize.setSize(getSize());
                
                boardSettings.setBoardSize(getSize());
                //System.out.println(boardSettings.getBoardSize());
                setUpStrings();
            }
        });
        
        
        
      
        //////////////////////////////////
        //              JSON            //
        //////////////////////////////////
        
        /*System.out.println("News count: " + json.getNewsNumber());
        System.out.println(json.getNewsText(1));
        System.out.println(json.getNewsColor(1));
        System.out.println(json.getNewsFontName(1));
        System.out.println(json.getNewsFontType(1));
        System.out.println(json.getNewsFontSize(1));
        System.out.println(json.getSettingsBackGround(1));
        System.out.println(json.getSettingsSpeed());*/
        
        //LOAD Settings here
        this.boardSettings.setBackgroundColor1(Color.decode(json.getSettingsBackGround(1)));
        this.boardSettings.setBackgroundColor2(Color.decode(json.getSettingsBackGround(2)));
        this.boardSettings.setSpeed(json.getSettingsSpeed());
        
        //LOAD NEWS here
        int newsCount = json.getNewsNumber();
        for(int i = 1; i <= newsCount; ++i){
            news.add(new News(
                    json.getNewsText(i), 
                    json.getNewsColor(i), 
                    json.getNewsFontName(i), 
                    json.getNewsFontType(i), 
                    json.getNewsFontSize(i)
            ));
        }
        
        //Load news manually
        /*news.add(new News(this.json.getStr(), "#000000", "TimesRoman", Font.PLAIN, 20)); 
        news.add(new News("prova1", "#600ff0", "TimesRoman", Font.PLAIN, 30));
        news.add(new News("prova2", "#600ff0", "TimesRoman", Font.PLAIN, 40));
        news.add(new News("prova3", "#600ff0", "TimesRoman", Font.PLAIN, 80));
        news.add(new News("prova4", "#600ff0", "TimesRoman", Font.PLAIN, 60));
        news.add(new News("prova5 aiushuasi auih gfasuaf uias  fasu hfaus fauif au", "#600ff0", "TimesRoman", Font.PLAIN, 40));
        */
        
        
        
        
        
        
        //Set up news on screen
        if(news.isEmpty()){
            System.err.print("No news found!");
            return;
        }
        
        setUpStrings(); //Called here and on Screen Resize Listener

        
        
        

    }
    
    
    
    
    
    @Override
    public void run() {
        while(true){
            move();
            
            try{
                Thread.sleep(boardSettings.getSpeed());
                //Thread.sleep(0, 1);
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
            
            if(i%2 == 0){
                g.setColor(boardSettings.getBackgroundColor1());
                g.fillRect(0, newsCenter * ((i*2)+1)-newsCenter, boardSettings.getBoardSize().width, newsCenter * ((i*2)+1)+newsCenter);
            }
            else{
                g.setColor(boardSettings.getBackgroundColor2());
                g.fillRect(0, newsCenter * ((i*2)+1)-newsCenter, boardSettings.getBoardSize().width, newsCenter * ((i*2)+1)+newsCenter);
            }
            
            
            g.setColor(news.get(i).getColor());
            g.setFont(news.get(i).getFont());
            g.drawString(news.get(i).getText(), news.get(i).getX(), news.get(i).getY());
            
            
            //Check the string size
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
            news.get(i).setX(boardSettings.getBoardSize().width);
            //System.out.println( news.get(i).getX());
        }  
        return;
    }
    
    private void setUpStrings(){
        this.newsNumber = news.size();  
        this.newsCenter = (boardSettings.getBoardSize().height / newsNumber) / 2;
        for(int i = 0; i < news.size(); ++i){
            news.get(i).setX(boardSettings.getBoardSize().width);
            //news.get(i).setY(newsCenter * ((i*2)+1));
            news.get(i).setY(newsCenter * ((i*2)+1) + news.get(i).getFont().getSize()/4);       //FontSize / 4 ---> Center the string in Y axis
        }
        return;
    }
    
}
