package org.kjs.Dao.Lol;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.kjs.utils.Screen;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LoL {
    static String application = "League of Legends";
    static String path = "src/main/java/org/kjs/image/LOL/";


    static WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, application);
    static WinDef.RECT lol_window = new WinDef.RECT();

    public static void main(String[] args) {
//        BufferedImage image = Screen.getScreen(application);
//        //1280*720
//        Screen.saveScreenImage(image, path, "mainFrame", "jpg");
        //758,402
        //获取默认屏幕
        User32.INSTANCE.GetWindowRect(hwnd, lol_window);
        System.out.println(lol_window.left);
        System.out.println(lol_window.top);
        Point career = getCareer();
        click(career.x+lol_window.left,career.y+lol_window.top);

    }


    /**
     * 获取几个最重要的按钮坐标
     * 主页，生涯，藏品，战利品，头像框
     */
    public static Point getHomePage() {
        Point homepage = new Point(285, 40);
        return homepage;
    }

    public static Point getCareer() {
        Point career = new Point(686, 40);
        return career;
    }

    public static Point getCollection() {
        Point collection = new Point(756, 40);
        return collection;
    }

    public static Point getTrophy() {
        Point trophy = new Point(820, 40);
        return trophy;
    }

    public static Point getAvatar() {
        Point avatar = new Point(1090, 40);
        return avatar;
    }

    /**
     *
     * ！！！warning 必须IDEA以管理员启动
     * 模拟点击
     *
     * @param x
     * @param y
     */
    public static void click( int x, int y) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("robot异常");
            e.printStackTrace();
        }
        System.out.println("x:"+x+"   y:"+y);
        robot.mouseMove(x, y);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); //按下左键

        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); //松开左键
    }


}
