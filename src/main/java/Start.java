
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
public class Start{
    
    private GSon json;
    private Images images;
    
    public Start(GSon json, Images images){
        this.json = json;
        this.images = images;
        
    }
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){

                                                           
        
        
        
        
        
        
        //////////////////////////////////////////
        //       LOADING EXTERNAL FILES         //
        //////////////////////////////////////////
        
        String fileName = "settings.json";
        GSon json = new GSon(fileName);
        json.checkFile();
        if(!json.isIsFileOk()){
            System.err.print("Error with " + fileName);
            return;
        }
        
        
        String folderName = "imgs";                                                                             //IMG DEFINE
        Images images = new Images(folderName);
        images.checkFolder();
        
        
        //////////////////////////////////////////
        //          Start MAIN Window           //
        //////////////////////////////////////////
        ProgressBar it = new ProgressBar(0, (int)images.getImagesCount());

        JFrame frame = new JFrame("Screen News");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        //frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frame.setContentPane(it);
        frame.setVisible(true);
    
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension(dim.width/10, dim.height/8));                              //Jframe dim
        frame.pack();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);  //center it to screen
        
        


        
        
        Thread prograssBarThread = new Thread(it);
        prograssBarThread.setPriority(Thread.MAX_PRIORITY);
        prograssBarThread.start();  
        
        
        
        
        
        
        
        
        images.imagesLoad(it);                                                                                   //IMG START LOAD
        //System.out.println("test");
        
        
        
           


        //////////////////////////////////////////
        //      Start the Screen News Board     //
        //////////////////////////////////////////
        
        JFrame jf = new BoardStart(json, images);
        //jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //jf.setLocationRelativeTo(null); 
        //jf.setUndecorated(true);

        
        //set size for the system
        jf.pack();
        //setLocation(dim.width/2-boardSettings.getBoardSize().width/2, dim.height/2-boardSettings.getBoardSize().height/2);  //center it to screen
        
        
        jf.setVisible(true);     
        jf.setTitle("NEWS");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
   
    }
    
}
