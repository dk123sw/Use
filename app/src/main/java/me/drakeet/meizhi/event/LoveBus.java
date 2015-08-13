package me.drakeet.meizhi.event;

import com.squareup.otto.Bus;

/**
 * 爱之车~，爱之座位~~
 * Created by drakeet on 8/13/15.
 */
public class LoveBus {

    private static final Bus BUS = new Bus();

    public static Bus getLovelySeat() {return BUS;}

    private LoveBus() {}
}