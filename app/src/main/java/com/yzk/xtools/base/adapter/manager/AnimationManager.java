package com.yzk.xtools.base.adapter.manager;

import android.support.v7.widget.RecyclerView;

import com.yzk.xtools.base.adapter.BaseAdapter;
import com.yzk.xtools.base.adapter.animation.AlphaInAnimation;
import com.yzk.xtools.base.adapter.animation.BaseAnimation;
import com.yzk.xtools.base.adapter.animation.ScaleInAnimation;
import com.yzk.xtools.base.adapter.animation.SlideInBottomAnimation;
import com.yzk.xtools.base.adapter.animation.SlideInLeftAnimation;
import com.yzk.xtools.base.adapter.animation.SlideInRightAnimation;
import com.yzk.xtools.base.adapter.status.AnimationType;


/**
 * Created by BlingBling on 2016/12/15.
 */

public class AnimationManager {

    private BaseAdapter mQuickAdapter;

    private BaseAnimation mAnimation;
    private int mLastPosition = -1;
    private boolean mFirstOnly = true;

    public AnimationManager(BaseAdapter quickAdapter) {
        mQuickAdapter = quickAdapter;
    }

    public void resetAnimation() {
        mLastPosition = -1;
    }

    public void addAnimation(RecyclerView.ViewHolder holder) {
        if (mAnimation != null) {
            final int position = holder.getLayoutPosition();
            if (!mFirstOnly || position > mLastPosition) {
                mAnimation.getAnimators(holder.itemView).start();
                mLastPosition = position;
            }
        }
    }

    public AnimationManager setFirstOnly(boolean firstOnly) {
        mFirstOnly = firstOnly;
        return this;
    }

    public void openAnimation() {
        openAnimation(AnimationType.ANIMATION_ALPHA_IN);
    }

    public void openAnimation(@AnimationType.Type int animationType) {
        switch (animationType) {
            case AnimationType.ANIMATION_ALPHA_IN:
                openAnimation(new AlphaInAnimation());
                break;
            case AnimationType.ANIMATION_SCALE_IN:
                openAnimation(new ScaleInAnimation());
                break;
            case AnimationType.ANIMATION_SLIDE_IN_BOTTOM:
                openAnimation(new SlideInBottomAnimation());
                break;
            case AnimationType.ANIMATION_SLIDE_IN_LEFT:
                openAnimation(new SlideInLeftAnimation());
                break;
            case AnimationType.ANIMATION_SLIDE_IN_RIGHT:
                openAnimation(new SlideInRightAnimation());
                break;
        }
    }

    public void openAnimation(BaseAnimation animation) {
        mAnimation = animation;
    }
}
