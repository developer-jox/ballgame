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
