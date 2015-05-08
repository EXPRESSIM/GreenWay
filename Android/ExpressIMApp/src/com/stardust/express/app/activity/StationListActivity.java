package com.stardust.express.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.R;
import com.stardust.express.app.adapter.StationListAdapter;
import com.stardust.express.app.entity.StationEntity;
import com.stardust.express.app.utils.StringUtils;
import com.stardust.express.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gyb on 2015/5/8.
 */
public class StationListActivity extends BaseActivity {

    private ListView stationListView;
    private StationListAdapter adapter;
    private EditText keywordInput;
    private List<StationEntity> stationList;
    private StationEntity selectedStation;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_station_list;
    }

    @Override
    protected void initViews() {
        stationListView = (ListView) findViewById(R.id.station_list);
        adapter = new StationListAdapter(this);
        stationListView.setAdapter(adapter);
        keywordInput = (EditText) findViewById(R.id.keyword);
        keywordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!StringUtils.isNotNull(editable.toString())) {
                    adapter.getData().addAll(stationList);
                    adapter.notifyDataSetChanged();
                    return;
                }

                List<StationEntity> result = new ArrayList<StationEntity>();
                for (StationEntity stationEntity : stationList) {
                    if (stationEntity.name.contains(editable.toString())) {
                        result.add(stationEntity);
                    }
                }
                adapter.getData().clear();
                adapter.getData().addAll(result);
                adapter.notifyDataSetChanged();
            }
        });

        stationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedStation = (StationEntity) adapterView.getItemAtPosition(i);
                for (StationEntity entity : stationList) {
                    entity.checked = selectedStation.name.equals(entity.name);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void fillData() {
        initDataSource();
        adapter.getData().addAll(stationList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "完成");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                if (selectedStation == null) {
                    ToastUtils.showToastAtCenter(this, "请选择收费站");
                    return true;
                }
                Intent intent = new Intent();
                intent.putExtra("Station", selectedStation);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_CANCELED);
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initDataSource() {
        stationList = new ArrayList<StationEntity>();
        stationList.add(new StationEntity("灞桥"));
        stationList.add(new StationEntity("豁口"));
        stationList.add(new StationEntity("临潼西"));
        stationList.add(new StationEntity("兵马俑"));
        stationList.add(new StationEntity("新丰"));
        stationList.add(new StationEntity("渭南西"));
        stationList.add(new StationEntity("渭南东"));
        stationList.add(new StationEntity("华县西"));
        stationList.add(new StationEntity("华县"));
        stationList.add(new StationEntity("夫水"));
        stationList.add(new StationEntity("华阴"));
        stationList.add(new StationEntity("港口"));
        stationList.add(new StationEntity("秦东"));
        stationList.add(new StationEntity("潼关"));
        stationList.add(new StationEntity("三桥"));
        stationList.add(new StationEntity("咸阳"));
        stationList.add(new StationEntity("咸阳西"));
        stationList.add(new StationEntity("兴平"));
        stationList.add(new StationEntity("武功"));
        stationList.add(new StationEntity("杨凌"));
        stationList.add(new StationEntity("绛帐"));
        stationList.add(new StationEntity("法门寺"));
        stationList.add(new StationEntity("太白山"));
        stationList.add(new StationEntity("常兴"));
        stationList.add(new StationEntity("杨凌西"));
        stationList.add(new StationEntity("眉县"));
        stationList.add(new StationEntity("蔡家坡"));
        stationList.add(new StationEntity("虢镇"));
        stationList.add(new StationEntity("宝鸡"));
        stationList.add(new StationEntity("阿房宫"));
        stationList.add(new StationEntity("咸阳南"));
        stationList.add(new StationEntity("宝鸡西"));
        stationList.add(new StationEntity("坪头"));
        stationList.add(new StationEntity("陈仓"));
        stationList.add(new StationEntity("陈仓匝道"));
        stationList.add(new StationEntity("姜城"));
        stationList.add(new StationEntity("陇关"));
        stationList.add(new StationEntity("火烧寨"));
        stationList.add(new StationEntity("陇县"));
        stationList.add(new StationEntity("水沟"));
        stationList.add(new StationEntity("千阳"));
        stationList.add(new StationEntity("南寨"));
        stationList.add(new StationEntity("凤翔"));
        stationList.add(new StationEntity("渭南北"));
        stationList.add(new StationEntity("吝店"));
        stationList.add(new StationEntity("卤阳湖"));
        stationList.add(new StationEntity("蒲城东"));
        stationList.add(new StationEntity("茶镇"));
        stationList.add(new StationEntity("午子山"));
        stationList.add(new StationEntity("西乡"));
        stationList.add(new StationEntity("沙河"));
        stationList.add(new StationEntity("盐井"));
        stationList.add(new StationEntity("铺镇"));
        stationList.add(new StationEntity("汉中北"));
        stationList.add(new StationEntity("褒城"));
        stationList.add(new StationEntity("新街子"));
        stationList.add(new StationEntity("勉县北"));
        stationList.add(new StationEntity("茶店"));
        stationList.add(new StationEntity("五郎坪"));
        stationList.add(new StationEntity("略阳"));
        stationList.add(new StationEntity("陕西略阳"));
        stationList.add(new StationEntity("流水"));
        stationList.add(new StationEntity("嵩坪"));
        stationList.add(new StationEntity("紫阳"));
        stationList.add(new StationEntity("权河"));
        stationList.add(new StationEntity("毛坝"));
        stationList.add(new StationEntity("巴山"));
        stationList.add(new StationEntity("陕川界"));
        stationList.add(new StationEntity("华胥"));
        stationList.add(new StationEntity("蓝田"));
        stationList.add(new StationEntity("玉山"));
        stationList.add(new StationEntity("灞源"));
        stationList.add(new StationEntity("腰市"));
        stationList.add(new StationEntity("洛南"));
        stationList.add(new StationEntity("板桥"));
        stationList.add(new StationEntity("商洛"));
        stationList.add(new StationEntity("铜川新区"));
        stationList.add(new StationEntity("马额"));
        stationList.add(new StationEntity("新兴"));
        stationList.add(new StationEntity("三原"));
        stationList.add(new StationEntity("泾阳"));
        stationList.add(new StationEntity("聂冯"));
        stationList.add(new StationEntity("神木北"));
        stationList.add(new StationEntity("永兴"));
        stationList.add(new StationEntity("石马"));
        stationList.add(new StationEntity("府谷南"));
        stationList.add(new StationEntity("府谷"));
        stationList.add(new StationEntity("牛家梁"));
        stationList.add(new StationEntity("古城滩"));
        stationList.add(new StationEntity("榆林南"));
        stationList.add(new StationEntity("鱼河"));
        stationList.add(new StationEntity("镇川"));
        stationList.add(new StationEntity("米脂"));
        stationList.add(new StationEntity("四十里铺"));
        stationList.add(new StationEntity("史家湾"));
        stationList.add(new StationEntity("青云"));
        stationList.add(new StationEntity("麻黄梁"));
        stationList.add(new StationEntity("王家砭"));
        stationList.add(new StationEntity("通镇"));
        stationList.add(new StationEntity("佳县"));
        stationList.add(new StationEntity("陕西佳县"));
        stationList.add(new StationEntity("锦阳湖"));
        stationList.add(new StationEntity("演池"));
        stationList.add(new StationEntity("招安"));
        stationList.add(new StationEntity("候市"));
        stationList.add(new StationEntity("志丹"));
        stationList.add(new StationEntity("志丹南"));
        stationList.add(new StationEntity("吴起"));
        stationList.add(new StationEntity("阿房宫"));
        stationList.add(new StationEntity("河池寨"));
        stationList.add(new StationEntity("西高新"));
        stationList.add(new StationEntity("长安路"));
        stationList.add(new StationEntity("曲江"));
        stationList.add(new StationEntity("纺织城"));
        stationList.add(new StationEntity("香王"));
        stationList.add(new StationEntity("新筑"));
        stationList.add(new StationEntity("杏园"));
        stationList.add(new StationEntity("未央"));
        stationList.add(new StationEntity("未央(北)"));
        stationList.add(new StationEntity("富平"));
        stationList.add(new StationEntity("荆姚"));
        stationList.add(new StationEntity("蒲城"));
        stationList.add(new StationEntity("孙镇"));
        stationList.add(new StationEntity("澄城"));
        stationList.add(new StationEntity("合阳"));
        stationList.add(new StationEntity("芝川"));
        stationList.add(new StationEntity("韩城"));
        stationList.add(new StationEntity("龙门"));
        stationList.add(new StationEntity("禹门口"));
        stationList.add(new StationEntity("西禹小计"));
        stationList.add(new StationEntity("高陵"));
        stationList.add(new StationEntity("阎良"));
        stationList.add(new StationEntity("户县"));
        stationList.add(new StationEntity("涝峪"));
        stationList.add(new StationEntity("三星"));
        stationList.add(new StationEntity("金水"));
        stationList.add(new StationEntity("大河坝"));
        stationList.add(new StationEntity("宁陕"));
        stationList.add(new StationEntity("皇冠"));
        stationList.add(new StationEntity("朱雀"));
        stationList.add(new StationEntity("纸坊"));
        stationList.add(new StationEntity("龙亭"));
        stationList.add(new StationEntity("洋县"));
        stationList.add(new StationEntity("城固"));
        stationList.add(new StationEntity("上元观"));
        stationList.add(new StationEntity("汉中东"));
        stationList.add(new StationEntity("汉中"));
        stationList.add(new StationEntity("勉县"));
        stationList.add(new StationEntity("胡家坝"));
        stationList.add(new StationEntity("韩家坝"));
        stationList.add(new StationEntity("宁强立交"));
        stationList.add(new StationEntity("宁强"));
        stationList.add(new StationEntity("铜川"));
        stationList.add(new StationEntity("黄堡匝道"));
        stationList.add(new StationEntity("西安北"));
        stationList.add(new StationEntity("铜川开放"));
        stationList.add(new StationEntity("黄堡开放"));
        stationList.add(new StationEntity("耀州北开放"));
        stationList.add(new StationEntity("铜川新区"));
        stationList.add(new StationEntity("川口"));
        stationList.add(new StationEntity("金锁关"));
        stationList.add(new StationEntity("宜君"));
        stationList.add(new StationEntity("黄陵"));
        stationList.add(new StationEntity("彭镇 "));
        stationList.add(new StationEntity("铜延小计"));
        stationList.add(new StationEntity("延安南"));
        stationList.add(new StationEntity("甘泉"));
        stationList.add(new StationEntity("南泥湾"));
        stationList.add(new StationEntity("富县"));
        stationList.add(new StationEntity("洛川"));
        stationList.add(new StationEntity("阿党"));
        stationList.add(new StationEntity("延安北"));
        stationList.add(new StationEntity("沿河湾"));
        stationList.add(new StationEntity("安塞南"));
        stationList.add(new StationEntity("安塞北"));
        stationList.add(new StationEntity("建华寺"));
        stationList.add(new StationEntity("化子坪"));
        stationList.add(new StationEntity("镰刀湾"));
        stationList.add(new StationEntity("天赐湾"));
        stationList.add(new StationEntity("李家湾"));
        stationList.add(new StationEntity("乔沟湾"));
        stationList.add(new StationEntity("靖边南"));
        stationList.add(new StationEntity("靖边东"));
        stationList.add(new StationEntity("榆林"));
        stationList.add(new StationEntity("西左界"));
        stationList.add(new StationEntity("横山"));
        stationList.add(new StationEntity("黄蒿界"));
        stationList.add(new StationEntity("王圈梁"));
        stationList.add(new StationEntity("定边"));
        stationList.add(new StationEntity("砖井"));
        stationList.add(new StationEntity("安边"));
        stationList.add(new StationEntity("良镇"));
        stationList.add(new StationEntity("东坑"));
        stationList.add(new StationEntity("靖边西"));
        stationList.add(new StationEntity("王则湾"));
        stationList.add(new StationEntity("孟家湾"));
        stationList.add(new StationEntity("小壕兔"));
        stationList.add(new StationEntity("红碱淖"));
        stationList.add(new StationEntity("陕蒙界"));
        stationList.add(new StationEntity("金鸡滩"));
        stationList.add(new StationEntity("大保当"));
        stationList.add(new StationEntity("锦界"));
        stationList.add(new StationEntity("西沟"));
        stationList.add(new StationEntity("神木"));
        stationList.add(new StationEntity("店塔"));
        stationList.add(new StationEntity("韦曲"));
        stationList.add(new StationEntity("太乙宫"));
        stationList.add(new StationEntity("营盘"));
        stationList.add(new StationEntity("柞水"));
        stationList.add(new StationEntity("镇安"));
        stationList.add(new StationEntity("东坪"));
        stationList.add(new StationEntity("小河"));
        stationList.add(new StationEntity("茨沟"));
        stationList.add(new StationEntity("谭坝"));
        stationList.add(new StationEntity("五里"));
        stationList.add(new StationEntity("安康"));
        stationList.add(new StationEntity("恒口"));
        stationList.add(new StationEntity("蒲溪"));
        stationList.add(new StationEntity("安康东"));
        stationList.add(new StationEntity("汉阴"));
        stationList.add(new StationEntity("池河"));
        stationList.add(new StationEntity("石泉"));
        stationList.add(new StationEntity("旬阳"));
        stationList.add(new StationEntity("神河"));
        stationList.add(new StationEntity("张河"));
        stationList.add(new StationEntity("双河"));
        stationList.add(new StationEntity("茅坪"));
        stationList.add(new StationEntity("白河"));
        stationList.add(new StationEntity("陕西白河"));
        stationList.add(new StationEntity("阎村"));
        stationList.add(new StationEntity("山阳 "));
        stationList.add(new StationEntity("高坝"));
        stationList.add(new StationEntity("天竺山"));
        stationList.add(new StationEntity("漫川关匝道 "));
        stationList.add(new StationEntity("漫川关主线"));
        stationList.add(new StationEntity("空工"));
        stationList.add(new StationEntity("白鹿塬"));
        stationList.add(new StationEntity("蓝田东"));
        stationList.add(new StationEntity("辋川"));
        stationList.add(new StationEntity("葛牌"));
        stationList.add(new StationEntity("杨斜"));
        stationList.add(new StationEntity("南城子"));
        stationList.add(new StationEntity("商洛西"));
        stationList.add(new StationEntity("商洛东"));
        stationList.add(new StationEntity("棣花"));
        stationList.add(new StationEntity("丹凤"));
        stationList.add(new StationEntity("竹林关"));
        stationList.add(new StationEntity("金丝峡"));
        stationList.add(new StationEntity("过凤楼"));
        stationList.add(new StationEntity("商南西"));
        stationList.add(new StationEntity("界牌"));
        stationList.add(new StationEntity("汉城"));
        stationList.add(new StationEntity("北环路"));
        stationList.add(new StationEntity("马家堡"));
        stationList.add(new StationEntity("机场"));
        stationList.add(new StationEntity("六村堡"));
        stationList.add(new StationEntity("咸阳东"));
        stationList.add(new StationEntity("机场西"));
        stationList.add(new StationEntity("马庄"));
        stationList.add(new StationEntity("西张堡"));
        stationList.add(new StationEntity("礼泉"));
        stationList.add(new StationEntity("乾县"));
        stationList.add(new StationEntity("乾陵"));
        stationList.add(new StationEntity("永寿南"));
        stationList.add(new StationEntity("永寿南"));
        stationList.add(new StationEntity("渡马"));
        stationList.add(new StationEntity("太峪"));
        stationList.add(new StationEntity("彬县"));
        stationList.add(new StationEntity("亭口"));
        stationList.add(new StationEntity("长武"));
        stationList.add(new StationEntity("陕甘界"));
        stationList.add(new StationEntity("杨桥畔"));
        stationList.add(new StationEntity("双城"));
        stationList.add(new StationEntity("魏家楼"));
        stationList.add(new StationEntity("巡检司"));
        stationList.add(new StationEntity("子洲"));
        stationList.add(new StationEntity("绥德"));
        stationList.add(new StationEntity("中角"));
        stationList.add(new StationEntity("辛家沟"));
        stationList.add(new StationEntity("吴堡"));
        stationList.add(new StationEntity("陕晋界"));
        stationList.add(new StationEntity("陕西壶口"));
        stationList.add(new StationEntity("秋林"));
        stationList.add(new StationEntity("宜川"));
        stationList.add(new StationEntity("瓦子街"));
        stationList.add(new StationEntity("厢寺川"));
        stationList.add(new StationEntity("富县南"));
        stationList.add(new StationEntity("张村驿"));
        stationList.add(new StationEntity("直罗"));
        stationList.add(new StationEntity("张家湾"));
        stationList.add(new StationEntity("陕西富县"));
    }
}