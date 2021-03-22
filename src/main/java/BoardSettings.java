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
    private int B_WIDTH = 800;
    private int B_HEIGHT = 600;
    
    
    private long speed = 15;

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
}
