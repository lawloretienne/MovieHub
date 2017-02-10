package com.etiennelawlor.moviehub.ui.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by etiennelawlor on 1/8/15.
 */
public class HorizontalLinearItemDecoration extends RecyclerView.ItemDecoration {

    // region Member Variables
    private int space;
    // endregion

    // region Constructors
    public HorizontalLinearItemDecoration(int space) {
        this.space = space;
    }
    // endregion

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int childCount = parent.getAdapter().getItemCount();

        outRect.top = space;

        if (position == 0) {
            outRect.left = space;
            outRect.right = space / 2;
        } else if (position == childCount - 1) {
            outRect.left = space / 2;
            outRect.right = space;
        } else {
            outRect.left = space / 2;
            outRect.right = space / 2;
        }

        outRect.bottom = space;
    }
}
