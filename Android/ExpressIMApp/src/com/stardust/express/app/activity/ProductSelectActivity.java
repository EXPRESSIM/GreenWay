package com.stardust.express.app.activity;

import android.content.Intent;
import android.view.*;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.R;
import com.stardust.express.app.adapter.ProductListAdapter;
import com.stardust.express.app.entity.GoodsNameEntity;
import com.stardust.express.app.utils.ScreenUtils;
import com.stardust.express.app.utils.SharedUtil;
import com.stardust.express.app.utils.ToastUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.stardust.express.app.utils.StringUtils.getPinYin;

/**
 * Created by Sylar on 2016/11/21.
 */
public class ProductSelectActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private ListView parentListView;
    private ListView childListView;
    private ProductListAdapter parentListAdapter;
    private ProductListAdapter childListAdapter;
    private GoodsNameEntity selectedProduct;
    private List<GoodsNameEntity> childrenDataList;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_product_select;
    }

    @Override
    protected void initViews() {
        childrenDataList = new ArrayList<GoodsNameEntity>();

        parentListView = (ListView) findViewById(R.id.product_parent_list);
        childListView = (ListView) findViewById(R.id.product_child_list);
        parentListAdapter = new ProductListAdapter(this, true);
        childListAdapter = new ProductListAdapter(this, false);
        parentListView.setAdapter(parentListAdapter);
        childListView.setAdapter(childListAdapter);

        parentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GoodsNameEntity item = (GoodsNameEntity) adapterView.getItemAtPosition(i);
                childListAdapter.getData().clear();
                childListAdapter.getData().addAll(item.children);
                childListAdapter.notifyDataSetChanged();
                for (GoodsNameEntity parent : parentListAdapter.getData()) {
                    parent.isChecked = parent.name.equals(item.name);
                }
                parentListAdapter.notifyDataSetChanged();
            }
        });
        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct = (GoodsNameEntity) adapterView.getItemAtPosition(i);
                for (GoodsNameEntity child : childListAdapter.getData()) {
                    child.isChecked = child.name.equals(selectedProduct.name);
                }
                childListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void fillData() {
        try {
            String json = SharedUtil.getString(this, "Cargo");
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);
                String name = object.optString("parent");
                String pinyin = getPinYin(name);
                GoodsNameEntity entity = new GoodsNameEntity(name, pinyin);
                JSONArray children = object.optJSONArray("children");
                for (int j = 0; j < children.length(); j++) {
                    entity.children.add(new GoodsNameEntity(children.optString(j), getPinYin(children.optString(j))));
                }
                parentListAdapter.getData().add(entity);
                childrenDataList.addAll(entity.children);
            }
            parentListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_select_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.onActionViewExpanded();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) searchView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        layoutParams.width = ScreenUtils.getScreenResolution(this).getWidth() - ScreenUtils.dp2px(this, 60);
        searchView.setLayoutParams(layoutParams);
        searchView.setQueryHint("输入汉字或拼音搜索");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                if (selectedProduct != null) {
                    Intent intent = new Intent();
                    intent.putExtra("data", selectedProduct);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtils.showToast(ProductSelectActivity.this, "请选择分类");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<GoodsNameEntity> result = new ArrayList<GoodsNameEntity>();
        for (GoodsNameEntity item : childrenDataList) {
            if (item.pinyin.contains(s) || item.name.contains(s)) {
                result.add(item);
            }
        }
        childListAdapter.getData().clear();
        childListAdapter.getData().addAll(result);
        childListAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
