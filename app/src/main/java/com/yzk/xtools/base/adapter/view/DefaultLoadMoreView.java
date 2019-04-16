package com.yzk.xtools.base.adapter.view;


import com.yzk.xtools.R;
import com.yzk.xtools.base.adapter.status.LoadMoreView;

/**
 * Created by BlingBling on 2016/11/11.
 */

public class DefaultLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.core_adapter_load_more;
    }

    @Override
    public int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    public int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    public int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}