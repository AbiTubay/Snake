package Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * GamePanel controls all gameObject components
 * Game restart not explored here
 * 
 * @author abigail
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    private static final long serialVersionIUD = 1L;
    
    private static final int WIDTH = 500, HEIGHT = 500;
    
    private Thread thread;
    
    public boolean running;
    
    public boolean right = true, left = false, up = false, down = false;
    
    private BodyPart body;
    
    private ArrayList<BodyPart> snake;
    
    private int xCoor = 10, yCoor = 10, size = 5;
    
    private int ticks = 0; //body
    
    private Apple apple;
    private ArrayList<Apple> apples;
    private Random r;
    
    private int key = KeyEvent.VK_RIGHT;
    
    
    /**
     * GamePanel constructor to start the game
     */
    public GamePanel(){
        
        setFocusable(true);
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        addKeyListener(this);
        
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
        
        r = new Random();
        
        start();
    }
    
    /**
     * start game
     */
    public void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    /**
     * stop game
     */
    public void stop(){
        running = false;
        try {
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    /**
     * controls snake (called ticks) and apple bodies
     */
    public void tick(){
        if(snake.size() == 0) {
            body = new BodyPart(xCoor, yCoor, 10);
            snake.add(body);
        }
        ticks++;
        
        //if snake size fills the whole stage
        if(ticks > 250000){
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;
            
            ticks = 0;
            
            body = new BodyPart(xCoor, yCoor, 10);
            snake.add(body);
            
            if(snake.size() > size){
                snake.remove(0);
            }  
        }
        
        //spawn apple
        if(apples.size() == 0){
            int xApCoor = r.nextInt(49);
            int yApCoor = r.nextInt(49);
            
            apple = new Apple(xApCoor, yApCoor, 10);
            apples.add(apple);
        }
        
        //collision with apples
        for(int i=0; i<apples.size(); i++){
            if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()){
                size++;
                apples.remove(i);
                i++;
            }
        }
        
        //collision with its body
        for(int i = 0; i< snake.size(); i++){
            if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()){
                if(i != snake.size()-1){
                    //System.out.println("Game Over");
                    stop();
                    
                }
            }
        }
        
        //collision with walls
        if(xCoor<0 || xCoor > 49 || yCoor<0 || yCoor > 49){
            //System.out.println("Game Over");
            stop();
        }
        
    }
   
    /**
     * while game play is running, draw pixels for road, snake, and apple        
     * @param g frame
     */
    public void paint(Graphics g){
        
        if(running){
            g.clearRect(xCoor, yCoor, WIDTH, HEIGHT);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            for(int i =0; i< WIDTH/10; i++){
                g.drawLine(i*10, 0, i*10, HEIGHT);
            }

            for(int i =0; i< HEIGHT/10; i++){
                g.drawLine(0, i*10, HEIGHT, i*10);
            }
            for(int i=0; i< snake.size(); i++){
                snake.get(i).draw(g);
            }
            for(int i = 0; i< apples.size(); i++){
                apples.get(i).draw(g);
            }       
        }else{
            gameOver(g); // invoke gameOver if game isn't running anymore
        }
    }
    
    private void gameOver(Graphics g) {   
        g.setColor(Color.RED);
        g.setFont(new Font("Sans serif", Font.BOLD, 30));
        g.drawString("Game Over!", 150, 150);
        repaint();
        
        //Restart game if space is pressed. --->> Will create another JFrame window for the new game and not destroy the old one
        if (key == KeyEvent.VK_SPACE){
          System.out.println("pressed space");
          running = true;
          Main game = new Main();
        }
    }
    
    public static void main(String[] args) {
        new GamePanel();
    }
    
    /**
     * invokes appropriate methods for running the game
     */
    @Override
    public void run(){
        while(running){
            tick();
            repaint();
        }
    }
    
    /**
     * check for which key is pressed to control the snake
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e){
        key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left){
            right = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_LEFT && !right){
            left = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_UP && !down){
            up = true;
            left = false;
            right = false;
        }
        if(key == KeyEvent.VK_DOWN && !up){
            down = true;
            right = false;
            left = false;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){}
    
    @Override
    public void keyTyped(KeyEvent e){}
    
}
