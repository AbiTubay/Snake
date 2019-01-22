package Snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author abigail
 */
public class Apple {
    
    private int xCoor, yCoor, width, height;
    
    //apple specs
    public Apple(int xCoor, int yCoor, int tilesize){
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tilesize;
        height = tilesize;
    }
    
    //apple descriptions
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(xCoor*width, yCoor*height, width, height);
    }

    /**
     * 
     * @return xCoor
     */
    public int getxCoor() {
        return xCoor;
    }

    /**
     * 
     * @param xCoor set xCoor
     */
    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    /**
     * 
     * @return yCoor
     */
    public int getyCoor() {
        return yCoor;
    }

    /**
     * 
     * @param yCoor set yCoor
     */
    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
    
}
