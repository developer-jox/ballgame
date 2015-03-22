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
import static java.awt.Color.black;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Boll extends Point.Double {

//    static int N = 0;
//    static int keys[][] = new int[][]{
//    Upp,ner,vänster,höger    
//    {87, 83, 65, 68},
//        {38, 40, 37, 39}
//    };
    static HashMap<Point, HashSet<Boll>> map = new HashMap();
//    static Boll b1 = null;
//    static Boll b2 = null;
    boolean caseCollision = false;
    Point ph = new Point();
//    int tics;
    int[] myKeys;
    double vx, vy;

    Color color = Color.BLUE;
    double radius = 20;
    double iStomp;
    double gravity;

    public Boll(double x0, double y0, int radius, int mass, int keys[], double inGravity, Color color) {
        x = x0;
        y = y0;
        this.radius = radius;
        myKeys = keys.clone();
        gravity = inGravity;
        this.color = color;
        insert();
    }

    //utan attributer
    public Boll(double x0, double y0) {
        x = x0;
        y = y0;
    }

    double ox, oy;

    void move(MyKeyListener keyli, int width, int height) {
//        if (tics++ == 100) {
//            color = Color.BLUE;
//            tics = 0;
//        }
        if (keyli.isDown(myKeys[0])) {
            vy -= 0.06;
        }
        if (keyli.isDown(myKeys[1])) {
            vy += 0.06;
        }
        if (keyli.isDown(myKeys[2])) {
            vx -= 0.06;
        }
        if (keyli.isDown(myKeys[3])) {
            vx += 0.06;
        }
        ox = x;
        oy = y;
        x += vx;
        y += vy;
        if (vx > 1.5 || vy > 1.5 || vx < -1.5 || vy < -1.5) {
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
        insert();
//        if (b1 != null) {
//            if (!b1.hits(b2)) {
//                b1.caseColition = false;
//                b2.caseColition = false;
//                b1 = null;
//                b2 = null;
//                System.out.printf("<--------END-------->\n");
//            }
//        }
        Boll target = firstHit();
        if (target != null) {
            // unmove
//            x = ox;
//            y = oy;
//            boolean isColiding = this.caseCollision || target.caseCollision;
//            if (!isColiding) {
                collide(target, this);
                caseCollision = true;
//                if (b1 == null) {
//                    b1 = this;
//                    b2 = target;
//                    this.caseColition = true;
//                    target.caseColition = true;
//                    System.out.printf("------->START<-------\n");
//                }
//                double dist = target.distance(this);
//                double tdx = (x - target.x) / dist;
//                double tdy = (y - target.y) / dist;
//                double asd = 2 * radius - dist;
//                asd /= (radius * 2);
//                double dvx = (tdx * asd);
//                double dvy = (tdy * asd);
//                target.vx -= dvx;
//                target.vy -= dvy;
//                vx += dvx;
//                vy += dvy;
//                if (this.caseColition) {
//                    System.out.printf("CO: %.3f,%.3f  %.3f,%.3f\n", dvx, dvy, tdx, tdy);
//                }
//            } else {
//                if (this.caseColition) {
//                    System.out.printf("XX: %.3f,%.3f  %.3f,%.3f\n", this.vx, this.vy, target.vx, target.vy);
//                }
//                double tvx = vx, tvy = vy;
//                vx = target.vx;
//                vy = target.vy;
//                target.vx = tvx;
//                target.vy = tvy;

            }
//        } else {
            caseCollision = false;
//        } 

    }

    public Boll firstHit() {
        Point tPh = new Point();
        for (int hx = -1; hx <= 1; hx += 1) {
            for (int hy = -1; hy <= 1; hy += 1) {
                tPh.setLocation(ph.x + hx, ph.y + hy);
                for (Boll ball : get(tPh)) {
                    if (ball == this) {
                        continue;
                    }
                    if (ball.hits(this)) {
                        return ball;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Boll> hits() {
        ArrayList list = new ArrayList();
        Point tPh = new Point();
        for (int hx = -1; hx <= 1; hx += 1) {
            for (int hy = -1; hy <= 1; hy += 1) {
                tPh.setLocation(ph.x + hx, ph.y + hy);
                for (Boll ball : get(tPh)) {
                    if (ball == this) {
                        continue;
                    }
                    if (ball.hits(this)) {
                        list.add(ball);
                    }
                }
            }
        }
        return list;
    }

    void paint(Graphics2D grphcs, Boll other) {
        grphcs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        if (other != null) {
            grphcs.setColor(new Color(0, 0, 0, 50));
//            Line2D line = new Line2D.Double(this, other);
//            grphcs.draw(line);
//        }
//        if (caseCollision) {
//            grphcs.setColor(Color.RED);
//        } else {
//            grphcs.setColor(color);
//        }
        grphcs.fillOval((int) x - (int) radius, (int) y - (int) radius, 2 * (int) radius, 2 * (int) radius);
//        grphcs.drawLine(boll, boll2);
//        System.out.println("boll: " + boll);
//        System.out.println("boll2: " + boll2);
        double oy = y / (radius * 2);
        grphcs.setColor(Color.MAGENTA);
        grphcs.drawRect((int) ph.x * (int) radius * 2, (int) oy * (int) radius * 2, (int) radius * 2, (int) radius * 2);
    }

    boolean hits(Boll other) {
//        System.out.println("other.radius: " + other.radius);
//        System.out.println("this.radius: " + this.radius);
//        System.out.println("this.distance other: " + this.distance(other));
        return this.distance(other) < other.radius + this.radius;
    }

    private void insert() {
        if (map.containsKey(ph)) {
            map.get(ph).remove(this);
        }
        ph.x = (int) (x / (radius * 2));
        ph.y = (int) (y / (radius * 2));
        HashSet set = map.get(ph);
        if (set == null) {
            set = new HashSet();
            map.put(ph, set);
        }
        set.add(this);
    }

    private HashSet<Boll> get(Point key) {
        HashSet set = map.get(key);
        if (set == null) {
            set = new HashSet();
        }
        return set;
    }

    private void collide(Boll a, Boll b) {
        double len = Point.distance(a.x, a.y, b.x, b.y);
        double ABx = (b.x - a.x) / len, BAx = -ABx;
        double ABy = (b.y - a.y) / len, BAy = -ABy;

        double bSpeed = a.vx * ABx + a.vy * ABy;
        ABx *= bSpeed;
        ABy *= bSpeed;

        double aSpeed = b.vx * BAx + b.vy * BAy;
        BAx *= aSpeed;
        BAy *= aSpeed;

        b.vx = ABx + (b.vx - BAx);
        b.vy = ABy + (b.vy - BAy);

        a.vx = (a.vx - ABx) + BAx;
        a.vy = (a.vy - ABy) + BAy;

    }

}
