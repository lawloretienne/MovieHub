package com.etiennelawlor.moviehub.util;

/**
 * Created by etiennelawlor on 12/19/16.
 */

public class MathUtility {

    private MathUtility() { }

    public static float constrain(float min, float max, float v) {
        return Math.max(min, Math.min(max, v));
    }
}
