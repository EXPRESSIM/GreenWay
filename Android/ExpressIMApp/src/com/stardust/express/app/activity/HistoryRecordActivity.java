package com.stardust.express.app.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.R;
import com.stardust.express.app.adapter.HistoryRecordAdapter;
import com.stardust.express.app.db.dao.HistoryRecordDao;
import com.stardust.express.app.entity.HistoryRecordEntity;
import com.stardust.express.app.utils.CacheFileUtils;

import java.util.List;

/**
 * Created by Sylar on 15/5/14.
 */
public class HistoryRecordActivity extends BaseActivity {

    private ListView listview;
    private HistoryRecordAdapter adapter;
    private HistoryRecordDao historyRecordDao;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_history_record;
    }

    @Override
    protected void initViews() {
        historyRecordDao = new HistoryRecordDao(this);
        listview = (ListView) findViewById(R.id.history_record_list);
        adapter = new HistoryRecordAdapter(this);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HistoryRecordEntity historyRecordEntity = (HistoryRecordEntity) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(HistoryRecordActivity.this, ViewInformationActivity.class);
                intent.putExtra("HistoryRecord", historyRecordEntity);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void fillData() {

    }


    private void reloadData() {
        List<HistoryRecordEntity> list = historyRecordDao.getRecordList();
        if (list != null) {
            adapter.getData().clear();
            adapter.getData().addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "清空所有数据");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                historyRecordDao.clear();
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
                CacheFileUtils.cleanExternalFiles(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
