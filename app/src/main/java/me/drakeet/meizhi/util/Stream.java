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

package me.drakeet.meizhi.util;

import rx.Observable;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 15/11/11 12:00
 */
public class Stream<T> extends Observable<T> {
    /**
     * Creates an Observable with a Function to execute when it is subscribed to.
     * <p>
     * <em>Note:</em> Use {@link #create(OnSubscribe)} to create an Observable, instead
     * of
     * this constructor,
     * unless you specifically have a need for inheritance.
     *
     * @param f {@link OnSubscribe} to be executed when {@link #subscribe(Subscriber)} is
     * called
     */
    protected Stream(OnSubscribe<T> f) {
        super(f);
    }
}
