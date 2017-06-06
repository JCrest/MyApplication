package com.example.jiangchuanfa.beijingnews2rd.DetailPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiangchuanfa.beijingnews2rd.Base.MenuDetailBasePager;
import com.example.jiangchuanfa.beijingnews2rd.DoMain.NewsCenterBean;
import com.example.jiangchuanfa.beijingnews2rd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crest on 2017/6/6.
 */

public class NewsMenuDetailPager extends MenuDetailBasePager {
    private final List<NewsCenterBean.DataBean.ChildrenBean> datas;
    ViewPager viewpager;
    List<TabDetailPager> tabDetailPagers;


    public NewsMenuDetailPager(Context context, List<NewsCenterBean.DataBean.ChildrenBean> children) {
        super(context);
        this.datas = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_news_menu_detail, null);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        tabDetailPagers = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {

            tabDetailPagers.add(new TabDetailPager(context, datas.get(i)));

        }
        viewpager.setAdapter(new NewsMenuDetailPagerAdapter());

    }


    private class NewsMenuDetailPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return tabDetailPagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = tabDetailPagers.get(position);
            View rootView = tabDetailPager.rootView;
            container.addView(rootView);
            tabDetailPager.initData();
            return rootView;
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
}



