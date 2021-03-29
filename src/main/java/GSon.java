
import com.google.gson.*;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
        
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
public class GSon {

    private String strFileName;
    private Path fileName;
    private String str;
    
    private boolean isFileOk;

    
    private String defaultBackgroundColor1;
    private String defaultBackgroundColor2;
    private long defaultSpeed;
    private int defaultNewsCycleCount;
    private long defaultImagesSpeed;
    
    private String defaultText;
    private String defaultColor;
    private String defaultFont_name;
    private int defaultFont_type;
    private int defaultFont_size;
    
    
    
    
    
    //Read from json
    private String backgroundColor1;
    private String backgroundColor2;
    private long speed;
    private int newsCycleCount;
    private long imagesSpeed;
    
    private ArrayList<News> news = new ArrayList<News>();
    
    
    
    
    public GSon(String strFileName){
        this.strFileName = strFileName;
        this.isFileOk = false;

        //readFile();
        
        
        //DEFAULT JSON VALUES
        this.defaultBackgroundColor1 = "#808080";
        this.defaultBackgroundColor2 = "#424242";
        this.defaultSpeed = 1;
        this.defaultNewsCycleCount = 2;
        this.defaultImagesSpeed = 600;
        
        this.defaultText =  "Error reading the news text";
        this.defaultColor = "#000000";
        this.defaultFont_name = "TimesRoman";
        this.defaultFont_type = 0;   // 0 - PLAIN        1 - BOLD        2 - ITALIC
        this.defaultFont_size = 60;
 
        
        
        

    }

    
    

   
    
   

    
    
    
    //Used in Start.java to check if file exist, if it doesn't "settings.json" will be created an then read.
    public void checkFile(){
        File file = new File(this.strFileName);
        if( file.exists()){
            //System.out.println("Esiste");
            readFile();                                             //Read the file here
            this.setIsFileOk(true);
        }
        else{
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(writeDeafultFile());                       //Write defalt file values
                bw.flush();
                bw.close();
                //System.out.println("NON Esiste");
                
                this.setIsFileOk(true);
                checkFile();                                        //Once file is created then retry to read it
            } 
            catch (IOException e) {
            }          
            
            
        }
    }
    
    
    public String writeDeafultFile(){
        String output;
        output = 
                    "{\n" +
                    "\t\"settings\" : {\n" +
                    "\t\t\"backgroundColor1\": \t\"#808080\",\n" +
                    "\t\t\"backgroundColor2\": \t\"#424242\",\n" +
                    "\t\t\"speed\": \t\t\t\t1,\n" +
                    "\t\t\"newsCycleCount\": \t\t3,\n" +
                    "\t\t\"imagesSpeed\": \t\t\t2000\n" +
                    "\t},\n" +
                    "\t\"news\" : {\n" +
                    "\t\t\"1\" : {\n" +
                    "\t\t\t\"text\" : \t\t\t\"My 1st news\",\n" +
                    "\t\t\t\"color\" : \t\t\t\"#ffffff\",\n" +
                    "\t\t\t\"font_name\" : \t\t\"TimesRoman\",\n" +
                    "\t\t\t\"font_type\" : \t\t0,\n" +
                    "\t\t\t\"font_size\" : \t\t60\n" +
                    "\t\t},\n" +
                    "\t\t\"2\" : {\n" +
                    "\t\t\t\"text\" : \t\t\t\"My 2nd news\"\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}"
        ;
        return output;
    }
    
    public void readFile(){
        //JSON
        try {
            //fileName = Path.of(strFileName);                  //Java 7
            fileName = Paths.get(strFileName);                  //Java 8
        }
        catch(Exception ex){
        }
        
        try {
            //str = Files.readString(fileName);                 //Java 7
            str = new String(Files.readAllBytes(fileName));     //Java 8
            
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void readJsonAndLoadIt(){
        this.setBackgroundColor1(this.getSettingsBackGround(1));
        this.setBackgroundColor2(this.getSettingsBackGround(2));
        this.setSpeed(this.getSettingsSpeed());
        this.setNewsCycleCount(this.getSettingsNewsCycleCount());
        this.setImagesSpeed(this.getSettingsImagesSpeed());
        
        
        int newsCount = this.getNewsNumber();
        for(int i = 1; i <= newsCount; ++i){
            getNews().add(new News(
                    this.getNewsText(i), 
                    this.getNewsColor(i), 
                    this.getNewsFontName(i), 
                    this.getNewsFontType(i), 
                    this.getNewsFontSize(i)
            ));
        }
        
        //System.out.println(news.get(1).getText());
    }
    
    
    
    
    
    
    
    
    
    
    public int getNewsNumber(){
        int output = 0;
    
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);

            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("news");
            output = jsonObject2.size();
        
        return output;
    }
    
    
    public String getNewsText(int i){
        String output = this.defaultText;
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
        
            /*JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("userJson");
            System.out.println(jsonObject2.size());
            String name = jsonObject2.get("surname").getAsString();
            System.out.println(name);*/
   
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("news");
            JsonObject jsonObject3 = jsonObject2.getAsJsonObject(""+i);
            output = jsonObject3.get("text").getAsString();
            
            
            //System.out.println(jsonObject3.size());
        }
        catch(Exception ex){  
        } 
        return output;
    }
    
    public String getNewsColor(int i){
        String output = this.defaultColor;
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("news");
            JsonObject jsonObject3 = jsonObject2.getAsJsonObject(""+i);
            output = jsonObject3.get("color").getAsString();
            
        }
        catch(Exception ex){  
        } 
        return output;
    }
    
    public String getNewsFontName(int i){
        String output = this.defaultFont_name;
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("news");
            JsonObject jsonObject3 = jsonObject2.getAsJsonObject(""+i);
            output = jsonObject3.get("font_name").getAsString();
            
        }
        catch(Exception ex){  
        } 
        return output;
    }
    
    public int getNewsFontType(int i){
        int output = this.defaultFont_type;
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("news");
            JsonObject jsonObject3 = jsonObject2.getAsJsonObject(""+i);
            output = jsonObject3.get("font_type").getAsInt();
            
        }
        catch(Exception ex){  
        } 
        return output;
    }
    
    public int getNewsFontSize(int i){
        int output = this.defaultFont_size;
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("news");
            JsonObject jsonObject3 = jsonObject2.getAsJsonObject(""+i);
            output = jsonObject3.get("font_size").getAsInt();
            
        }
        catch(Exception ex){  
        } 
        return output;
    }    
    
    public String getSettingsBackGround(int i){
        String output;     
        if(i%2 == 0)
            output = this.defaultBackgroundColor2;
        else
            output = this.defaultBackgroundColor1;
        
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("settings");
            output = jsonObject2.get("backgroundColor"+i).getAsString();         
        }
        catch(Exception ex){  
        } 
        return output;
    }
    
    public long getSettingsSpeed(){
        long output = this.defaultSpeed;
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("settings");
            output = jsonObject2.get("speed").getAsLong();         
        }
        catch(Exception ex){  
        } 
        return output;
    }
    
    
    public int getSettingsNewsCycleCount(){
        int output = this.defaultNewsCycleCount;
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("settings");
            output = jsonObject2.get("newsCycleCount").getAsInt();         
        }
        catch(Exception ex){  
        } 
        return output;
    }
    
    
    public long getSettingsImagesSpeed(){
        long output = this.defaultImagesSpeed;
        
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("settings");
            output = jsonObject2.get("imagesSpeed").getAsLong();         
        }
        catch(Exception ex){  
        } 
        return output; 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @return the newsCycleCount
     */
    public int getNewsCycleCount() {
        return newsCycleCount;
    }

    /**
     * @param newsCycleCount the newsCycleCount to set
     */
    public void setNewsCycleCount(int newsCycleCount) {
        this.newsCycleCount = newsCycleCount;
    }
    
    /**
     * @return the str
     */
    public String getStr() {
        return str;
    }

    /**
     * @param str the str to set
     */
    public void setStr(String str) {
        this.str = str;
    }
    
    /**
     * @return the backgroundColor1
     */
    public String getBackgroundColor1() {
        return backgroundColor1;
    }

    /**
     * @param backgroundColor1 the backgroundColor1 to set
     */
    public void setBackgroundColor1(String backgroundColor1) {
        this.backgroundColor1 = backgroundColor1;
    }

    /**
     * @return the backgroundColor2
     */
    public String getBackgroundColor2() {
        return backgroundColor2;
    }

    /**
     * @param backgroundColor2 the backgroundColor2 to set
     */
    public void setBackgroundColor2(String backgroundColor2) {
        this.backgroundColor2 = backgroundColor2;
    }

    /**
     * @return the speed
     */
    public long getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(long speed) {
        this.speed = speed;
    }

    /**
     * @return the isFileOk
     */
    public boolean isIsFileOk() {
        return isFileOk;
    }

    /**
     * @param isFileOk the isFileOk to set
     */
    public void setIsFileOk(boolean isFileOk) {
        this.isFileOk = isFileOk;
    }

    /**
     * @return the news
     */
    public ArrayList<News> getNews() {
        return news;
    }

    /**
     * @param news the news to set
     */
    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    /**
     * @return the imagesSpeed
     */
    public long getImagesSpeed() {
        return imagesSpeed;
    }

    /**
     * @param imagesSpeed the imagesSpeed to set
     */
    public void setImagesSpeed(long imagesSpeed) {
        this.imagesSpeed = imagesSpeed;
    }
    

}
