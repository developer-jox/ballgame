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
