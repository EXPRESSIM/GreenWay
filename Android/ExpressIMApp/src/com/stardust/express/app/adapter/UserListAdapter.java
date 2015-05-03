package com.stardust.express.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.stardust.express.app.BaseAbstractAdapter;
import com.stardust.express.app.R;
import com.stardust.express.app.entity.UserEntity;

/**
 * Created by Gyb on 2015/4/28.
 */
public class UserListAdapter extends BaseAbstractAdapter<UserEntity> {

    public UserListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.user_list_item;
    }

    @Override
    protected IViewHolder<UserEntity> getViewHolder() {
        return new ViewHolder();
    }

    private class ViewHolder implements IViewHolder<UserEntity> {

        private TextView userNameTextView;
        private TextView stationNameTextView;

        @Override
        public void initViews(View view, int position) {
            userNameTextView = (TextView) view.findViewById(R.id.user_name);
            stationNameTextView = (TextView) view.findViewById(R.id.station_name);
        }

        @Override
        public void fillData(UserEntity userEntity, int position) {
            userNameTextView.setText(userEntity.userName);
            stationNameTextView.setText(userEntity.stationName);
        }
    }
}
