package com.example.jiangchuanfa.beijingnews2rd.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by crest on 2017/6/5.
 *
 *
 */

public class NoViewPager extends ViewPager{


    public NoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
