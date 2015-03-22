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
package test1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;

public class Boll {

    Color color = Color.BLUE;

    static HashMap<Integer, HashSet<Boll>> map = new HashMap();
    int ox;
    int x, vx;

    public Boll(int x, int vx) {
        this.x = x;
        this.vx = vx;
        insert();
    }

    private void insert() {
        if (map.containsKey(ox)) {
            map.get(ox).remove(this);
        }
        ox = x / 20;
        HashSet set = map.get(ox);
        if (set == null) {
            set = new HashSet();
            map.put(ox, set);
        }
        set.add(this);
    }

    void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x-10, 0, 20, 20);
    }

    void move() {
        x += vx;
        if (x < 0 || x > 400) {
            vx = -vx;
        }
        insert();
        for (int x = -1; x <= 1; x += 1) {
            for (Boll ball : get(ox + x)) {
                if (ball == this) {
                    continue;
                }
                ball.color= Color.BLUE;
                if (ball.hits(this)) {
                    ball.color = Color.RED;
                } else {
                    ball.color = Color.GREEN;
                }
            }
        }

    }

    private HashSet<Boll> get(int key) {
        HashSet set = map.get(key);
        if (set == null) {
            set = new HashSet();
        }
        return set;
    }

    private boolean hits(Boll aThis) {
        return Math.abs(x - aThis.x) < 20;
    }
}
