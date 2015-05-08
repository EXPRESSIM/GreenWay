package com.stardust.express.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.stardust.express.app.BaseAbstractAdapter;
import com.stardust.express.app.R;
import com.stardust.express.app.entity.StationEntity;

import java.util.AbstractCollection;

/**
 * Created by Gyb on 2015/5/8.
 */
public class StationListAdapter extends BaseAbstractAdapter<StationEntity> {
    public StationListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.station_list_item;
    }

    @Override
    protected IViewHolder<StationEntity> getViewHolder() {
        return new ViewHolder();
    }

    private class ViewHolder implements IViewHolder<StationEntity> {
        private TextView name;
        private ImageView imageView;

        @Override
        public void initViews(View view, int position) {
            name = (TextView) view.findViewById(R.id.station_name);
            imageView = (ImageView) view.findViewById(R.id.image);
        }

        @Override
        public void fillData(StationEntity stationEntity, int position) {
            name.setText(stationEntity.name);
            if (stationEntity.checked) {
                imageView.setBackgroundResource(R.drawable.checked);
            } else {
                imageView.setBackground(null);
            }
        }
    }
}
