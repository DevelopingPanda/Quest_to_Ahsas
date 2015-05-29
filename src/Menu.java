import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Stefan on 2015-05-28.
 */
public class Menu {
    public static BufferedImage scroll;
    public static BufferedImage scrollDead;
    public static BufferedImage scrollask;

    public static void loadScroll(){
        URL url = Menu.class.getResource("scroll.png");
        URL url1 = Menu.class.getResource("scroll_over.png");
        URL url2 = Menu.class.getResource("scrollask.png");
        try {
            scroll = ImageIO.read(url);
            scrollDead=ImageIO.read(url1);
            scrollask=ImageIO.read(url2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void Draw(BufferStrategy s){
        Graphics2D g = (Graphics2D) s.getDrawGraphics();

        g.drawImage(env.backgroundImg,0,0,800,600,null);
        env.Draw(s);
        Stefan.Draw(s);
        Enemy.Draw(s);
        Nutella.Draw(s);
        if(!Stefan.dead) {
            env.Update();
            g.drawImage(scroll, 200, 20, 400, 600, null);
        }
        else {
            g.drawImage(scrollDead, 200, 70, 400, 600, null);
        }

        s.show();
    }
}
