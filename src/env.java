import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Stefan on 2015-05-27.
 */
public class env {

    public static pieces[] pieceses = new pieces[9];
    public static BufferedImage treeImg;
    public static BufferedImage backgroundImg;
    public static int castleX = 700;
    public static int castleY = 160;


    public static void setGround(){
        URL url = env.class.getResource("background.png");
        try {
            backgroundImg = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <pieceses.length ; i++) {
            pieceses[i] = new pieces();
            pieceses[i].xpos = i*125;
            if(i%2==0)
                pieceses[i].tree=false;
        }
    }

    public static void Update(){
        if(castleX>100) {
            for (pieces p : pieceses) {
                p.xpos -= 6;
                if (p.xpos < -125) {
                    p.xpos += 125 * 8;
                }
            }
            if (Main.clear) castleX -= 6;
        }

    }
    public static void Draw(BufferStrategy s){
        Graphics2D g = (Graphics2D) s.getDrawGraphics();
        g.drawImage(backgroundImg,0,0,800,600,null);
        if(Main.state== Main.STATE.END){
            Firework.UpdateDraw(s);
        }
        for (pieces p : pieceses){
            p.Draw(s);
        }
        if(Main.clear){
            g.drawImage(Main.castle,castleX,castleY,400,400,null);
        }
        if(castleX<100){

        }
    }
}

class pieces{
    int xpos = 0;
    boolean tree = true;

    void Draw(BufferStrategy s){
        Graphics2D g = (Graphics2D) s.getDrawGraphics();
        g.drawImage(Stefan.sprites[6],xpos,365,125,230, null);
        if(tree)
        g.drawImage(env.treeImg,xpos-100,317,250,250, null);
    }
}
