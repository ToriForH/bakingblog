package com.herchanivska.viktoriia.bakingblog.model;

public enum MeasureUnit {
    GRAMS,
    MILLILITERS,
    PIECES;

    public String getName() {
        return super.name().toLowerCase();
    }
}
