package com.example.jiangchuanfa.beijingnews2rd.Fragment;

import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.jiangchuanfa.beijingnews2rd.Base.BaseFragment;
import com.example.jiangchuanfa.beijingnews2rd.Base.BasePager;
import com.example.jiangchuanfa.beijingnews2rd.Pager.HomePager;
import com.example.jiangchuanfa.beijingnews2rd.Pager.NewsPager;
import com.example.jiangchuanfa.beijingnews2rd.Pager.SettingPager;
import com.example.jiangchuanfa.beijingnews2rd.R;
import com.example.jiangchuanfa.beijingnews2rd.View.NoViewPager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by crest on 2017/6/3.
 *
 *
 */

public class ContentFragment extends BaseFragment {

    @InjectView(R.id.vp)
    NoViewPager vp;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    ArrayList<BasePager> pagers;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.content_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        pagers = new ArrayList<>();
        pagers.add(new HomePager(context));
        pagers.add(new NewsPager(context));
        pagers.add(new SettingPager(context));

        vp.setAdapter(new MyAdapter());

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case  R.id.rb_home:
                        vp.setCurrentItem(0,false);
                        Log.e("TAG","选择变化监听的活动0");
                        break;
                    case  R.id.rb_news:
                        vp.setCurrentItem(1,false);
                        Log.e("TAG","选择变化监听的活动1");
                        break;
                    case  R.id.rb_setting:
                        vp.setCurrentItem(2,false);
                        Log.e("TAG","选择变化监听的活动2");
                        break;
                }
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagers.get(position).initData();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagers.get(0).initData();

        rgMain.check(R.id.rb_home);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = pagers.get(position);
            View rootView = basePager.rootView;
//            basePager.initData();//(在这里调用次方法会预加载数据)
            container.addView(rootView);
            Log.e("TAG","适配器的活动--------------"+position);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
