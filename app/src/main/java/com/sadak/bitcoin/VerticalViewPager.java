package com.sadak.bitcoin;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class VerticalViewPager extends ViewPager {
    private static final String TAG = "VerticalViewPager";
    private static final boolean DEBUG = true;

    private float mLastMotionX;
    private float mLastMotionY;
    private float mTouchSlop;
    private boolean mVerticalDrag;
    private boolean mHorizontalDrag;

    // Vertical transit page transformer
    private final ViewPager.PageTransformer mPageTransformer = new ViewPager.PageTransformer() {
        @Override
        public void transformPage(View view, float position) {
            final int pageWidth = view.getWidth();
            final int pageHeight = view.getHeight();
            if (position < -1) {
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 1) {
                view.setAlpha(1);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // set Y position to swipe in from top
                float yPosition = position * pageHeight;
                view.setTranslationY(yPosition);



            } else {
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    };

    public VerticalViewPager(Context context) {
        super(context, null);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        init();
    }

    private void init() {
        // Make page transit vertical
        setPageTransformer(true, mPageTransformer);
        // Get rid of the overscroll drawing that happens on the left and right (the ripple)
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        final float x = ev.getX();
        final float y = ev.getY();

        if (DEBUG) Log.v(TAG, "onTouchEvent " + x + ", " + y);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mLastMotionX = x;
                mLastMotionY = y;
                if (!super.onTouchEvent(ev))
                    return false;
                return verticalDrag(ev);
            }
            case MotionEvent.ACTION_MOVE: {
                final float xDiff = Math.abs(x - mLastMotionX);
                final float yDiff = Math.abs(y - mLastMotionY);
                if (!mHorizontalDrag && !mVerticalDrag) {
                    if (yDiff > mTouchSlop && yDiff > xDiff) { // Swiping left and right
                        mHorizontalDrag = true;
                    } else if (xDiff > mTouchSlop && xDiff > yDiff) { //Swiping up and down
                        mVerticalDrag = true;
                    }
                }
                if (mHorizontalDrag) {
                    return super.onTouchEvent(ev);
                } else if (mVerticalDrag) {
                    return verticalDrag(ev);
                }
            }
            case MotionEvent.ACTION_UP: {
                if (mHorizontalDrag) {
                    mHorizontalDrag = false;
                    return super.onTouchEvent(ev);
                }
                if (mVerticalDrag) {
                    mVerticalDrag = false;
                    return verticalDrag(ev);
                }

            }
        }
        // Set both flags to false in case user lifted finger in the parent view pager
        mHorizontalDrag = false;
        mVerticalDrag = false;

        return false;
    }


    private boolean verticalDrag(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();
        ev.setLocation(x, y);
        return super.onTouchEvent(ev);
    }
}
