package ballgame.pkg1;

import java.awt.Color;

class Target extends Boll {
    public Target(int radius, int width, int height) {
        this(radius + (width - radius * 2) * Math.random(), radius + (height - radius * 2) * Math.random());
    }

    public Target(double x0, double y0) {
        super(x0, y0);
        color= Color.RED;
    }

}
