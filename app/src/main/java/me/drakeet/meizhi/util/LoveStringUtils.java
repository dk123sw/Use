package me.drakeet.meizhi.util;

/**
 * Created by drakeet on 8/14/15.
 */
public class LoveStringUtils {

    public static String getVideoPreviewImageUrl(String resp) {
        int s0 = resp.indexOf("<h1>休息视频</h1>");
        if (s0 == -1) return null;

        int s1 = resp.indexOf("<img", s0);
        int s2 = resp.indexOf("http:", s1);
        int e2 = resp.indexOf(".jpg", s2) + ".jpg".length();

        return resp.substring(s2, e2);
    }
}
