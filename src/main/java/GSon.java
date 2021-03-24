
import com.google.gson.*;
import java.io.File;
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
   
    
    
    public void readStringsTestNoFile(){
        String myJsonString = "{\"name\":\"john\",\"lastname\":\"smith\"}";
        //JsonParser parser1 = new JsonParser();
        //JsonElement element1 = parser1.parse(myJsonString);
        //JsonObject jsonObject11 = element1.getAsJsonObject();
        
        
        //JsonObject jsonObjectAlt = JsonParser.parseString(myJsonString).getAsJsonObject();
        
        	
        
        try{
            
        
        
            //String name1 = jsonObjectAlt.get("lastname").getAsString();
            //System.out.println(name1);
        }
        catch(Exception ex){  
        }   
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
    
    
    
    
    public void createOrLoadFile(){
        File file = new File(this.strFileName);
        if( file.exists()){
            System.out.println("Esiste");
            this.isFileOk = true;
        }
        else{
            try {
                file.createNewFile();
                System.out.println("NON Esiste");
                this.isFileOk = true;
            } 
            catch (IOException e) {
            }          
            
            
        }
    }
    
    
    
    
}
