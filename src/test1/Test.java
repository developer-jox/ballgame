package test1;

import java.applet.Applet;
import java.awt.Graphics;

public class Test extends Applet {

    Boll[] balls = new Boll[10];

    @Override
    public void init() {
        for (int i = 0; i < 10; i++) {
            balls[i] = new Boll(30 * i+5, i / 5);
        }
    }

    @Override
    public void paint(Graphics g) {
        for (Boll ball : balls) {
            ball.paint(g);
        }
        balls[9].move();
    }

}
