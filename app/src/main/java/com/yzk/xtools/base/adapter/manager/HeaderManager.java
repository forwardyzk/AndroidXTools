package com.yzk.xtools.base.adapter.manager;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yzk.xtools.base.adapter.BaseAdapter;
import com.yzk.xtools.base.adapter.view.AdapterView;


/**
 * Created by BlingBling on 2016/12/9.
 */

public class HeaderManager extends BaseManager<LinearLayout> {

    private int mOrientation = LinearLayout.VERTICAL;

    public HeaderManager(BaseAdapter quickAdapter) {
        super(quickAdapter);
    }

    @Override
    public int getItemViewCount() {
        if (mViews.size() == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public LinearLayout onCreateLayout(ViewGroup parent) {
        final LinearLayout linearLayout = new LinearLayout(parent.getContext());
        if (mOrientation == LinearLayout.VERTICAL) {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT));
        } else {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                    RecyclerView.LayoutParams.MATCH_PARENT));
        }
        return linearLayout;
    }

    public void setOrientation(int orientation) {
        if (orientation != LinearLayout.VERTICAL && orientation != LinearLayout.HORIZONTAL) {
            throw new IllegalArgumentException();
        } else {
            mOrientation = orientation;
        }
    }

    public void addHeaderView(AdapterView header) {
        addHeaderView(header, mViews.size());
    }

    public void addHeaderView(AdapterView header, int index) {
        if (header == null) {
            throw new NullPointerException();
        }
        header.setQuickAdapter(mQuickAdapter);
        mViews.add(index, header);
        checkBindLayout();
        if (mViews.size() == 1) {
            int position = getHeaderViewNotifyPosition();
            if (position != -1) {
                mQuickAdapter.notifyItemInserted(position);
            }
        }
    }

    public void setHeaderView(AdapterView header) {
        setHeaderView(header, 0);
    }

    public void setHeaderView(AdapterView header, int index) {
        if (header == null) {
            throw new NullPointerException();
        }
        final int size = mViews.size();
        if (index >= size) {
            index = size;
            addHeaderView(header, index);
        } else {
            header.setQuickAdapter(mQuickAdapter);
            mViews.set(index, header);
            checkBindLayout();
        }
    }

    public void removeHeaderView(AdapterView header) {
        if (mViews.size() == 0) return;

        mViews.remove(header);
        checkBindLayout();
        if (mViews.size() == 0) {
            int position = getHeaderViewNotifyPosition();
            if (position != -1) {
                removeThisView();
                mQuickAdapter.notifyItemRemoved(position);
            }
        }
    }

    public void removeAllHeaderView() {
        if (mViews.size() == 0) return;

        mViews.clear();
        checkBindLayout();
        int position = getHeaderViewNotifyPosition();
        if (position != -1) {
            removeThisView();
            mQuickAdapter.notifyItemRemoved(position);
        }
    }

    private int getHeaderViewNotifyPosition() {
        //Return to header view notify position
        if (mQuickAdapter.getEmptyViewCount() == 1) {
            if (mQuickAdapter.emptyManager().isHeaderAndEmptyEnable()) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}
