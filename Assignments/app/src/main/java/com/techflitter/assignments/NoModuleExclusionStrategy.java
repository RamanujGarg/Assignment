package com.techflitter.assignments;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.Expose;

public class NoModuleExclusionStrategy implements ExclusionStrategy {
    private boolean isDeserialize;

    public NoModuleExclusionStrategy(boolean isDeserialize) {
        this.isDeserialize = isDeserialize;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes field) {
        return !(field.getAnnotation(Expose.class) == null || (isDeserialize ? field.getAnnotation(Expose.class).deserialize() : field.getAnnotation(Expose.class).serialize()));
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
