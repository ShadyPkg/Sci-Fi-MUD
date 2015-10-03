/*
 * Copyright (C) 2015 jonc
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package scifimud;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonc
 */
//run this class to start the applet
public class StartApplet extends ConsoleApplet{
     @Override
     protected void program(){
        SciFiMUD mud = new SciFiMUD();
         try {
             mud.main();
         } catch (URISyntaxException ex) {
             Logger.getLogger(StartApplet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (UnsupportedEncodingException ex) {
             Logger.getLogger(StartApplet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (GeneralSecurityException ex) {
             Logger.getLogger(StartApplet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(StartApplet.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
