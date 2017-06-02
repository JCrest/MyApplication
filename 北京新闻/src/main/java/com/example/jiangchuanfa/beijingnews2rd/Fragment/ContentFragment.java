package com.example.jiangchuanfa.beijingnews2rd.Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.jiangchuanfa.beijingnews2rd.Base.BaseFragment;
import com.example.jiangchuanfa.beijingnews2rd.Base.BasePager;
import com.example.jiangchuanfa.beijingnews2rd.Pager.HomePager;
import com.example.jiangchuanfa.beijingnews2rd.Pager.NewsPager;
import com.example.jiangchuanfa.beijingnews2rd.Pager.SettingPager;
import com.example.jiangchuanfa.beijingnews2rd.R;

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
    ViewPager vp;
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
            basePager.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
