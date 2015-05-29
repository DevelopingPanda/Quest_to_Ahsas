
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Stefan on 2014-08-19.
 */
public class spritesheet {
    public BufferedImage SpriteSheet;

    public spritesheet(BufferedImage ss){
        this.SpriteSheet = ss;
    }
    public BufferedImage grabSprite(int x, int y, int width, int height){
        BufferedImage sprite = SpriteSheet.getSubimage(x, y, width, height);
        return sprite;
    }
    public static void init(){
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheet = null;
        try {
            spriteSheet = loader.LoadImage("spritesheet.png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spritesheet ss = new spritesheet(spriteSheet);

       //BufferedImages to be loaded
        for (int i = 0; i <4 ; i++) {
            Stefan.sprites[i]=ss.grabSprite(i*125+1*(i+1),1,125,125);
        }
        for (int i = 0; i <4 ; i++) {
            Stefan.sprites[i+4]=ss.grabSprite(i*125+1*(i+1),127,125,125);
        }
        Stefan.sprites[8]=ss.grabSprite(379,127,125,125);

        env.treeImg = ss.grabSprite(1,253,250,250);
        Nutella.sprite = ss.grabSprite(253,253,125,125);

        Enemy.sprites[0]=ss.grabSprite(253,380,124,60);
        Enemy.sprites[1]=ss.grabSprite(253,442,124,60);
        Enemy.sprites[2]=ss.grabSprite(380,380,124,60);
        Enemy.sprites[3]=ss.grabSprite(380,442,124,60);

        Main.castle=ss.grabSprite(380,253,124,125);

        /*
        SimpleTower.sprites[0] = ss.grabSprite(1,1,20,20);
        */

    }
}
