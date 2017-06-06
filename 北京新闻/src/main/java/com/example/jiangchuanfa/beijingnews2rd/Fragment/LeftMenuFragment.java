package com.example.jiangchuanfa.beijingnews2rd.Fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Activity.MainActivity;
import com.example.jiangchuanfa.beijingnews2rd.Base.BaseFragment;
import com.example.jiangchuanfa.beijingnews2rd.DoMain.NewsCenterBean;
import com.example.jiangchuanfa.beijingnews2rd.Pager.NewsPager;
import com.example.jiangchuanfa.beijingnews2rd.R;
import com.example.jiangchuanfa.beijingnews2rd.Utils.DensityUtil;

import java.util.List;

/**
 * Created by crest on 2017/6/3.
 */

public class LeftMenuFragment extends BaseFragment {
    private ListView listView;
    private LeftMenuAdapter adapter;
    private List<NewsCenterBean.DataBean> datas;
    private int prePosition = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View initView() {
        listView = new ListView(context);
        //listView.setDivider(getResources().getDrawable(R.drawable.button_red_pressed));
        listView.setDivider(getResources().getDrawable(R.drawable.button_red_pressed,null));
        listView.setDividerHeight(10);
        listView.setPadding(0, DensityUtil.px2dip(context, 400), 0, 0);
        //设置ListView的item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //记录位置
                prePosition = position;
                //适配器刷新
                adapter.notifyDataSetChanged();//getCount-->getView

                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();//关<->开

                //根据位置切换到对应的详情页面
                switchPager(prePosition);

            }
        });
        return listView;
    }



    @Override
    public void initData() {
        super.initData();

    }

    //为左侧菜单设置数据的方法
    public void setData(List<NewsCenterBean.DataBean> datas) {
        this.datas = datas;

        adapter = new LeftMenuAdapter();
        listView.setAdapter(adapter);

        switchPager(prePosition);


    }

    private void switchPager(int position) {
        MainActivity mainActivity = (MainActivity) context;
        //2.得到ContentFragment
        ContentFragment contentFragment = mainActivity.getContentFragment();
        //3.得到NewsPager
        NewsPager newsPager = contentFragment.getNewsPager();
        //4.调用切换方法
        newsPager.swichPager(position);


    }

    private class LeftMenuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenu, null);
            if (prePosition == position) {
                //高亮
                textView.setEnabled(true);
            } else {
                //默认
                textView.setEnabled(false);
            }
            //根据位置得到数据
            NewsCenterBean.DataBean dataBean = datas.get(position);
            textView.setText(dataBean.getTitle());
            return textView;
        }
    }
}
