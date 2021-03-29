
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
public class ProgressBar extends JPanel implements Runnable{

    JProgressBar pbar;
    static int MY_MINIMUM;
    static int MY_MAXIMUM;
    
    private int currentValue;

    public ProgressBar(int MY_MINIMUM, int MY_MAXIMUM) {
        this.MY_MINIMUM = MY_MINIMUM;
        this.MY_MAXIMUM = MY_MAXIMUM;
        
        setLayout(new GridBagLayout());
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setBorder(new EmptyBorder(28, 28, 28, 28));
        GridBagConstraints constraints = new GridBagConstraints( );                 //https://www.oreilly.com/library/view/learning-java/1565927184/ch16s06.html
        JLabel jLabel1 = new JLabel("Loading Images...");
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(jLabel1, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        
        // initialize Progress Bar
        pbar = new JProgressBar();
        pbar.setMinimum(MY_MINIMUM);
        pbar.setMaximum(MY_MAXIMUM);
        // add to JPanel
        add(pbar, constraints);
    }

    public void updateBar(int newValue) {
      pbar.setValue(newValue);
    }

    @Override
    public void run() {
        boolean fullyLoad = false;
        while(!fullyLoad){
            updateBar(this.currentValue);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) { }
            
            if(this.currentValue >= MY_MAXIMUM){               //end thread
                fullyLoad = true;
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);      //Obtain Window/JFrame from inside a JPanel
                topFrame.setVisible(false);
            }
        }
     }
    
    
    
    
    
    
    /**
     * @return the currentValue
     */
    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * @param currentValue the currentValue to set
     */
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
    
}