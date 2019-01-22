package Snake;

import java.awt.*;
import javax.swing.*;

/**
 * Snake body makeup
 * @author abigail
 */
public class BodyPart {
    
    private int xCoor, yCoor, width, height;
    
    /**
     * Snake body specs
     * @param xCoor xCoor of head
     * @param yCoor yCoor of Head
     * @param tilesize size of snake
     */
    public BodyPart(int xCoor, int yCoor, int tilesize){
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tilesize;
        height = tilesize;
    }
   
    /**
     * snake description
     * @param g 
     */
    public void draw(Graphics g){      
        g.setColor(Color.WHITE);
        g.fillRect(xCoor*width, yCoor*height, width, height);
    }
    
    /**
     * 
     * @return xCoor
     */
    public int getxCoor(){
        return xCoor;
    }
    
    /**
     * 
     * @param xCoor set xCoor
     */
    public void setxCoor(int xCoor){
        this.xCoor = xCoor;
    }
    
    /**
     * 
     * @return yCoor
     */
    public int getyCoor(){
        return yCoor;
    }
    
    /**
     * 
     * @param yCoor set yCoor
     */
    public void setyCoor(int yCoor){
        this.yCoor = yCoor;
    }
    
}
