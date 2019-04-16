package com.yzk.xtools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yzk.xtools.base.adapter.listener.OnItemClickListener;
import com.yzk.xtools.databinding.ActivityMainBinding;
import com.yzk.xtools.main.adapter.ToolsAdapter;
import com.yzk.xtools.page.ToolsHelper;
import com.yzk.xtools.utils.ToastUtil;

/**
 * @Description: 主页
 * @Author: yang
 * @Time: 2019/4/16 14:55
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding viewDataBinding;
    private ToolsAdapter mAdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initRecycler();
    }

    private void initRecycler() {
        mAdater = new ToolsAdapter();
        viewDataBinding.mainRecycler.setLayoutManager(new LinearLayoutManager(this));
        viewDataBinding.mainRecycler.setAdapter(mAdater);
        mAdater.setNewData(ToolsHelper.getToolsList());

        mAdater.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, View view, int position) {
                ToastUtil.showToast(mAdater.getItem(position).aClass.getSimpleName());
            }
        });

    }
}
