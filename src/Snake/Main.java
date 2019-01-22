package Snake;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import java.util.*;
import javax.swing.JLabel;

/**
 * frame for snake
 * @author abigail
 */
public class Main {
    
    private JFrame frame = new JFrame();
    
    public Main(){
        frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
  
    }
    
    
    public static void main(String[] args) {
        new Main();
    }

}
