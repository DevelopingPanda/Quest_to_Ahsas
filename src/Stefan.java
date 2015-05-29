import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by Stefan on 2015-05-27.
 */
public class Stefan {

    public static int Xvel =0;
    public static int Yvel =0;
    public static int yPos=415;
    public static int xPos=200;

    public static boolean dead = false;

    public static BufferedImage[] sprites = new BufferedImage[9];
    static int localCounter = 0;

    public static void Update(){
        //Gravity
        if(yPos + Yvel>=415) {
            yPos = 415;
            Yvel = 0;
        }
        else
            Yvel += 2;
        yPos += Yvel;

        //Hunger
        if(Main.counter%3==0&&Main.state== Main.STATE.GAME){
            Main.hunger-=1;
        }
    }

    public static void Draw(BufferStrategy s){
        Graphics2D g = (Graphics2D) s.getDrawGraphics();

        //hitbox
//        g.setColor(Color.red);
//        g.drawRect(xPos-90,yPos+60,65,40);
        if(dead){
            g.drawImage(sprites[8], xPos, yPos, -125, 125, null);
        }
        else if(env.castleX<=100){
            g.drawImage(sprites[0],xPos,yPos,-125,125,null);
        }
        else {
            if (Yvel == 0 && onGround()) {

                g.drawImage(sprites[localCounter], xPos, yPos, -125, 125, null);
                if (Main.counter % 4 == 0) {
                    localCounter++;
                    if (localCounter > 3) localCounter = 0;
                }
            } else if (Yvel >= 0) {
                g.drawImage(sprites[5], xPos, yPos, -125, 125, null);
            } else if (Yvel < 0) {
                g.drawImage(sprites[4], xPos, yPos, -125, 125, null);
            }
        }

    }

    public static void setYvel(int yVel){
        Yvel = yVel;
    }

    public static boolean onGround(){
        boolean b = false;
        if(yPos>= 400)
            b=true;
        else
            b=false;
        return b;
    }

    public static Rectangle getBonds(){
        return new Rectangle(xPos-90,yPos+60,65,40);
    }
}
