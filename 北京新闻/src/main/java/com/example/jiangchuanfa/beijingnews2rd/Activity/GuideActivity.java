package com.example.jiangchuanfa.beijingnews2rd.Activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.jiangchuanfa.beijingnews2rd.R;
import com.example.jiangchuanfa.beijingnews2rd.Utils.DensityUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    @InjectView(R.id.vp)
    ViewPager vp;
    @InjectView(R.id.btn_start_main)
    Button btnStartMain;
    @InjectView(R.id.ll_point_group)
    LinearLayout llPointGroup;
    @InjectView(R.id.activity_guide)
    RelativeLayout activityGuide;
    @InjectView(R.id.iv_red_point)
    ImageView ivRedPoint;

    ArrayList<ImageView> imageViews;
    int[] ids = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private int leftMargin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.inject(this);

        initData();

        vp.setAdapter(new MyAdapter());

        vp.addOnPageChangeListener(new MyOnPageChangeListener());

        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                leftMargin = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
                Log.e("TAG", "leftMargin==" + leftMargin);
            }
        });
    }

    private void initData() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.guide_point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(GuideActivity.this, 10), DensityUtil.dip2px(GuideActivity.this, 10));
            point.setLayoutParams(params);
            if (i != 0) {
                params.leftMargin = DensityUtil.dip2px(GuideActivity.this, 10);
            }
            llPointGroup.addView(point);
        }

    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @OnClick(R.id.btn_start_main)
    public void onViewClicked() {
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float left = leftMargin * (positionOffset + position);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
            params.leftMargin = (int) left;
            ivRedPoint.setLayoutParams(params);
            Log.e("TAG","position=="+position+",positionOffset=="+positionOffset+",positionOffsetPixels=="+positionOffsetPixels);

        }

        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {
                btnStartMain.setVisibility(View.VISIBLE);
            } else {
                btnStartMain.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
