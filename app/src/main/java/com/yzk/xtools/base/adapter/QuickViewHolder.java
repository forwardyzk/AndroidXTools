package com.yzk.xtools.base.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by BlingBling on 2016/12/14.
 */

public class QuickViewHolder extends RecyclerView.ViewHolder {

    private Object mObject;

    private final SparseArray<View> mViews = new SparseArray<>();

    public QuickViewHolder(View view) {
        super(view);
    }

    public <V extends View> V getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    /**
     * Will set the text of a TextView.
     *
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     */
    public QuickViewHolder setText(@IdRes int viewId, CharSequence value) {
        final TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public QuickViewHolder setText(@IdRes int viewId, @StringRes int strId) {
        final TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId    The view id.
     * @param textColor The text color (not a resource id).
     */
    public QuickViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        final TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * Will set background color of a view.
     *
     * @param viewId The view id.
     * @param color  A color, not a resource id.
     */
    public QuickViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        final View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Will set background of a view.
     *
     * @param viewId        The view id.
     * @param backgroundRes A resource to use as a background.
     */
    public QuickViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        final View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set the image of an ImageView from a resource id.
     *
     * @param viewId     The view id.
     * @param imageResId The image resource id.
     */
    public QuickViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        final ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId   The view id.
     * @param drawable The image drawable.
     */
    public QuickViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        final ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Add an action to set the image of an image view. Can be called multiple times.
     */
    public QuickViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        final ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    public QuickViewHolder setAlpha(@IdRes int viewId, float value) {
        final View view = getView(viewId);
        view.setAlpha(value);
        return this;
    }

    /**
     * Set a view visibility to VISIBLE (true) or GONE (false).
     *
     * @param viewId  The view id.
     * @param visible True for VISIBLE, false for GONE.
     */
    public QuickViewHolder setVisible(@IdRes int viewId, boolean visible) {
        return setVisibility(viewId, visible ? View.VISIBLE : View.GONE);
    }

    public QuickViewHolder setVisibility(@IdRes int viewId, int visibility) {
        final View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     */
    public QuickViewHolder setProgress(@IdRes int viewId, int progress) {
        final ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     */
    public QuickViewHolder setProgress(@IdRes int viewId, int progress, int max) {
        final ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the range of a ProgressBar to 0...max.
     *
     * @param viewId The view id.
     * @param max    The max value of a ProgressBar.
     */
    public QuickViewHolder setMax(@IdRes int viewId, int max) {
        final ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     */
    public QuickViewHolder setRating(@IdRes int viewId, float rating) {
        final RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     */
    public QuickViewHolder setRating(@IdRes int viewId, float rating, int max) {
        final RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the checked status of a checkable.
     *
     * @param viewId  The view id.
     * @param checked The checked status;
     */
    public QuickViewHolder setChecked(@IdRes int viewId, boolean checked) {
        final View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param tag    The tag;
     */
    public QuickViewHolder setTag(@IdRes int viewId, Object tag) {
        final View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param key    The key of tag;
     * @param tag    The tag;
     */
    public QuickViewHolder setTag(@IdRes int viewId, int key, Object tag) {
        final View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public void setObject(Object mObj) {
        this.mObject = mObj;
    }

    public <O> O getObject() {
        return (O) mObject;
    }
}