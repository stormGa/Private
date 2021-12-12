package org.kjs.Dao;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.awt.*;

import static org.kjs.Dao.LoL.click;

public class HomePage {
    static String application = "League of Legends";
    static String path = "src/main/java/org/kjs/image/LOL/";


    static WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, application);
    static WinDef.RECT lol_window = new WinDef.RECT();

    public static void returnHomePage() {
        Point career = LoL.getCareer();
        click(career.x+lol_window.left,career.y+lol_window.top);
    }
}
