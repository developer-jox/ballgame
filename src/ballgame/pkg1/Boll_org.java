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
import static java.awt.Color.red;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

class Boll_org extends Point.Double {

//    static int N = 0;
//    static int keys[][] = new int[][]{
//    Upp,ner,vänster,höger    
//    {87, 83, 65, 68},
//        {38, 40, 37, 39}
//    };

    int[] myKeys;
    double vx, vy;

    Color color = Color.BLUE;
    double radius = 20;
    double iStomp;
    double gravity;
    public Boll_org(double x0, double y0, int radius, int mass, int keys[], double inGravity, Color color) {
        x = x0;
        y = y0;
        this.radius = radius;
        myKeys = keys.clone();
        gravity = inGravity;
        this.color = color;
    }

    //utan attributer
    public Boll_org(double x0, double y0) {
        x = x0;
        y = y0;
    }

    void move(MyKeyListener keyli, int width, int height) {
        if (keyli.isDown(myKeys[0])) {
            vy -= 0.04;
        }
        if (keyli.isDown(myKeys[1])) {
            vy += 0.04;
        }
        if (keyli.isDown(myKeys[2])) {
            vx -= 0.04;
        }
        if (keyli.isDown(myKeys[3])) {
            vx += 0.04;
        }
        x += vx;
        y += vy;
        if (vx > 0.1|| vy > 0.1 || vx < -0.1 || vy < -0.1) {
            vx *= gravity;
            vy *= gravity;
        }
//        if (vx <= -7) {
//            vx = -7;
//        }
//        if (vx >= 7) {
//            vx = 7;
//        }
//        if (vy <= -7) {
//            vy = -7;
//        }
//        if (vy >= 7) {
//            vy = 7;
//        }
        if (x - radius < 0) {
            iStomp = -(x - radius);
            x = iStomp + radius;
            vx = -vx;
        }
        if (x + radius > width) {
            iStomp = (x + radius) - width;
            x = width - iStomp - radius;
            vx = -vx;
        }
        if (y - radius < 0) {
            iStomp = -(y - radius);
            y = iStomp + radius;
            vy = -vy;
        }
        if (y + radius > height) {
            iStomp = (y + radius) - height;
            y = height - iStomp - radius;
            vy = -vy;
        }
    }

    void paint(Graphics2D grphcs, Boll_org other) {
        grphcs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grphcs.setColor(color);
        grphcs.fillOval((int) x - (int) radius, (int) y - (int) radius, 2 * (int) radius, 2 * (int) radius);
//        grphcs.drawLine(boll, boll2);
//        System.out.println("boll: " + boll);
//        System.out.println("boll2: " + boll2);
        if (other != null) {
            grphcs.setColor(red);
            Line2D line = new Line2D.Double(this, other);
            grphcs.draw(line);
        }
    }

    boolean hits(Boll_org other) {
//        System.out.println("other.radius: " + other.radius);
//        System.out.println("this.radius: " + this.radius);
//        System.out.println("this.distance other: " + this.distance(other));
        return this.distance(other) < other.radius + this.radius;
    }
}
