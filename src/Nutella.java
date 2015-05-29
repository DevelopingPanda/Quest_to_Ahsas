import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Stefan on 2015-05-28.
 */
public class Nutella {
    public int xpos = 0;
    public int ypos = 0;
    public int angle = 1;
    public int angleChange =2;

    public static Nutella[] nutellas = new Nutella[30];

    public static BufferedImage sprite;

    public Nutella(int x, int y){
        xpos =x;
        ypos = y;
    }

    public static void Update() {
        //move them
        for (int i = 0; i < nutellas.length; i++) {
            if (nutellas[i] != null) {
                nutellas[i].xpos -= 6;
                if (nutellas[i].xpos < -40) {
                    nutellas[i] = null;
                }
            }
        }
        //Collision
        for (int i = 0; i < nutellas.length; i++) {
            if(nutellas[i]!=null) {
                if (nutellas[i].getBounds().intersects(Stefan.getBonds())) {
                    Main.hunger += 20;
                    if(Main.hunger>100)Main.hunger=100;
                    nutellas[i] = null;
                    break;
                }
            }
        }
            //Spawner
            if (Main.state== Main.STATE.GAME&&Main.counter % 40 == 0) {
                for (int i = 0; i < nutellas.length; i++) {
                    if (nutellas[i] == null) {
                        nutellas[i] = new Nutella(800, 10*randInt(30, 50));
                        break;
                    }
                }
            }
        }

    public static void Draw(BufferStrategy s){
        Graphics2D g = (Graphics2D) s.getDrawGraphics();
        for (Nutella n : nutellas){
            if(n!=null) {
                if(Main.state== Main.STATE.GAME||Main.state== Main.STATE.END) {
                    if (n.angle > 80) n.angleChange = -4;
                    else if (n.angle < -80) n.angleChange = 4;
                    n.angle += n.angleChange;
                }
                    AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(n.angle), sprite.getWidth() / 2, sprite.getHeight() / 2);
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                // Drawing the rotated image at the required drawing locations
                g.drawImage(op.filter(sprite,null), n.xpos, n.ypos, 40, 40, null);
                //hitbox
//                g.setColor(Color.red);
//                g.drawRect(n.xpos, n.ypos, 40, 40);
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(xpos, ypos, 40, 40);
    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }



}
