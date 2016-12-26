package com.etiennelawlor.moviehub.utilities;

import android.graphics.Typeface;

import com.etiennelawlor.trestle.library.Span;
import com.etiennelawlor.trestle.library.Trestle;

/**
 * Created by etiennelawlor on 4/13/16.
 */
public class TrestleUtility {

    public static CharSequence getFormattedText(String text, Typeface font) {
        return Trestle.getFormattedText(
                new Span.Builder(text)
                        .typeface(font)
                        .build());
    }

    public static CharSequence getFormattedText(String text, Typeface font, int size) {
        return Trestle.getFormattedText(
                new Span.Builder(text)
                        .typeface(font)
                        .absoluteSize(size)
                        .build());
    }
}
