package com.stardust.express.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stardust.express.app.BaseAbstractAdapter;
import com.stardust.express.app.R;
import com.stardust.express.app.entity.GoodsNameEntity;

/**
 * Created by Sylar on 2016/11/21.
 */
public class ProductListAdapter extends BaseAbstractAdapter<GoodsNameEntity> {
    private boolean isParent;

    public ProductListAdapter(Context context, boolean isParent) {
        super(context);
        this.isParent = isParent;
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.product_list_item;
    }

    @Override
    protected IViewHolder<GoodsNameEntity> getViewHolder() {

        return new IViewHolder<GoodsNameEntity>() {
            private TextView parentName;
            private TextView childName;
            private LinearLayout container;

            @Override
            public void initViews(View view, int position) {
                container = (LinearLayout) view.findViewById(R.id.container);
                parentName = (TextView) view.findViewById(R.id.parent_name);
                childName = (TextView) view.findViewById(R.id.child_name);
                if (isParent) {
                    parentName.setVisibility(View.VISIBLE);
                    childName.setVisibility(View.GONE);
                } else {
                    parentName.setVisibility(View.GONE);
                    childName.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void fillData(GoodsNameEntity goodsNameEntity, int position) {
                if (goodsNameEntity.isChecked) {
                    container.setBackgroundColor(Color.parseColor("#48D1CC"));
                    parentName.setTextColor(Color.WHITE);
                    childName.setTextColor(Color.WHITE);
                } else {
                    container.setBackgroundColor(Color.WHITE);
                    parentName.setTextColor(Color.BLACK);
                    childName.setTextColor(Color.BLACK);
                }

                if (isParent) {
                    parentName.setText(goodsNameEntity.name);
                } else {
                    childName.setText(goodsNameEntity.name);
                }
            }
        };
    }
}
