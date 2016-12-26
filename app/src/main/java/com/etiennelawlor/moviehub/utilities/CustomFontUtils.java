package com.etiennelawlor.moviehub.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.etiennelawlor.moviehub.R;

/**
 * Created by etiennelawlor on 12/14/15.
 */
public class CustomFontUtils {

    // region Constants
    private static final int LATO_BLACK = 0;
    private static final int LATO_BLACK_ITALIC = 1;
    private static final int LATO_BOLD = 2;
    private static final int LATO_BOLD_ITALIC = 3;
    private static final int LATO_HAIRLINE = 4;
    private static final int LATO_HAIRLINE_ITALIC = 5;
    private static final int LATO_HEAVY = 6;
    private static final int LATO_HEAVY_ITALIC = 7;
    private static final int LATO_ITALIC = 8;
    private static final int LATO_LIGHT = 9;
    private static final int LATO_LIGHT_ITALIC = 10;
    private static final int LATO_MEDIUM = 11;
    private static final int LATO_MEDIUM_ITALIC = 12;
    private static final int LATO_REGULAR = 13;
    private static final int LATO_SEMIBOLD = 14;
    private static final int LATO_SEMIBOLD_ITALIC = 15;
    private static final int LATO_THIN = 16;
    private static final int LATO_THIN_ITALIC = 17;
    // endregion

    public static void applyCustomFont(TextView customFontTextView, Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomFontTextView);

        try {
            int font = attributeArray.getInteger(R.styleable.CustomFontTextView_textFont, 13);
            Typeface customFont = getTypeface(context, font);
            customFontTextView.setTypeface(customFont);
        } finally {
            attributeArray.recycle();
        }
    }

    private static Typeface getTypeface(Context context, int font) {
        switch (font) {
            case LATO_BLACK:
                return FontCache.getTypeface("Lato-Black.ttf", context);
            case LATO_BLACK_ITALIC:
                return FontCache.getTypeface("Lato-BlackItalic.ttf", context);
            case LATO_BOLD:
                return FontCache.getTypeface("Lato-Bold.ttf", context);
            case LATO_BOLD_ITALIC:
                return FontCache.getTypeface("Lato-BoldItalic.ttf", context);
            case LATO_HAIRLINE:
                return FontCache.getTypeface("Lato-Hairline.ttf", context);
            case LATO_HAIRLINE_ITALIC:
                return FontCache.getTypeface("Lato-HairlineItalic.ttf", context);
            case LATO_HEAVY:
                return FontCache.getTypeface("Lato-Heavy.ttf", context);
            case LATO_HEAVY_ITALIC:
                return FontCache.getTypeface("Lato-HeavyItalic.ttf", context);
            case LATO_ITALIC:
                return FontCache.getTypeface("Lato-Italic.ttf", context);
            case LATO_LIGHT:
                return FontCache.getTypeface("Lato-Light.ttf", context);
            case LATO_LIGHT_ITALIC:
                return FontCache.getTypeface("Lato-LightItalic.ttf", context);
            case LATO_MEDIUM:
                return FontCache.getTypeface("Lato-Medium.ttf", context);
            case LATO_MEDIUM_ITALIC:
                return FontCache.getTypeface("Lato-MediumItalic.ttf", context);
            case LATO_REGULAR:
                return FontCache.getTypeface("Lato-Regular.ttf", context);
            case LATO_SEMIBOLD:
                return FontCache.getTypeface("Lato-Semibold.ttf", context);
            case LATO_SEMIBOLD_ITALIC:
                return FontCache.getTypeface("Lato-SemiboldItalic.ttf", context);
            case LATO_THIN:
                return FontCache.getTypeface("Lato-Thin.ttf", context);
            case LATO_THIN_ITALIC:
                return FontCache.getTypeface("Lato-ThinItalic.ttf", context);
            default:
                // no matching font found
                // return null so Android just uses the standard font (Roboto)
                return null;
        }
    }
}
