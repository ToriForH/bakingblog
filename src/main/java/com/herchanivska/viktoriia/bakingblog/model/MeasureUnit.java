package com.herchanivska.viktoriia.bakingblog.model;

public enum MeasureUnit {
    GRAMS,
    MILLILITERS,
    PIECES;

    public String getLowerCaseName() {
        return super.name().toLowerCase();
    }
}
