import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by Stefan on 2015-05-27.
 */
public class Main implements KeyListener{

    public static int counter = 1;
    public static int hunger = 100;
    public static long startTime;
    public static boolean clear = false;

    public static BufferedImage castle;

    public static enum STATE{
        GAME,
        MENU,
        PAUSE,
        END
    }

    public static STATE state = STATE.MENU;

    public static void main(String args[]){
        new Main();
    }

    public Main(){
        //window
        JFrame window = new JFrame("Quest to Ahsas");
        window.setSize(800,600);
        window.setResizable(false);
        window.setVisible(true);
        window.addKeyListener(this);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.createBufferStrategy(2);
        BufferStrategy s = window.getBufferStrategy();

        spritesheet.init();
        env.setGround();
        Menu.loadScroll();
        Firework.LoadSheet();

        //Loop
        while (true) {
            //GAME
            long B = System.currentTimeMillis();
            //GAME
            if (state == STATE.GAME) {
                Update();
                Draw(s);

            }
            //MENU
            else if(state==STATE.MENU){
                Menu.Draw(s);
            }
            //PAUSE
            else if(state==STATE.PAUSE){
                if(Stefan.dead){
                    if(System.currentTimeMillis()-Enemy.timeSet>1200){
                        state=STATE.MENU;
                    }
                }
            }
            else if(state==STATE.END){
                //Wait to clear out, spawning disabled
                Stefan.Update();
                env.Update();
                Nutella.Update();
                Enemy.Update();
                if(isEmpty(Nutella.nutellas)&&isEmpty(Enemy.enemies)){
                    clear=true;
                }
                Draw(s);

                s.show();

            }

            counter++;
            if (counter > 1000) counter = 1;
                long A = System.currentTimeMillis();
                long S = (1000 / 30) - (A - B);
                if (S < 0) S = 1;
                try {
                    Thread.sleep(S);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    public static void Update(){
        Stefan.Update();
        env.Update();
        Nutella.Update();
        Enemy.Update();

        //Game Over logic
            //Victory Logic
        if(System.currentTimeMillis()-startTime>=90000){
            state=STATE.END;
        }
            //Dead Logic
        if(hunger<=0)Stefan.dead=true;
        if(Stefan.dead)state=STATE.PAUSE;
    }
    public static void Draw(BufferStrategy s){
        Graphics2D g = (Graphics2D) s.getDrawGraphics();
//        g.setColor(new Color(64, 104, 48));
//        g.fillRect(0,0,800,600);

        env.Draw(s);
        Nutella.Draw(s);
        Stefan.Draw(s);
        Enemy.Draw(s);

        //HUD
        g.setFont(new Font("Calibri",Font.BOLD,20));
        g.setColor(Color.black);
        g.drawString("Hunger: ",50,65);

        g.setStroke(new BasicStroke(2));
        g.drawRect(135, 47, 104, 24);
        g.setColor(new Color(163, 63, 33));
        g.fillRect(137,49,hunger,20);

        g.setColor(new Color(124, 83, 34));
        g.drawRect(550,50,124,19);
        g.setColor(new Color(244, 151, 35));
        if(state!=STATE.END)
        g.fillRect(552, 52, 2*(int) (System.currentTimeMillis() - startTime) / 1000, 15);
        else
            g.fillRect(552, 52, 120, 15);
        g.drawImage(castle,700,30,50,50,null);

        if(env.castleX<=100){
            g.drawImage(Menu.scrollask, 200, 70, 400, 500, null);
        }

        s.show();
    }

    public static void reset(){
        for (int i = 0; i <Enemy.enemies.length ; i++) {
            Enemy.enemies[i]=null;
        }
        for (int i = 0; i <Nutella.nutellas.length ; i++) {
            Nutella.nutellas[i]=null;
        }
        Stefan.yPos=415;
        hunger=100;
        Stefan.dead= false;
        startTime=System.currentTimeMillis();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(i==KeyEvent.VK_SPACE){

            if(state==STATE.MENU){
                state=STATE.GAME;
                counter=1;
                reset();
            }
            else if(Stefan.onGround())
                Stefan.setYvel(-30);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    static boolean isEmpty (Nutella[] n){
        boolean b =true;
        for(Nutella m: n){
            if(m!=null){b=false;break;}
        }
        return b;
    }
    static boolean isEmpty (Enemy[] n){
        boolean b =true;
        for(Enemy m: n){
            if(m!=null){b=false;break;}
        }
        return b;
    }
}
