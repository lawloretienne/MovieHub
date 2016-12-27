package com.etiennelawlor.moviehub.utilities;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by etiennelawlor on 12/20/16.
 */

/**
 * Utility methods for working with animations.
 */

public class AnimationUtility {

    // region Constants
    public static final long DURATION = 1000L;
    public static final long DURATION_2 = 400L;
    // endregion

    // region Static Variables
    private static Interpolator fastOutSlowIn;
    private static Interpolator fastOutLinearIn;
    private static Interpolator linearOutSlowIn;
    private static Interpolator linear;
    // endregion

    // region Constructors
    private AnimationUtility() { }
    // endregion

    public static void animateBackgroundColorChange(final View view, int startColor, int endColor){
        ValueAnimator infoBackgroundColorAnim = ValueAnimator.ofArgb(startColor, endColor);
        infoBackgroundColorAnim.addUpdateListener(new ValueAnimator
                .AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });
        infoBackgroundColorAnim.setDuration(DURATION);
        infoBackgroundColorAnim.setInterpolator(
                getFastOutSlowInInterpolator(view.getContext()));
        infoBackgroundColorAnim.start();
    }

    public static void animateTextColorChange(final TextView tv, int startColor, int endColor){
        ValueAnimator titleTextColorAnim = ValueAnimator.ofArgb(startColor, endColor);
        titleTextColorAnim.addUpdateListener(new ValueAnimator
                .AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv.setTextColor((int) animation.getAnimatedValue());
            }
        });
        titleTextColorAnim.setDuration(DURATION);
        titleTextColorAnim.setInterpolator(
                getFastOutSlowInInterpolator(tv.getContext()));
        titleTextColorAnim.start();
    }

    public static Animation getExpandHeightAnimation(final View v, final int targetHeight){
        Animation animation = new Animation(){
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        return animation;
    }

    public static int getTargetHeight(View v){
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(widthSpec, heightSpec);

        int targetHeight = v.getMeasuredHeight();
        return targetHeight;
    }


    private static int getInitialHeight(View v){
        int initialHeight = v.getMeasuredHeight();
        return initialHeight;
    }

    public static void expand(final View v) {
        final int targetHeight = getTargetHeight(v);

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
//        Animation a = new Animation(){
//            @Override
//            protected void applyTransformation(float interpolatedTime, Transformation t) {
//                v.getLayoutParams().height = interpolatedTime == 1
//                        ? LinearLayout.LayoutParams.WRAP_CONTENT
//                        : (int)(targetHeight * interpolatedTime);
//                v.requestLayout();
//            }
//
//            @Override
//            public boolean willChangeBounds() {
//                return true;
//            }
//        };

        Animation animation = AnimationUtility.getExpandHeightAnimation(v, targetHeight);


        // 6dp/ms
//        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density) * 6);
        animation.setDuration(DURATION_2);
//        a.setInterpolator(new DecelerateInterpolator());
        v.startAnimation(animation);
    }

    public static void collapse(final View v) {
        final int initialHeight = getInitialHeight(v);

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 6dp/ms
//        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density) * 6);
        a.setDuration(DURATION_2);
//        a.setInterpolator(new DecelerateInterpolator());

        v.startAnimation(a);
    }

    public static Interpolator getFastOutSlowInInterpolator(Context context) {
        if (fastOutSlowIn == null) {
            fastOutSlowIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.fast_out_slow_in);
        }
        return fastOutSlowIn;
    }

    public static Interpolator getFastOutLinearInInterpolator(Context context) {
        if (fastOutLinearIn == null) {
            fastOutLinearIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.fast_out_linear_in);
        }
        return fastOutLinearIn;
    }

    public static Interpolator getLinearOutSlowInInterpolator(Context context) {
        if (linearOutSlowIn == null) {
            linearOutSlowIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.linear_out_slow_in);
        }
        return linearOutSlowIn;
    }

    public static Interpolator getLinearInterpolator() {
        if (linear == null) {
            linear = new LinearInterpolator();
        }
        return linear;
    }
}
