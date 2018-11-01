import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Sprite {
    private int x_pos; 
    private int y_pos; 
    private int width; 
    private int height; 
    private int vx; 
    private int vy; 
        
    
   public Sprite(int x, int y, int w, int h, int vx, int vy){
       this.x_pos = x; 
       this.y_pos = y; 
       this.width = w; 
       this.height = h; 
       this.vx = vx; 
       this.vy = vy; 
   }
   public Sprite(int x, int y, int w, int h){
       this.x_pos = x; 
       this.y_pos = y; 
       this.width = w; 
       this.height = h; 
   }
    public int getX_pos() {
        return x_pos;
    }
    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }
    public int getY_pos() {
        return y_pos;
    }
    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getVx() {
        return vx;
    }
    public void setVx(int vx) {
        this.vx = vx;
    }
    public int getVy() {
        return vy;
    }
    public void setVy(int vy) {
        this.vy = vy;
    }
    
    public Rectangle getBounds(){
       int x = this.getX_pos(); 
       int y = this.getY_pos();
       int h = this.getHeight(); 
       int w = this.getWidth(); 
       Rectangle bounds = new Rectangle(x, y, w, h);
       return bounds;
    }
    public void move(){
        x_pos += vx;
        y_pos += vy;
    }
    public void drawSprite(Graphics2D g2){
        g2.fill(getBounds());
    }

}


    