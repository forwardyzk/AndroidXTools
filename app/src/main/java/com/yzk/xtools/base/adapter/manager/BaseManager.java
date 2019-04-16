package com.yzk.xtools.base.adapter.manager;

import android.view.View;
import android.view.ViewGroup;

import com.yzk.xtools.base.adapter.BaseAdapter;
import com.yzk.xtools.base.adapter.view.AdapterView;

import java.util.ArrayList;

/**
 * Created by BlingBling on 2016/11/21.
 */

public abstract class BaseManager<T extends ViewGroup> {

    protected BaseAdapter mQuickAdapter;

    private T mLayout;
    protected ArrayList<AdapterView> mViews = new ArrayList<>();
    private boolean mShouldBindLayout = false;

    public BaseManager(BaseAdapter quickAdapter) {
        mQuickAdapter = quickAdapter;
    }

    public final T getLayout() {
        return mLayout;
    }

    public abstract int getItemViewCount();

    public final T createLayout(ViewGroup parent) {
        if (mLayout == null) {
            mLayout = onCreateLayout(parent);
        }
        return mLayout;
    }

    public abstract T onCreateLayout(ViewGroup parent);

    public final void bindLayout() {
        if (mShouldBindLayout) {
            mLayout.removeAllViews();
            for (int i = 0, size = mViews.size(); i < size; i++) {
                View view = mViews.get(i).getViewOrCreate(mLayout);
                ViewGroup parent = (ViewGroup) view.getParent();
                if(parent!=null){
                    parent.removeAllViews();
                }
                mLayout.addView(view);
            }
            mShouldBindLayout = false;
        }
    }

    public final void checkBindLayout() {
        mShouldBindLayout = true;
        if (mLayout != null) {
            bindLayout();
        }
    }

    /**
     * 从RecyclerView中移除此View，因为Header、Footer、Empty、LoadMore移除添加用的是同一个view，
     * RecyclerView默认有动画，防止移除添加同时操作的时候提示要添加的View没有移除
     */
    protected void removeThisView() {
        final T view = getLayout();
        if (view == null) {
            return;
        }
        ViewGroup vp = (ViewGroup) view.getParent();
        if (vp != null) {
            vp.removeView(getLayout());
        }
    }

    public void onViewAttachedToWindow() {
        for (int i = 0, size = mViews.size(); i < size; i++) {
            mViews.get(i).onViewAttachedToWindow();
        }
    }

    public void onViewDetachedFromWindow() {
        for (int i = 0, size = mViews.size(); i < size; i++) {
            mViews.get(i).onViewDetachedFromWindow();
        }
    }
}
