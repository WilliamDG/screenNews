
import java.awt.Color;
import java.awt.Dimension;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
public class BoardSettings {
    private int B_WIDTH;
    private int B_HEIGHT;
    private Dimension boardSize;
    private Color backgroundColor;
    private long speed;

    
    
    
    public BoardSettings(){
        B_WIDTH = 800;
        B_HEIGHT = 600;
        boardSize = new Dimension(B_WIDTH, B_HEIGHT);   
        
        backgroundColor = Color.decode("#808080");      //or --> new Color(80, 80, 80);
        
        speed = 5;
    }
    
    
    
    /**
     * @return the B_WIDTH
     */
    public int getB_WIDTH() {
        return B_WIDTH;
    }

    /**
     * @param B_WIDTH the B_WIDTH to set
     */
    public void setB_WIDTH(int B_WIDTH) {
        this.B_WIDTH = B_WIDTH;
    }

    /**
     * @return the B_HEIGHT
     */
    public int getB_HEIGHT() {
        return B_HEIGHT;
    }

    /**
     * @param B_HEIGHT the B_HEIGHT to set
     */
    public void setB_HEIGHT(int B_HEIGHT) {
        this.B_HEIGHT = B_HEIGHT;
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
     * @return the boardSize
     */
    public Dimension getBoardSize() {
        return boardSize;
    }

    /**
     * @param boardSize the boardSize to set
     */
    public void setBoardSize(Dimension boardSize) {
        this.boardSize = boardSize;
    }

    
    /**
     * @return the backgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    
    
    
    
    
    
    
    
    
    public int getTest(){
        return 0;
    }
}
