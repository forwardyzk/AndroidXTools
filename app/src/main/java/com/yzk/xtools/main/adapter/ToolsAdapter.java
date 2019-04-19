package com.yzk.xtools.main.adapter;

import com.yzk.xtools.R;
import com.yzk.xtools.base.adapter.BaseQuickAdapter;
import com.yzk.xtools.base.adapter.QuickViewHolder;
import com.yzk.xtools.base.adapter.view.ItemView;
import com.yzk.xtools.page.data.UtilBean;

/**
 * Author:yang
 * Time:2019/4/16 14:31
 * Description:主页工具
 */
public class ToolsAdapter extends BaseQuickAdapter<UtilBean> {

    public ToolsAdapter() {
        addItemView(new ItemView(R.layout.item_main_recycler_list).listenerOnItemClick());
    }

    @Override
    protected void onBind(QuickViewHolder holder, UtilBean item, int position) {
        if (item == null) {
            return;
        }
        holder.setText(R.id.item_main_tv, item.desc);
    }


}
