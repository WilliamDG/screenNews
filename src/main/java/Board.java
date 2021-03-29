
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    
    //private ArrayList<News> news = new ArrayList<News>();
    private int newsNumber;
    private int newsCenter;
    private int newsCounter;
    
    private GSon json;
    private Images images;
    
    public Board(BoardSettings bs, GSon json, Images images){

        this.boardSettings = bs;
        this.json = json;
        this.images = images;
        
        this.newsCounter = 0;
        // boardSettings.getB
       
        //setBackground(boardSettings.getBackgroundColor1()); 
        setPreferredSize(boardSettings.getBoardSize());
        


        


        
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
        //LOAD NEWS here
        json.readJsonAndLoadIt();       
        
        

        
        //Set up news on screen
        if(json.getNews().isEmpty()){
            System.err.print("No news found!");
            //boardThread = null;
            return;
        }
        
        setUpStrings(); //Called here and on Screen Resize Listener

        
        
        
        boardThread = new Thread(this);
        boardThread.setPriority(Thread.MAX_PRIORITY);
        boardThread.start();                                                                //start the thread at the end
    }
    
    
    
    
    
    @Override
    public void run() {
        while(true){
            
            //System.out.println(images.getCurrentImageNumber());
            
            if(this.getNewsCounter() < json.getNewsCycleCount()){                           //News
                move();                                                             
                try{
                    Thread.sleep(json.getSpeed());
                }catch(InterruptedException ex){}      
            }
            else{                                                                           //Images
                                                                 
                if(images.getCurrentImageNumber() >= images.getImagesCount()){
                    setNewsCounter(0); 
                    images.setCurrentImageNumber(0);
                    continue;
                }                    
                else{    
                    try{
                        Thread.sleep(json.getImagesSpeed());
                    }catch(InterruptedException ex){} 
                    
                }
                images.setCurrentImageNumber(images.getCurrentImageNumber()+1);
            }
            
            
            repaint();  
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(this.getNewsCounter() < json.getNewsCycleCount()){  //show news
            if(json.getNews().isEmpty())
                return;

            for(int i = 0; i < json.getNews().size(); ++i){

                if(i%2 == 0){
                    g.setColor(Color.decode(json.getBackgroundColor1()));
                    g.fillRect(0, newsCenter * ((i*2)+1)-newsCenter, boardSettings.getBoardSize().width, newsCenter * ((i*2)+1)+newsCenter);
                }
                else{
                    g.setColor(Color.decode(json.getBackgroundColor2()));
                    g.fillRect(0, newsCenter * ((i*2)+1)-newsCenter, boardSettings.getBoardSize().width, newsCenter * ((i*2)+1)+newsCenter);
                }


                g.setColor(json.getNews().get(i).getColor());
                g.setFont(json.getNews().get(i).getFont());
                g.drawString(json.getNews().get(i).getText(), json.getNews().get(i).getX(), json.getNews().get(i).getY());


                //Check the string size
                json.getNews().get(i).setWidth(g.getFontMetrics().stringWidth(json.getNews().get(i).getText()));

            }
        }
        else    //show images
        {
            //BufferedImage image = null;
            //try {
                //image = ImageIO.read(new File("imgs\\wallpaper2.jpg"));
                //BufferedImage resizedImage = new BufferedImage(boardSettings.getBoardSize().width, boardSettings.getBoardSize().height, image.TYPE_INT_RGB);
                //g.drawImage(image, 0, 0, boardSettings.getBoardSize().width, boardSettings.getBoardSize().height, this);
                //System.out.println(images.getImagesArray().get(0).toString());
            //} catch (IOException ex) {}
            if(images.getCurrentImageNumber() < images.getImagesCount())
                g.drawImage(images.getImagesArray().get(images.getCurrentImageNumber()), 0, 0, boardSettings.getBoardSize().width, boardSettings.getBoardSize().height, this);
        }
    }
    
    
    
    public void move(){
        if(json.getNews().isEmpty())
            return;
            
        for(int i = 0; i < json.getNews().size(); ++i){    
            json.getNews().get(i).setX(json.getNews().get(i).getX()-1);
            if(json.getNews().get(i).getX() + json.getNews().get(i).getWidth() <= 0){
                json.getNews().get(i).setEnd(true);
                //System.out.println("Stringa " + i + " FINITA");
                
            }
        }
        if(allNewsEnded())
            restartNews();
        //System.out.println(allNewsEnded());
        
        
   }
    
    
    
    
    
    
    
    
    
    
   
    private boolean allNewsEnded(){
        boolean result = true;
       
        for(int i = 0; i < json.getNews().size(); ++i){ 
            if(!json.getNews().get(i).isEnd())
               result = false; 
        }   
       
        return result;
    }
   
    private void restartNews(){
        for(int i = 0; i < json.getNews().size(); ++i){ 
            json.getNews().get(i).setEnd(false);
            json.getNews().get(i).setX(boardSettings.getBoardSize().width);
            //System.out.println( news.get(i).getX());
        }  
        this.setNewsCounter(this.getNewsCounter() + 1);
        return;
    }
    
    private void setUpStrings(){
        
        if(json.getNews().isEmpty())
            return;
        
        this.newsNumber = json.getNews().size();  
        this.newsCenter = (boardSettings.getBoardSize().height / newsNumber) / 2;
        for(int i = 0; i < json.getNews().size(); ++i){
            json.getNews().get(i).setX(boardSettings.getBoardSize().width);
            //news.get(i).setY(newsCenter * ((i*2)+1));
            json.getNews().get(i).setY(newsCenter * ((i*2)+1) + json.getNews().get(i).getFont().getSize()/4);       //FontSize / 4 ---> Center the string in Y axis
        }
        return;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @return the newsCounter
     */
    public int getNewsCounter() {
        return newsCounter;
    }

    /**
     * @param newsCounter the newsCounter to set
     */
    public void setNewsCounter(int newsCounter) {
        this.newsCounter = newsCounter;
    }
}
