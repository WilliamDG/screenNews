
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
    private Color backgroundColor1;
    private Color backgroundColor2;
    private long speed;

    
    
    
    public BoardSettings(){
        B_WIDTH = 800;
        B_HEIGHT = 600;
        boardSize = new Dimension(B_WIDTH, B_HEIGHT);   
        
        backgroundColor1 = Color.decode("#808080");      //or --> new Color(80, 80, 80);
        backgroundColor2 = Color.decode("#424242");      //or --> new Color(80, 80, 80);
        
        speed = 1;
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
     * @return the backgroundColor1
     */
    public Color getBackgroundColor1() {
        return backgroundColor1;
    }

    /**
     * @param backgroundColor1 the backgroundColor1 to set
     */
    public void setBackgroundColor1(Color backgroundColor1) {
        this.backgroundColor1 = backgroundColor1;
    }
    

    /**
     * @return the backgroundColor2
     */
    public Color getBackgroundColor2() {
        return backgroundColor2;
    }

    /**
     * @param backgroundColor2 the backgroundColor2 to set
     */
    public void setBackgroundColor2(Color backgroundColor2) {
        this.backgroundColor2 = backgroundColor2;
    }
}
