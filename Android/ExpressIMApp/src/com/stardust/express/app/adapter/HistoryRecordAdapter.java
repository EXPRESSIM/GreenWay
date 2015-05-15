package com.stardust.express.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.stardust.express.app.BaseAbstractAdapter;
import com.stardust.express.app.R;
import com.stardust.express.app.entity.HistoryRecordEntity;

/**
 * Created by Sylar on 15/5/14.
 */
public class HistoryRecordAdapter extends BaseAbstractAdapter<HistoryRecordEntity> {
    public HistoryRecordAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.history_record_list_item;
    }

    @Override
    protected IViewHolder<HistoryRecordEntity> getViewHolder() {
        return new ViewHolder();
    }

    private class ViewHolder implements IViewHolder<HistoryRecordEntity> {
        private TextView recordDate;
        private TextView isCommit;

        @Override
        public void initViews(View view, int position) {
            recordDate = (TextView) view.findViewById(R.id.record_date);
            isCommit = (TextView) view.findViewById(R.id.is_commit);
        }

        @Override
        public void fillData(HistoryRecordEntity entity, int position) {
            recordDate.setText(entity.recordDate);
            isCommit.setText(entity.isCommit ? "已提交" : "未提交");
        }
    }
}
