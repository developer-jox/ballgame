/* 
 * Copyright (C) 2015 developer-jox <developer.jox@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ballgame.pkg1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.concurrent.Semaphore;

class GamePanel extends JPanel {

    int N = 1200;
    final Semaphore mutexRefresh = new Semaphore(0);
    final Semaphore mutexRefreshing = new Semaphore(1);
    int refresh = 0;

    ArrayList<Boll> boll = new ArrayList(N);

    Target target = new Target(300, 400);
    MyKeyListener keyli = new MyKeyListener(this);

    public GamePanel() {
        JButton but = new JButton("kalle");
        add("North", but);
        but.addKeyListener(keyli);
        setPreferredSize(new Dimension(1920, 1120));
        setDoubleBuffered(true);

        boll.add(new Boll(30, 30, 10, 0, new int[]{'W', 'S', 'A', 'D'}, 0.995, Color.GRAY));
        for (int i = 1; i < N; i++) {
            boll.add(new Boll((40 + 30 * (i % 100) + 20) , 50 + (i / 100) * 40, 10, 0, new int[]{KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT}, 0.995, Color.BLUE));
        }
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        for (Boll b : boll) {
            b.paint((Graphics2D) grphcs, (Boll) boll.get(0));
        }
        boll.get(0).paint((Graphics2D) grphcs, null);
//        target.paint((Graphics2D) grphcs, null);
    }

    void run() {
        requestFocus();
        while (true) {
//            if (keyli.isDown(KeyEvent.VK_PLUS)) {
//                N++;
//                boll.add();
//            } else if (keyli.isDown(KeyEvent.VK_MINUS)) {
//                N--;
//                boll.remove(N, Boll);
//            }
            for (Boll b : boll) {
                b.move(keyli, getWidth(), getHeight());
            }
//            ArrayList<Boll> hits = boll.get(0).hits();
//            for (Boll b : hits) {
//                b.color = Color.YELLOW;
//            }
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
                Thread.sleep(4);
            } catch (Exception e) {
            }

        }
    }

}
