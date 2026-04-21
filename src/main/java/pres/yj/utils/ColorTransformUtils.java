package pres.yj.utils;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/21
 * 颜色转换工具类
 */
public class ColorTransformUtils {

    private ColorTransformUtils() {

    }

    /**
     * 获取标准颜色 (将数据万象的 5 位色值转为 6 位)
     *
     * @param color 颜色
     * @return 标准颜色
     */
    public static String getStandardColor(String color) {
        if (color.length() == 7) {
            color = color.substring(0, 4) + "0" + color.substring(4, 7);
        }
        return color;
    }
}
