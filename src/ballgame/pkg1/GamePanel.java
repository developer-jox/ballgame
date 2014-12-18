package ballgame.pkg1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

class GamePanel extends JPanel {

    int N = 301;

    Boll[] boll = new Boll[N];

    Target target = new Target(300, 400);
    MyKeyListener keyli = new MyKeyListener(this);

    public GamePanel() {
        JButton but = new JButton("kalle");
        add("North", but);
        but.addKeyListener(keyli);
        setPreferredSize(new Dimension(1024, 1000));
        setDoubleBuffered(true);

        boll[0] = new Boll(30, 30, 20, 0, new int[]{'W', 'S', 'A', 'D'}, 0.998, Color.GRAY);
        for (int i = 1; i < N; i++) {
            boll[i] = new Boll((80 + i * 50) % 1000, 100 + (i / 20) * 75, 20, 0, new int[]{KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT}, 0.998, Color.BLUE);
        }
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        for (Boll b : boll) {
            b.paint((Graphics2D) grphcs, boll[0]);
        }
        boll[0].paint((Graphics2D) grphcs, null);
//        target.paint((Graphics2D) grphcs, null);
    }

    void run() {
        requestFocus();
        while (true) {
            for (Boll b : boll) {
                b.move(keyli, getWidth(), getHeight());
            }
            ArrayList<Boll> hits = boll[0].hits();
            for (Boll b : hits) {
                b.color = Color.YELLOW;
            }
//            for (int i = 0; i < 200; i++) {
//                if (boll[i].hits(target)) {
//                    target = new Target(20, getWidth(), getHeight());
//                }
//            }
//            if (nowTime - time > 2000) {
//                fps = new Fps();
//            }
            repaint();

            try {
                Thread.sleep(6);
            } catch (Exception e) {
            }

        }
    }

}
