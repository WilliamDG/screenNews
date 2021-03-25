
import com.google.gson.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    
    public boolean isFileOk;
    
    public GSon(String strFileName){
        this.strFileName = strFileName;
        this.isFileOk = false;

        //readFile();
        
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
   
    
    
    
    public String readStringsTest(){
        String output = "ERROR reading JSON";
        try{
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
        
            /*JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("userJson");
            System.out.println(jsonObject2.size());
            String name = jsonObject2.get("surname").getAsString();
            System.out.println(name);*/
   
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonArray jsonObject3 = jsonObject1.getAsJsonArray("strings");
            output = jsonObject3.get(1).getAsString();
            
            //System.out.println(jsonObject3.get(1));
            //System.out.println(jsonObject3.size());
        }
        catch(Exception ex){  
        }  
        
        /*JsonObject jsonObject4 = element.getAsJsonObject();
        JsonPrimitive jsonObject5 = jsonObject4.getAsJsonPrimitive();
        System.out.println(jsonObject5.size());*/
        
        
        return output;
    }
    
    
    
    
    public void checkFile(){
        File file = new File(this.strFileName);
        if( file.exists()){
            //System.out.println("Esiste");
            readFile();
            this.isFileOk = true;
        }
        else{
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(writeFile());
                bw.flush();
                bw.close();
                //System.out.println("NON Esiste");
                
                this.isFileOk = true;
                checkFile();
            } 
            catch (IOException e) {
            }          
            
            
        }
    }
    
    
    public String writeFile(){
        String output;
        output = 
                    "{\n" +
                    "\t\"settings\" : {\n" +
                    "\t\t\"backgroundColor1\": \t\"#808080\",\n" +
                    "\t\t\"backgroundColor2\": \t\"#424242\",\n" +
                    "\t\t\"speed\": \t\t\t\t1\n" +
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
            fileName = Path.of(strFileName);
        }
        catch(Exception ex){
        }
        
        try {
            str = Files.readString(fileName);
            //System.out.println(str);
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int getNewsNumber(){
        int output = 0;
    
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(str);
        
            /*JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("userJson");
            System.out.println(jsonObject2.size());
            String name = jsonObject2.get("surname").getAsString();
            System.out.println(name);*/
   
            JsonObject jsonObject1 = element.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.getAsJsonObject("news");
            output = jsonObject2.size();
        
        return output;
    }
    
    
    public String getNewsText(int i){
        String output = "Error reading the news text";
        
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
        String output = "#000000";
        
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
        String output = "TimesRoman";
        
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
        int output = 0;
        
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
        int output = 60;
        
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
        String output = "#123123";
        
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
        long output = 1;
        
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
    
}
