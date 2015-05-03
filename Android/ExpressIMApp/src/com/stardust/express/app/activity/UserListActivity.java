package com.stardust.express.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.R;
import com.stardust.express.app.adapter.UserListAdapter;
import com.stardust.express.app.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gyb on 2015/4/27.
 */
public class UserListActivity extends BaseActivity {

    private ListView userListView;
    private UserListAdapter userListAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void initViews() {
        userListView = (ListView) findViewById(R.id.user_list_view);
        userListAdapter = new UserListAdapter(this);
        userListView.setAdapter(userListAdapter);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserEntity userEntity = (UserEntity) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(UserListActivity.this, SendInformationActivity.class);
                intent.putExtra("Operator", userEntity);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void fillData() {
        List<UserEntity> userEntityList = new ArrayList<UserEntity>();
        for (int i = 1; i < 11; i++) {
            UserEntity entity = new UserEntity();
            entity.userId = i;
            entity.userName = "张三" + i;
            entity.stationName = "高速公路收费站" + i;
            userEntityList.add(entity);
        }
        userListAdapter.getData().addAll(userEntityList);
        userListAdapter.notifyDataSetChanged();
    }
}
