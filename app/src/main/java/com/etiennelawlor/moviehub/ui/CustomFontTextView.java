package com.etiennelawlor.moviehub.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.etiennelawlor.moviehub.utilities.CustomFontUtils;

/**
 * Created by etiennelawlor on 12/14/15.
 */
public class CustomFontTextView extends AppCompatTextView {

    // region Constructors
    public CustomFontTextView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
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
