
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
public class Images {

    private String folderName;
    private Path folderNamePath;
    private long imagesCount;
    
    private int currentImageNumber;
    
    List imagesList;
    private ArrayList<BufferedImage> imagesArray = new ArrayList<BufferedImage>();
    
    public Images(String folderName){
        this.folderName = folderName;
        this.imagesList = null;
        this.currentImageNumber = 0;
    }
    
    public void checkFolder(){
        folderNamePath = Paths.get(folderName);                  //Java 8
        if (Files.exists(folderNamePath)) {
            //System.out.println("Esiste!!!!!! IMG");
        }
        else{
            //System.out.println("Creo... IMG");
            try{
                Files.createDirectories(folderNamePath);
            }
            catch(Exception ex){
                System.err.println("Error: can't create folder imgs");
            }
        }
        imagesCount();
    }
    
    
    public void imagesCount(){
        try{
            setImagesCount(Files.list(folderNamePath).filter(f -> f.toFile().isFile()).count());
        }
        catch(Exception ex){}
        
        //System.out.println("num:" + imagesCount);
         
    }
    
    public void imagesLoad(){
        try{
            imagesList = Files.list(folderNamePath)
                    .filter(Files::isRegularFile)                   //f -> f.toFile().isFile()
                    .map(Path::getFileName).map(Path::toString)     //.map(p -> p.getFileName().toString())
                    .collect(Collectors.toList());
        }
        catch(Exception ex){} 
        
        //System.out.println(imagesList.get(1).toString());
        //System.out.println(folderNamePath.toString());
        
        imagesLoadArray();
    }
    
    public void imagesLoadArray(){
        
        for(int i = 0; i < getImagesCount(); i++){
            
            try{
                //System.out.println("start");
                getImagesArray().add(ImageIO.read(new File(folderName + "\\" + imagesList.get(i).toString())));
                //System.out.println("load");
            }
            catch(Exception ex){} 
        }
        //System.out.println(folderName + "\\" + imagesList.get(0).toString());
    }
    
    
    //public void resetNewsCounterCycle(Board b){
        //b.setNewsCounter(0);
        //System.out.println("reset");
    //}

    
    
    /**
     * @return the imagesArray
     */
    public ArrayList<BufferedImage> getImagesArray() {
        return imagesArray;
    }

    /**
     * @param imagesArray the imagesArray to set
     */
    public void setImagesArray(ArrayList<BufferedImage> imagesArray) {
        this.imagesArray = imagesArray;
    }
     
    /**
     * @return the currentImageNumber
     */
    public int getCurrentImageNumber() {
        return currentImageNumber;
    }

    /**
     * @param currentImageNumber the currentImageNumber to set
     */
    public void setCurrentImageNumber(int currentImageNumber) {
        this.currentImageNumber = currentImageNumber;
    }

    /**
     * @return the imagesCount
     */
    public long getImagesCount() {
        return imagesCount;
    }

    /**
     * @param imagesCount the imagesCount to set
     */
    public void setImagesCount(long imagesCount) {
        this.imagesCount = imagesCount;
    }
}
