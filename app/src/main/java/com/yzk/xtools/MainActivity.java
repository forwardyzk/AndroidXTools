package com.yzk.xtools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.yzk.xtools.base.adapter.listener.OnItemClickListener;
import com.yzk.xtools.databinding.ActivityMainBinding;
import com.yzk.xtools.main.adapter.ToolsAdapter;
import com.yzk.xtools.page.ToolsHelper;
import com.yzk.xtools.page.data.UtilBean;
import com.yzk.xtools.utils.KeyboardUtil;
import com.yzk.xtools.utils.ToastUtil;

import java.util.List;

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
        initSearch();
    }

    private final String SEARCH = "搜索";
    private final String RESET = "重置";

    private void initSearch() {
        viewDataBinding.mianSearch.setText(SEARCH);
        viewDataBinding.mianSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SEARCH.equals(viewDataBinding.mianSearch.getText().toString())) {
                    String searchKey = viewDataBinding.mianSearchEdit.getText().toString();
                    if (TextUtils.isEmpty(searchKey)) {
                        ToastUtil.showToast("请输入关键字");
                        return;
                    }

                    List<UtilBean> toolsListByKey = ToolsHelper.getToolsListByKey(searchKey);
                    if (toolsListByKey.size() == 0) {
                        ToastUtil.showToast("未找到该关键字对应的工具类");
                        return;
                    }
                    mAdater.setNewData(toolsListByKey);
                    viewDataBinding.mianSearch.setText(RESET);
                } else {
                    viewDataBinding.mianSearch.setText("");
                    mAdater.setNewData(ToolsHelper.getToolsList());
                    viewDataBinding.mianSearch.setText(SEARCH);
                }


            }
        });
        viewDataBinding.mianSearchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyboardUtil.isEnterKey(keyCode, event)) {
                    KeyboardUtil.hideSoftInput(MainActivity.this);
                    return true;
                }
                return false;
            }
        });
    }


    private void initRecycler() {
        mAdater = new ToolsAdapter();
        viewDataBinding.mainRecycler.setLayoutManager(new LinearLayoutManager(this));
        viewDataBinding.mainRecycler.setAdapter(mAdater);
        mAdater.setNewData(ToolsHelper.getToolsList());

        mAdater.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, View view, int position) {
                ToastUtil.showLongToast(mAdater.getItem(position).utilName);
            }
        });

    }
}
