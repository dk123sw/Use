package me.drakeet.meizhi;

/**
 * Created by drakeet on 8/9/15.
 */
public class DrakeetFactory {

    protected static final Object monitor = new Object();
    static Drakeet sSingleton = null;
    public static final int meizhiSize = 10;
    public static final int gankSize = 5;

    public static Drakeet getSingleton() {
        synchronized (monitor) {
            if (sSingleton == null) {
                sSingleton = new DrakeetRetrofit().getService();
            }
            return sSingleton;
        }
    }
}
