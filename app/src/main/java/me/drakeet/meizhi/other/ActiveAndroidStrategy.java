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
        return clazz != null && (clazz.equals(excludedThisClass) || shouldSkipClass(
            clazz.getSuperclass()));
    }
}