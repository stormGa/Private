package org.kjs.utils;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import org.kjs.TEST;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Screen {
    public static Map getDimensionMap()  {
        Properties properties = new Properties();
        //config为属性文件名，放在包com.test.config下，如果是放在src下，直接用config即可
        try {
            properties.load(new TEST().getClass().getResourceAsStream("/properties/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String Dimension_width = properties.getProperty("Dimension_width");
        String Dimension_height = properties.getProperty("Dimension_height");
        Map map = new HashMap();
        map.put("Dimension_width", Dimension_width);
        map.put("Dimension_height", Dimension_height);
        return map;
    }

    /**
     * @param str :应用名称
     * @return 图片缓冲流
     */
    public static BufferedImage getScreen(String str) {
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, str);
        if (hwnd != null)
            System.out.println("获取成功");
        else
            System.out.println("没有获取到");
        WinDef.RECT r = new WinDef.RECT();
        User32.INSTANCE.GetWindowRect(hwnd, r);

        WinDef.RECT lol_window = new WinDef.RECT();

        User32.INSTANCE.GetWindowRect(hwnd, lol_window);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        java.awt.Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        double x1 = 0;
        x1 = d.width / Double.parseDouble(getDimensionMap().get("Dimension_width").toString());
        double x2 = 0;
        x2 = d.height / Double.parseDouble(getDimensionMap().get("Dimension_height").toString());

     /*   Rectangle rg = lol_window.toRectangle();
        System.out.println(rg);*/
        Rectangle rg = new Rectangle((int) (lol_window.left * x1), (int) (lol_window.top * x2), (int) ((lol_window.right - lol_window.left) * x1), (int) ((lol_window.bottom - lol_window.top) * x2));
        BufferedImage image = robot.createScreenCapture(rg);


        return image;


    }

    public static void saveScreenImage(BufferedImage bufferedImage, String path, String imageName, String imageFormat) {
        File outputfile = new File(path + imageName + "." + imageFormat);
        try {
            ImageIO.write(bufferedImage, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分辨率比例，返回适配后的分辨率
     *
     * @param width  程序获取的width
     * @param height 程序获取的height
     */
    public static Map Resolution_Adaptation(int width, int height) {
        Properties properties = new Properties();
        //config为属性文件名，放在包com.test.config下，如果是放在src下，直接用config即可
        try {
            properties.load(new TEST().getClass().getResourceAsStream("/properties/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //用户系统分辨率
        String Dimension_width = properties.getProperty("Dimension_width");
        String Dimension_height = properties.getProperty("Dimension_height");
        java.awt.Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        double x1 = d.width / Double.parseDouble(Dimension_width);
        double x2 = d.height / Double.parseDouble(Dimension_height);
        width = (int) (width * x1);
        height = (int) (height * x2);
        Map map = new HashMap();
        map.put("width", width);
        map.put("height", height);
        return map;

    }

}
