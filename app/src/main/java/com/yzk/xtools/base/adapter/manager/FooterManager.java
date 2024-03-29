package com.yzk.xtools.base.adapter.manager;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yzk.xtools.base.adapter.BaseAdapter;
import com.yzk.xtools.base.adapter.view.AdapterView;


/**
 * Created by BlingBling on 2016/12/9.
 */

public class FooterManager extends BaseManager<LinearLayout> {

    private int mOrientation = LinearLayout.VERTICAL;

    public FooterManager(BaseAdapter quickAdapter) {
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

    public void addFooterView(AdapterView footer) {
        addFooterView(footer, mViews.size());
    }

    public void addFooterView(AdapterView footer, int index) {
        if (footer == null) {
            throw new NullPointerException();
        }
        footer.setQuickAdapter(mQuickAdapter);
        mViews.add(index, footer);
        checkBindLayout();
        if (mViews.size() == 1) {
            int position = getFooterViewNotifyPosition();
            if (position != -1) {
                mQuickAdapter.notifyItemInserted(position);
            }
        }
    }

    public void setFooterView(AdapterView footer) {
        setFooterView(footer, 0);
    }

    public void setFooterView(AdapterView footer, int index) {
        if (footer == null) {
            throw new NullPointerException();
        }
        final int size = mViews.size();
        if (index >= size) {
            index = size;
            addFooterView(footer, index);
        } else {
            footer.setQuickAdapter(mQuickAdapter);
            mViews.set(index, footer);
            checkBindLayout();
        }
    }

    public void removeFooterView(AdapterView footer) {
        if (mViews.size() == 0) return;

        mViews.remove(footer);
        checkBindLayout();
        if (mViews.size() == 0) {
            int position = getFooterViewNotifyPosition();
            if (position != -1) {
                removeThisView();
                mQuickAdapter.notifyItemRemoved(position);
            }
        }
    }

    public void removeAllFooterView() {
        if (mViews.size() == 0) return;

        mViews.clear();
        checkBindLayout();
        int position = getFooterViewNotifyPosition();
        if (position != -1) {
            removeThisView();
            mQuickAdapter.notifyItemRemoved(position);
        }
    }

    private int getFooterViewNotifyPosition() {
        //Return to footer view notify position
        if (mQuickAdapter.getEmptyViewCount() == 1) {
            int position = 1;
            if (mQuickAdapter.emptyManager().isHeaderAndEmptyEnable() && mQuickAdapter.getHeaderViewCount() != 0) {
                position++;
            }
            if (mQuickAdapter.emptyManager().isFooterAndEmptyEnable()) {
                return position;
            } else {
                return -1;
            }
        } else {
            return mQuickAdapter.getHeaderViewCount() + mQuickAdapter.getDataViewCount();
        }
    }
}
