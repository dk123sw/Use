package me.drakeet.meizhi;

/**
 * Created by drakeet on 8/9/15.
 */
public class DrakeetFactory {

    protected static final Object monitor = new Object();
    static Drakeet sSingleton = null;
    public static final int pageSize = 20;

    public static Drakeet getSingleton() {
        synchronized (monitor) {
            if(sSingleton == null) {
                sSingleton = new DrakeetRetrofit().getService();
            }
            return sSingleton;
        }
    }
}
