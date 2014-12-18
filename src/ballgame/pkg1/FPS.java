package ballgame.pkg1;

import java.awt.Graphics2D;
import java.util.Date;

public class FPS {

    
    int fps = 0;
    public FPS() {
    }

    void calcFPS(int count, long startTime) {
        Date date = new Date();
        long nowTime = date.getTime();
        
        if (nowTime - startTime >= 1000) {
            startTime = 0;
            nowTime = 0;
            count = fps;
        } 
    }
    void paint(Graphics2D grphcs, Boll other, int width, int height) {
        String Sfps = String.valueOf(fps);
        grphcs.drawString(Sfps, width - 40, height - 40);
    }
}
