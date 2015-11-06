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

package me.drakeet.meizhi.other;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 8/17/15 22:01
 */
public class ActiveAndroidStrategy implements ExclusionStrategy {

    private Class<?> excludedThisClass;
    private Class<?> excludedThisClassFields;


    public ActiveAndroidStrategy(Class<?> excludedThisClass, Class<?> excludedThisClassFields) {
        this.excludedThisClass = excludedThisClass;
        this.excludedThisClassFields = excludedThisClassFields;
    }


    @Override public boolean shouldSkipField(FieldAttributes attributes) {
        return attributes.getDeclaringClass().equals(excludedThisClassFields);
    }


    @Override public boolean shouldSkipClass(Class<?> clazz) {
        return clazz != null && (clazz.equals(excludedThisClass) ||
                shouldSkipClass(clazz.getSuperclass()));
    }
}