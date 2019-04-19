package com.yzk.xtools.base.adapter.view;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.yzk.xtools.base.adapter.BaseAdapter;
import com.yzk.xtools.base.adapter.listener.OnItemClickListener;
import com.yzk.xtools.base.adapter.listener.OnItemLongClickListener;
import com.yzk.xtools.base.adapter.listener.OnItemTouchListener;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by BlingBling on 2017/1/9.
 */

public final class ItemView {

    private static final int ITEM_VIEW_ID = 1;

    private int mViewType;
    @LayoutRes
    private int mLayoutId;

    private Set<Integer> mClickViewIds;
    private Set<Integer> mLongClickViewIds;
    private Set<Integer> mTouchViewIds;

    public ItemView(@LayoutRes int layoutId) {
        this(layoutId, layoutId);
    }

    public ItemView(int viewType, @LayoutRes int layoutId) {
        this.mViewType = viewType;
        this.mLayoutId = layoutId;
    }

    public int getViewType() {
        return mViewType;
    }

    @LayoutRes
    public int getLayoutId() {
        return mLayoutId;
    }

    private void checkClickViewIds() {
        if (mClickViewIds == null) {
            mClickViewIds = new HashSet<>();
        }
    }

    private void checkLongClickViewIds() {
        if (mLongClickViewIds == null) {
            mLongClickViewIds = new HashSet<>();
        }
    }

    private void checkTouchViewIds() {
        if (mTouchViewIds == null) {
            mTouchViewIds = new HashSet<>();
        }
    }

    public ItemView listenerOnItemClick() {
        checkClickViewIds();
        mClickViewIds.add(ITEM_VIEW_ID);
        return this;
    }

    public ItemView listenerOnItemClick(@IdRes int viewId) {
        checkClickViewIds();
        mClickViewIds.add(viewId);
        return this;
    }

    public ItemView listenerOnItemLongClick() {
        checkLongClickViewIds();
        mLongClickViewIds.add(ITEM_VIEW_ID);
        return this;
    }

    public ItemView listenerOnItemLongClick(@IdRes int viewId) {
        checkLongClickViewIds();
        mLongClickViewIds.add(viewId);
        return this;
    }

    public ItemView listenerOnItemTouch() {
        checkTouchViewIds();
        mTouchViewIds.add(ITEM_VIEW_ID);
        return this;
    }

    public ItemView listenerOnItemTouch(@IdRes int viewId) {
        checkTouchViewIds();
        mTouchViewIds.add(viewId);
        return this;
    }

    public void bindListener(final BaseAdapter adapter, final RecyclerView.ViewHolder holder) {
        bindClickListener(adapter, holder, adapter.getOnItemClickListener());
        bindLongClickListener(adapter, holder, adapter.getOnItemLongClickListener());
        bindTouchListener(adapter, holder, adapter.getOnItemTouchListener());
    }

    private View findView(RecyclerView.ViewHolder holder, int viewId) {
        if (viewId == ITEM_VIEW_ID) {
            return holder.itemView;
        } else {
            return holder.itemView.findViewById(viewId);
        }
    }

    private void bindClickListener(final BaseAdapter adapter, final RecyclerView.ViewHolder holder, final OnItemClickListener listener) {
        if (mClickViewIds == null) {
            return;
        }
        for (Integer integer : mClickViewIds) {
            findView(holder, integer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    if (pos == RecyclerView.NO_POSITION) {
                        return;
                    }
                    pos = pos - adapter.getHeaderViewCount();
                    if (listener != null) {
                        listener.onItemClick(holder, v, pos);
                    }
                }
            });
        }
    }

    private void bindLongClickListener(final BaseAdapter adapter, final RecyclerView.ViewHolder holder, final OnItemLongClickListener listener) {
        if (mLongClickViewIds == null) {
            return;
        }
        for (Integer integer : mLongClickViewIds) {
            findView(holder, integer).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getAdapterPosition();
                    if (pos == RecyclerView.NO_POSITION) {
                        return false;
                    }
                    pos = pos - adapter.getHeaderViewCount();
                    if (listener != null) {
                        listener.onItemLongClick(holder, v, pos);
                    }
                    return false;
                }
            });
        }
    }

    private void bindTouchListener(final BaseAdapter adapter, final RecyclerView.ViewHolder holder, final OnItemTouchListener listener) {
        if (mTouchViewIds == null) {
            return;
        }
        for (Integer integer : mTouchViewIds) {
            findView(holder, integer).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int pos = holder.getAdapterPosition();
                    if (pos == RecyclerView.NO_POSITION) {
                        return false;
                    }
                    pos = pos - adapter.getHeaderViewCount();
                    if (listener != null) {
                        return listener.onItemTouch(holder, v, event, pos);
                    } else {
                        return false;
                    }
                }
            });
        }
    }
}
