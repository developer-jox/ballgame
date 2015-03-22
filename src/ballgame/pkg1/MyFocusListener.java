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

import java.awt.Component;
import java.awt.Polygon;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyFocusListener implements FocusListener {
    
    boolean focus = false;

    MyFocusListener(Component comp) {
        comp.addFocusListener(this);
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        System.out.println("focus gained");
        focus = true;
    }

    @Override
    public void focusLost(FocusEvent e) {
        System.out.println("focus lost");
        focus = false;
    }
    
    public boolean isFocus(FocusEvent e) {
        return focus;
    }
    
    
}
