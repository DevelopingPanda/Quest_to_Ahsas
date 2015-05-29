import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Stefan on 2015-05-29.
 */
public class Firework {
    public static BufferedImage[] sprites = new BufferedImage[7];
    public static Firework[] fireworks = new Firework[10];

    public int x = 0;
    public int y = 500;
    public int detHeight = 0;
    public int size =0;

    public int spriteCounter = 0;

    public static void LoadSheet(){
        URL url = env.class.getResource("fireworks.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <7 ; i++) {
            sprites[i]=img.getSubimage(125*i+(i+1),1,125,125);
        }
    }

    public Firework(int xin, int y){
        //Where y is the detonation height
        x = xin;
        detHeight = y;
        spriteCounter=0;
        this.y=500;
        size = 10*Nutella.randInt(10,20);
    }

    public static void UpdateDraw(BufferStrategy s){
        //controller
        for (int i = 0; i <fireworks.length ; i++) {
            if(fireworks[i]!=null){
                //moveup
                if(fireworks[i].y>fireworks[i].detHeight){
                    fireworks[i].y -=10;
                }
                else if(Main.counter%3==0){
                    fireworks[i].spriteCounter++;
                    if(fireworks[i].spriteCounter>6){fireworks[i]=null;break;}
                }
                //Draw
                Graphics2D g = (Graphics2D)s.getDrawGraphics();
                g.drawImage(sprites[fireworks[i].spriteCounter],fireworks[i].x,fireworks[i].y,fireworks[i].size,fireworks[i].size,null);
            }
        }
        //spawner
        if(Main.counter%10==0){
            for (int i = 0; i <fireworks.length ; i++) {
                if(fireworks[i]==null){
                    fireworks[i]= new Firework(10*Nutella.randInt(2,78),Nutella.randInt(30,150));
                    break;
                }
            }
        }
    }
}
