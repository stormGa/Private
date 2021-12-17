package org.kjs.Dao;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.awt.*;

import static org.kjs.Dao.LoL.click;

/**
 * 藏品页
 */
public class Collection {
    static String application = "League of Legends";
    static String path = "src/main/java/org/kjs/image/LOL/";
    static WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, application);
    static WinDef.RECT lol_window = new WinDef.RECT();


    /**
     *
     */
    static Point skin = new Point(120, 100);

    public static void returnCollection() {
        User32.INSTANCE.GetWindowRect(hwnd, lol_window);
        Point collection = LoL.getCollection();
        click(collection.x + lol_window.left, collection.y + lol_window.top);

        enterSkin();
        sortByQuality();

    }

    public static void main(String[] args) {
        returnCollection();
    }

    public static void enterSkin() {
        collectionClick((int) skin.getX(), (int) skin.getY() );
    }

    /**
     * 按品质排序
     */
    public static void sortByQuality() {
        Point sort = new Point(80, 560);
        Point quality = new Point(76, 530);
        collectionClick((int) sort.getX(), (int) sort.getY());
        collectionClick((int) quality.getX(), (int) quality.getY());
    }

    public static void collectionClick(int x, int y) {
        x = x + lol_window.left;
        y = y + lol_window.top;
        click(x, y);
    }
}
