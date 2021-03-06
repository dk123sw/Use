/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.drakeet.meizhi;

import com.squareup.otto.Bus;

/**
 * 爱之车~，爱之座位~~
 * Created by drakeet on 8/13/15.
 * 这个otto在这里的用途是在内容页横屏时点击回退返回GankActivity
 */
public class LoveBus {

    private static final Bus BUS = new Bus();


    public static Bus getLovelySeat() {return BUS;}


    private LoveBus() {}
}