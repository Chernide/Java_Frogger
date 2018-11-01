import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameTest1 extends JPanel implements KeyListener
{
   private static final long serialVersionUID = 1L;
   private static final int PREF_W = 592;
   private static final int PREF_H = 373;
   private static final int BLOCK_SIZE = 16; 
   private static final int FROG_START_X = 254; 
   private static final int FROG_START_Y = 357; 
   private RenderingHints hints = new RenderingHints(
   RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   private Font font = new Font("Cooper Black", Font.PLAIN, 25);
   private Font big = new Font("Cooper Black", Font.PLAIN, 45);
   private Font small = new Font("Arial", Font.PLAIN, 10);
   private int score = 0; 
   private int lives = 5; 
   
   
   private boolean winGame; 
   private boolean gameOver; 
   private boolean logIntersect; 
   
   //Logs
   private ArrayList<Sprite> logs;
   private ArrayList<Sprite> logs1;
   private Image logImage; 
   
   //Cars
   private ArrayList<Sprite> cars;
   private ArrayList<Sprite> cars1;
   private int carSpeed1 = -2; 
   private int carSpeed = 1;
   private Image carImage;
   private Image carImage2;
   
   //Lilypads
   private Image lilyImage;
   private Image checklily; 
   private Sprite lily, lily1, lily2, lily3;
   private boolean winlily, winlily1, winlily2, winlily3; 
   
   //Frog
   private Sprite frog; 
   private Image frogImage; 
   
   private byte[][] board =  {
           {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
           {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
           {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,2,2,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,2,2,1},
           {1,2,2,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,2,2,1},
           {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
           {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
           {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           //256
           {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
           //272
           {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
           //288
           {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
           //304
           {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},

   };


   public GameTest1() 
   {
      addKeyListener(this);
      setFocusable(true);
      requestFocus();
      
      
      //LOGS
      logs = new ArrayList<>();
      logs1 = new ArrayList<>();
      makeLogs();
      logImage = new ImageIcon(this.getClass().getResource("Log.png")).getImage(); 
      //give picture to each log
      
      //CARS
      cars = new ArrayList<>();
      cars1 = new ArrayList<>();
      makeCars();
      carImage = new ImageIcon(this.getClass().getResource("Car.png")).getImage();
      carImage2 = new ImageIcon(this.getClass().getResource("Car2.png")).getImage();
      
      //LILYPADS
      makeLily();
      lilyImage = new ImageIcon(this.getClass().getResource("Lily+.png")).getImage();
      checklily = new ImageIcon(this.getClass().getResource("checkLily.png")).getImage();
      
      //FROG
      makeFrog();
      frogImage = new ImageIcon(this.getClass().getResource("Frog1.png")).getImage();
      
      new Timer(10, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             update();
             repaint();
          }
       }).start();
      }

   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }
   
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHints(hints);
      
      for(int i = 0; i < board[0].length; i++){
          for(int j = 0; j < board.length; j++){
              if(board[j][i] == 0){
                  g2.setColor(Color.black);
                  g2.fillRect(i+i*BLOCK_SIZE-1, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
              if(board[j][i] == 1){
                  g2.setColor(new Color(7, 84, 10));
                  g2.fillRect(i+i*BLOCK_SIZE-1, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
              if(board[j][i] == 2){
                  g2.setColor(Color.blue);
                  g2.fillRect(i+i*BLOCK_SIZE, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
              if(board[j][i] == 3){
                  g2.setColor(new Color(139,69,19));
                  g2.fillRect(i+i*BLOCK_SIZE, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
              if(board[j][i] == 4){
                  g2.setColor(new Color(98,112,126));
                  g2.fillRect(i+i*BLOCK_SIZE, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
              if(board[j][i] == 5){
                  g2.setColor(Color.white);
                  g2.fillRect(i+i*BLOCK_SIZE, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
              if(board[j][i] == 6){
                  g2.setColor(new Color(0,96,20));
                  g2.fillRect(i+i*BLOCK_SIZE, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
              if(board[j][i] == 7){
                  g2.setColor(new Color(70,80,43));
                  g2.fillRect(i+i*BLOCK_SIZE, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
              if(board[j][i] == 8){
                  g2.setColor(Color.black);
                  g2.fillRect(i+i*BLOCK_SIZE, j+j*BLOCK_SIZE, BLOCK_SIZE+1, BLOCK_SIZE+1);}
          }
      }
      g2.setColor(Color.white);
      g2.setFont(font);
      g2.drawString("Score: " + score, 0, 43);
      g2.drawString("Lives: " + lives, 495, 43);
      for(int i = 0; i < logs.size(); i++)
          g2.drawImage(logImage, logs.get(i).getX_pos(), logs.get(i).getY_pos(), logs.get(i).getWidth(), logs.get(i).getHeight(), this);
      for(int i = 0; i < logs1.size();i++)
          g2.drawImage(logImage, logs1.get(i).getX_pos(), logs1.get(i).getY_pos(), logs1.get(i).getWidth(), logs1.get(i).getHeight(), this);
      for(int i = 0; i < cars.size(); i++){
          g2.drawImage(carImage, cars.get(i).getX_pos(), cars.get(i).getY_pos(), cars.get(i).getWidth(), cars.get(i).getHeight(), this);
          g2.drawImage(carImage2, cars1.get(i).getX_pos(), cars1.get(i).getY_pos(), cars1.get(i).getWidth(), cars1.get(i).getHeight(), this);
      }
      g2.drawImage(lilyImage, lily.getX_pos(), lily.getY_pos(), lily.getWidth(), lily.getHeight(), this);
      g2.drawImage(lilyImage, lily1.getX_pos(), lily1.getY_pos(), lily1.getWidth(), lily1.getHeight(), this);
      g2.drawImage(lilyImage, lily2.getX_pos(), lily2.getY_pos(), lily2.getWidth(), lily2.getHeight(), this);
      g2.drawImage(lilyImage, lily3.getX_pos(), lily3.getY_pos(), lily3.getWidth(), lily3.getHeight(), this);
      
      
//      g2.fill(frog.getBounds());
      g2.drawImage(frogImage, frog.getX_pos()-16, frog.getY_pos()-17, 50, 50, this);
      
      
      if(winlily){
          g2.drawImage(checklily, lily.getX_pos(), lily.getY_pos(), lily.getWidth(), lily.getHeight(), this);
      }
      if(winlily1){
          g2.drawImage(checklily, lily1.getX_pos(), lily1.getY_pos(), lily1.getWidth(), lily1.getHeight(), this);
      }
      if(winlily2){
          g2.drawImage(checklily, lily2.getX_pos(), lily2.getY_pos(), lily2.getWidth(), lily2.getHeight(), this);
      }
      if(winlily3){
          g2.drawImage(checklily, lily3.getX_pos(), lily3.getY_pos(), lily3.getWidth(), lily3.getHeight(), this);
      }
      
      g2.setColor(Color.black);
      g2.setFont(big);
      if(winGame){
          g2.drawString("GAME OVER! YOU WIN", 20, getHeight()/2+30);
      }
      if(gameOver){
          g2.drawString("GAME OVER! YOU LOSE", 20, getHeight()/2+30);
      }
      g2.setColor(Color.white);
      g2.setFont(small);
      g2.drawString("Press 'Q' to speed RED CARS up ('W' to slow) Speed: " + carSpeed, 0, 10);
      g2.drawString("Press 'A' to speed YELLOW CARS up ('S' to slow) Speed: " + carSpeed1, 0, 20);
      
   }

   
   public void update(){
       moveLogs(); 
       moveCars();
       checkCollision();
       checkWin(); 
       checkLives(); 
   }
   
   public void makeLogs(){
       for(int i = 0; i <= 5; i++){
           Sprite log = new Sprite(-10 + i*100, 119, ((int)(Math.random()*0)+48), 16, 1, 0);
           logs.add(log);
       }   
       for(int i = 0; i <= 5; i++){
           Sprite log = new Sprite(getWidth() + i*110, 136, ((int)(Math.random()*0)+48), 16, -1, 0);
           logs1.add(log);
       } 
       for(int i = 0; i <= 5; i++){
           Sprite log = new Sprite(-10 + i*120, 154, ((int)(Math.random()*0)+48), 16, 1, 0);
           logs.add(log);
       }
   }
   public void makeCars(){
       for(int i = 0; i <= 5; i++){
           Sprite car = new Sprite(getWidth() + i*125, 222, 20, 16, carSpeed1, 0);
           cars1.add(car);
       }
       for(int i = 0; i <= 5; i++){
           Sprite car = new Sprite(-10 + i*140, 239  , 20, 16, carSpeed, 0);
           cars.add(car);
       }
       for(int i = 0; i <= 5; i++){
           Sprite car = new Sprite(getWidth() + i* 110, 256, 20, 16, carSpeed1, 0);
           cars1.add(car);
       }
       for(int i = 0; i <= 5; i++){
           Sprite car = new Sprite(-10 + i*160, 273, 20, 16, carSpeed, 0);
           cars.add(car);
       }
   }
   public void makeLily(){
       lily = new Sprite(18, 86, 28, 28, 0, 0);
       lily1 = new Sprite(190, 86, 28, 28, 0, 0);
       lily2 = new Sprite(360, 86, 28, 28, 0, 0);
       lily3 = new Sprite(546, 86, 28, 28, 0, 0);
       
   }
   public void makeFrog(){
       frog = new Sprite(FROG_START_X, FROG_START_Y, 16, 16, 1, 1);
   }
   
   public void moveLogs(){
       for(int i = 0; i < logs.size(); i++){
           logs.get(i).move();
           if(logs.get(i).getX_pos() > getWidth()){
               logs.get(i).setX_pos(-logs.get(i).getWidth());
           }
       }
           for(int i = 0; i < logs1.size(); i++){       
               logs1.get(i).move();
               if(logs1.get(i).getX_pos() < 0 - logs1.get(i).getWidth()){
                   logs1.get(i).setX_pos(getWidth() + 100);
               
           }
       }
   }
   public void moveCars(){
       for(int i = 0; i < cars.size(); i++){
           cars.get(i).setVx(carSpeed);
           cars.get(i).move();
           if(cars.get(i).getX_pos() > getWidth()){
               cars.get(i).setX_pos(-cars.get(i).getWidth());
           }
       }
       for(int i = 0; i < cars1.size(); i++){
           cars1.get(i).setVx(carSpeed1);
           cars1.get(i).move();
           if(cars1.get(i).getX_pos() < 0 - cars1.get(i).getWidth()){
               cars1.get(i).setX_pos(getWidth() + 100);
           }
       }
   }
   public void checkCollision(){
       for(int i = 0; i < cars.size(); i++){
           if(frog.getBounds().intersects(cars.get(i).getBounds())){
               frog.setX_pos(FROG_START_X);
               frog.setY_pos(FROG_START_Y);
               lives--; 
               score -=5; 
           }
       }
       for(int i = 0; i < cars1.size(); i++){
           if(frog.getBounds().intersects(cars1.get(i).getBounds())){
               frog.setX_pos(FROG_START_X);
               frog.setY_pos(FROG_START_Y);
               lives--; 
               score -= 5; 
           }
       }
       for(int i = 0; i < logs.size(); i++){
           if(frog.getBounds().intersects(logs.get(i).getBounds())){
               frog.setX_pos(logs.get(i).getX_pos());
               frog.setY_pos(logs.get(i).getY_pos());
               logIntersect = true; 
           }
       }
       for(int i = 0; i < logs1.size(); i++){
           if(frog.getBounds().intersects(logs1.get(i).getBounds())){
               frog.setX_pos(logs1.get(i).getX_pos());
               frog.setY_pos(logs1.get(i).getY_pos());
               logIntersect = true; 
           }
       }
       if(frog.getBounds().intersects(lily.getBounds())){
          score += 100;
          winlily = true;
          frog.setX_pos(FROG_START_X);
          frog.setY_pos(FROG_START_Y);
           }
       if(frog.getBounds().intersects(lily1.getBounds())){
           score += 100;
           winlily1 = true;
           frog.setX_pos(FROG_START_X);
           frog.setY_pos(FROG_START_Y);
            }
       if(frog.getBounds().intersects(lily2.getBounds())){
           score += 100;
           winlily2 = true;
           frog.setX_pos(FROG_START_X);
           frog.setY_pos(FROG_START_Y);
            }
       if(frog.getBounds().intersects(lily3.getBounds())){
           score += 100;
           winlily3 = true;
           frog.setX_pos(FROG_START_X);
           frog.setY_pos(FROG_START_Y);
            }
       if(frog.getX_pos() < 0 || frog.getX_pos() > getWidth()){
           lives--; 
           frog.setX_pos(FROG_START_X);
           frog.setY_pos(FROG_START_Y);
       }
   }
   public void checkWin(){
       if(winlily && winlily1 && winlily2 && winlily3){
           winGame = true; 
       }
   }
   
   public void checkLives(){
       if(lives < 0){
           gameOver = true; 
       }
   }
   @Override
   public void keyPressed(KeyEvent e)
   {
       int key = e.getKeyCode();

       if(key == KeyEvent.VK_UP){
           frog.setY_pos(frog.getY_pos() - BLOCK_SIZE - 1);    
           score++; 
       }
       if(key == KeyEvent.VK_DOWN){
           frog.setY_pos(frog.getY_pos() + BLOCK_SIZE + 1);
           score++; 
       }
       if(key == KeyEvent.VK_LEFT){
           frog.setX_pos(frog.getX_pos() - BLOCK_SIZE);
           score++; 
       }
       if(key == KeyEvent.VK_RIGHT){
           frog.setX_pos(frog.getX_pos() + BLOCK_SIZE);
           score++; 
       }
       if(key == KeyEvent.VK_Q){
           carSpeed += 1; 
       }
       if(key == KeyEvent.VK_W){
           if(carSpeed == 1){
               System.out.println("no");
           } else{
               carSpeed -= 1; 
           }
       }
       if(key == KeyEvent.VK_A){
           carSpeed1 -= 1; 
       }
       if(key == KeyEvent.VK_S){
           if(carSpeed1 == -1){
               System.out.println("no");
           } else{
               carSpeed1 += 1; 
           }
       }
       
    
       System.out.println(frog.getY_pos());
   }

   @Override
   public void keyReleased(KeyEvent e)
   {
      
   }

   @Override
   public void keyTyped(KeyEvent e){
       
   }

   private static void createAndShowGUI() {
      GameTest1 gamePanel = new GameTest1();

      JFrame frame = new JFrame("Frame Game");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(gamePanel);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      });
   }
}