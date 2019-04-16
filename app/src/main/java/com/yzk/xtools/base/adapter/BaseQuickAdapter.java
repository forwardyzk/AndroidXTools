package com.yzk.xtools.base.adapter;

import android.content.Context;
import android.view.View;

/**
 * Created by BlingBling on 2017/1/9.
 */

public abstract class BaseQuickAdapter<T> extends BaseAdapter<T, QuickViewHolder> {
    public BaseQuickAdapter() {
    }

    public BaseQuickAdapter(Context context) {
        super(context);
    }

    @Override
    protected QuickViewHolder createViewHolder(int viewType, View view) {
        return new QuickViewHolder(view);
    }

}
