package com.etiennelawlor.moviehub.presentation.common.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.etiennelawlor.moviehub.util.CustomFontUtils;

/**
 * Created by etiennelawlor on 12/14/15.
 */
public class CustomFontButton extends AppCompatButton {

    // region Constructors
    public CustomFontButton(Context context) {
        super(context);
        init(context, null);
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }
    // endregion

    // region Helper Methods
    private void init(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            CustomFontUtils.applyCustomFont(this, context, attrs);
        }
    }
    // endregion
}
