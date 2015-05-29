import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * Created by Stefan on 2015-05-28.
 */
public class Enemy {
    public static BufferedImage[] sprites = new BufferedImage[4];
    public  int localCounter = 0;
    static boolean col = false;
    static long timeSet = 0;

    public int xpos;
    public int ypos;
    public int speed;

    public static Enemy[] enemies = new Enemy[15];

    public Enemy(int x, int y){
        xpos = x;
        ypos = y;
        speed = Nutella.randInt(10,14);
    }

    public static void Update(){
        for (int i = 0; i <enemies.length ; i++) {
            if(enemies[i]!=null){
                //movement
                enemies[i].xpos -=enemies[i].speed;

                //Collision
                if(enemies[i].getBounds().intersects(Stefan.getBonds())){Stefan.dead = true;col = true; timeSet= System.currentTimeMillis();}
                else{
                    col=false;
                }
                //BoundsChecker
                if(enemies[i].xpos<=-180){enemies[i]=null;}
            }
            //Spawner
            else if(Main.state==Main.STATE.GAME&&Main.counter%70==0){
                enemies[i] = new Enemy(900,470);
                break;
            }
        }
    }

    public static void Draw(BufferStrategy s){
        Graphics2D g = (Graphics2D) s.getDrawGraphics();
        for (Enemy e: enemies) {
            if (e != null) {
                if(Main.state== Main.STATE.GAME||Main.state== Main.STATE.END) {
                    if (Main.counter % 5 == 0) {
                        e.localCounter++;
                        if (e.localCounter >= 4) e.localCounter = 0;
                    }
                }
                g.drawImage(sprites[e.localCounter], e.xpos, 470, -180, 90, null);
                //hitbox
//                g.setColor(Color.red);
//                g.drawRect(e.xpos-130, 480, 70, 70);
            }
        }

        //debug reasons
//        if(col||System.currentTimeMillis()-timeSet<1500){
//            g.fillOval(700,50,40,40);
//        }

    }

    public Rectangle getBounds(){
        return new Rectangle(xpos-130, 480, 70, 70);
    }
}
