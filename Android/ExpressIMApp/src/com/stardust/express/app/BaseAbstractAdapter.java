package com.stardust.express.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gyb on 2015/4/28.
 */
public abstract class BaseAbstractAdapter<T> extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<T> data;
    protected Context context;

    public BaseAbstractAdapter(Context context) {
        this.context = context;
        data = new ArrayList<T>();
        layoutInflater = LayoutInflater.from(context);
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        IViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(getItemLayoutResource(), null);
            viewHolder = getViewHolder();
            viewHolder.initViews(view, i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (IViewHolder) view.getTag();
        }
        T t = getItem(i);
        viewHolder.fillData(t, i);
        return view;
    }

    protected interface IViewHolder<T> {
        void initViews(View view, int position);

        void fillData(T t, int position);
    }

    protected abstract int getItemLayoutResource();

    protected abstract IViewHolder<T> getViewHolder();
}
