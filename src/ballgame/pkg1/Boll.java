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
    static HashMap<Integer, HashSet<Boll>> map = new HashMap();
    double ox;
    int tics;
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

    void move(MyKeyListener keyli, int width, int height) {
        if (tics++ == 100) {
            color = Color.BLUE;
            tics = 0;
        }
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
        x += vx;
        y += vy;
        if (vx > 0.1 || vy > 0.1 || vx < -0.1 || vy < -0.1) {
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
        Boll target = firstHit();
        if (target != null) {
            // unmove
            double tvx = vx, tvy = vy;
            vx = target.vx;
            vy = target.vy;
            target.vx = tvx;
            target.vy = tvy;
        }

    }

    public Boll firstHit() {
        ArrayList list = new ArrayList();

        for (int hx = -1; hx <= 1; hx += 1) {
            for (Boll ball : get((int) ox + hx)) {
                if (ball == this) {
                    continue;
                }
                if (ball.hits(this)) {
                    return ball;
                }
            }
        }
        return null;
    }

    public ArrayList<Boll> hits() {
        ArrayList list = new ArrayList();

        for (int hx = -1; hx <= 1; hx += 1) {
            for (Boll ball : get((int) ox + hx)) {
                if (ball == this) {
                    continue;
                }
                if (ball.hits(this)) {
                    list.add(ball);
                }
            }
        }
        return list;
    }

    void paint(Graphics2D grphcs, Boll other) {
        grphcs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grphcs.setColor(color);
        grphcs.fillOval((int) x - (int) radius, (int) y - (int) radius, 2 * (int) radius, 2 * (int) radius);
//        grphcs.drawLine(boll, boll2);
//        System.out.println("boll: " + boll);
//        System.out.println("boll2: " + boll2);
        if (other != null) {
            grphcs.setColor(black);
            Line2D line = new Line2D.Double(this, other);
            grphcs.draw(line);
        }
        double oy = y / (radius * 2);
        grphcs.setColor(Color.MAGENTA);
        grphcs.drawRect((int) ox * (int) radius * 2, (int) oy * (int) radius * 2,(int) radius * 2,(int) radius * 2);
    }

    boolean hits(Boll other) {
//        System.out.println("other.radius: " + other.radius);
//        System.out.println("this.radius: " + this.radius);
//        System.out.println("this.distance other: " + this.distance(other));
        return this.distance(other) < other.radius + this.radius;
    }

    private void insert() {
        if (map.containsKey((int) ox)) {
            map.get((int) ox).remove(this);
        }
        ox = x / (radius * 2);
        HashSet set = map.get((int) ox);
        if (set == null) {
            set = new HashSet();
            map.put((int) ox, set);
        }
        set.add(this);
    }

    private HashSet<Boll> get(int key) {
        HashSet set = map.get(key);
        if (set == null) {
            set = new HashSet();
        }
        return set;
    }
}