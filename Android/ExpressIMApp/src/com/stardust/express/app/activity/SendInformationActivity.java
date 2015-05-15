package com.stardust.express.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.Constants;
import com.stardust.express.app.R;
import com.stardust.express.app.activity.widget.DateTimePickerDialog;
import com.stardust.express.app.db.SQLiteManager;
import com.stardust.express.app.db.dao.HistoryRecordDao;
import com.stardust.express.app.entity.GoodsNameEntity;
import com.stardust.express.app.entity.HistoryRecordEntity;
import com.stardust.express.app.entity.StationEntity;
import com.stardust.express.app.entity.UserEntity;
import com.stardust.express.app.http.StringResponseListener;
import com.stardust.express.app.request.ArchiveRequest;
import com.stardust.express.app.request.LeaderLogonRequest;
import com.stardust.express.app.response.ArchiveResponse;
import com.stardust.express.app.response.LeaderLogonResponse;
import com.stardust.express.app.utils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Gyb on 2015/4/28.
 */
public class SendInformationActivity extends BaseActivity implements View.OnClickListener {

    private ImageView carFrontImage;
    private ImageView carBodyImage;
    private ImageView carBackImage;
    private ImageView goodsImage;

    private EditText stationName;
    private EditText dateTime;
    private Spinner provinceSpinner;
    private Spinner letterSpinner;
    private EditText carNumber;
    private Spinner carType;
    private EditText totalAmount;
    private Spinner stationChannel;
    private Spinner goodsCategory;
    private Spinner goodsName;
    private EditText comment;
    private TextView operator;
    private TextView videoPath;
    private Spinner isGreenSpinner;
    private Spinner tollCollectorSpinner;
    private EditText adjustAmount;
    private Spinner channelTypeSpinner;
    private LinearLayout adjustAmountLayout;
    private LinearLayout goodsNameLayout;

    private static final int TAKE_PICTURE = 400;
    private static final int RECORD_VIDEO = 500;
    private static final int SELECT_STATION = 600;
    private String storeFileDirPath;
    private String captureFileName;
    private String videoFileName;
    private int takePictureIdx;
    private static List<GoodsNameEntity> goodsNameList;

    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> letterAdapter;
    private ArrayAdapter<String> carTypeAdapter;
    private ArrayAdapter<GoodsNameEntity> goodsCategoryAdapter;
    private ArrayAdapter<GoodsNameEntity> goodsNameAdapter;
    private ArrayAdapter<String> channelAdapter;
    private ArrayAdapter<String> isGreenAdapter;
    private ArrayAdapter<String> channelTypeAdapter;
    private ArrayAdapter<String> tollCollectorAdapter;
    private UserEntity logonOperatorEntity;

    private HistoryRecordDao historyRecordDao;

    private static interface ImageIndex {
        int car_front = 1;
        int car_back = 2;
        int car_body = 3;
        int goods = 4;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_send_information;
    }

    @Override
    protected void initViews() {
        historyRecordDao = new HistoryRecordDao(this);

        carFrontImage = (ImageView) findViewById(R.id.car_front_image);
        carBodyImage = (ImageView) findViewById(R.id.car_body_image);
        carBackImage = (ImageView) findViewById(R.id.car_back_image);
        goodsImage = (ImageView) findViewById(R.id.goods_image);
        stationName = (EditText) findViewById(R.id.station_name);
        dateTime = (EditText) findViewById(R.id.date_time);
        carNumber = (EditText) findViewById(R.id.car_number);
        carType = (Spinner) findViewById(R.id.car_type);
        totalAmount = (EditText) findViewById(R.id.total_amount);
        stationChannel = (Spinner) findViewById(R.id.station_channel);
        goodsCategory = (Spinner) findViewById(R.id.goods_category);
        goodsName = (Spinner) findViewById(R.id.goods_name);
        comment = (EditText) findViewById(R.id.comment);
        operator = (TextView) findViewById(R.id.operator);
        provinceSpinner = (Spinner) findViewById(R.id.province_spinner);
        letterSpinner = (Spinner) findViewById(R.id.letter_spinner);
        videoPath = (TextView) findViewById(R.id.video_path);
        isGreenSpinner = (Spinner) findViewById(R.id.is_green);
        tollCollectorSpinner = (Spinner) findViewById(R.id.toll_collector);
        adjustAmount = (EditText) findViewById(R.id.adjust_amount);
        adjustAmountLayout = (LinearLayout) findViewById(R.id.adjust_amount_layout);
        goodsNameLayout = (LinearLayout) findViewById(R.id.goods_name_layout);
        channelTypeSpinner = (Spinner) findViewById(R.id.channel_type);

        findViewById(R.id.submit_button).setOnClickListener(this);
        findViewById(R.id.reset_button).setOnClickListener(this);
        carFrontImage.setOnClickListener(this);
        carBodyImage.setOnClickListener(this);
        carBackImage.setOnClickListener(this);
        goodsImage.setOnClickListener(this);
        dateTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Calendar calendar = Calendar.getInstance();
                    final int year = calendar.get(Calendar.YEAR);
                    final int monthOfYear = calendar.get(Calendar.MONTH);
                    final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                    final int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                    final int minute = calendar.get(Calendar.MINUTE);
                    String startDateTime = year + "年" + monthOfYear + "月" + dayOfMonth + "日 " + hourOfDay + ":" + minute;
                    DateTimePickerDialog dateTimePicKDialog = new DateTimePickerDialog(
                            SendInformationActivity.this, startDateTime);
                    dateTimePicKDialog.dateTimePicKDialog(dateTime);
                }
                return true;
            }
        });

        stationName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SendInformationActivity.this, StationListActivity.class), SELECT_STATION);
            }
        });

        findViewById(R.id.video).setOnClickListener(this);

        isGreenSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                if (item.equals("是")) {
                    adjustAmountLayout.setVisibility(View.GONE);
                    goodsNameLayout.setVisibility(View.VISIBLE);
                } else {
                    adjustAmountLayout.setVisibility(View.VISIBLE);
                    goodsNameLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void fillData() {
        logonOperatorEntity = (UserEntity) getIntent().getSerializableExtra("Operator");
        operator.setText(logonOperatorEntity.name);
        setTitle(SharedUtil.getString(this, "StationName") + "－" + SharedUtil.getString(this, "DeviceNO"));

        File dir = getExternalFilesDir("/");
        if (dir != null) {
            if (!dir.exists())
                dir.mkdirs();
            storeFileDirPath = dir.getAbsolutePath();
        }

        initGoodsNames();
        initProvinceSpinner();
        initLetterSpinner();
        initCarTypeSpinner();
        initStationChannelSpinner();
        initIsGreenSpinner();
        initChannelTypeSpinner();
        initTollCollectorSpinner();
    }

    private void initTollCollectorSpinner() {
        tollCollectorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.toll_collector));
        tollCollectorSpinner.setAdapter(tollCollectorAdapter);
    }

    private void initChannelTypeSpinner() {
        channelTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.channel_type));
        channelTypeSpinner.setAdapter(channelTypeAdapter);
    }

    private void initIsGreenSpinner() {
        isGreenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.is_green));
        isGreenSpinner.setAdapter(isGreenAdapter);
    }

    private void initStationChannelSpinner() {
        channelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.station_channel));
        stationChannel.setAdapter(channelAdapter);
    }

    private void initCarTypeSpinner() {
        carTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.car_type));
        carType.setAdapter(carTypeAdapter);
    }

    private void initLetterSpinner() {
        letterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.letter));
        letterSpinner.setAdapter(letterAdapter);
    }

    private void initProvinceSpinner() {
        provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.province));
        provinceSpinner.setAdapter(provinceAdapter);
    }

    private void initGoodsNames() {
        goodsCategoryAdapter = new ArrayAdapter<GoodsNameEntity>(this, android.R.layout.simple_spinner_dropdown_item, goodsNameList);
        goodsCategory.setAdapter(goodsCategoryAdapter);

        goodsNameAdapter = new ArrayAdapter<GoodsNameEntity>(this, android.R.layout.simple_spinner_dropdown_item);
        goodsName.setAdapter(goodsNameAdapter);

        goodsCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                goodsNameAdapter.clear();
                GoodsNameEntity category = (GoodsNameEntity) adapterView.getItemAtPosition(i);
                if (category.children != null && category.children.size() > 0) {
                    goodsNameAdapter.addAll(category.children);
                }
                goodsNameAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_button:
                if (validateInput()) {
                    if (NetworkUtils.isNetworkConnected(SendInformationActivity.this)) {
                        leaderLogon();
                    } else {
                        ToastUtils.showToastAtCenter(SendInformationActivity.this, "网络未链接，提交数据已保存，请在网络恢复后再次提交。");
                        HistoryRecordEntity entity = getHistoryRecordEntity(-1, false);
                        historyRecordDao.save(entity);
                    }
                }
                break;
            case R.id.reset_button:
                new AlertDialog.Builder(this).setTitle("提示").setMessage("是否确认清空数据?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetComponents();
                    }
                }).setNegativeButton("取消", null).create().show();
                break;
            case R.id.car_front_image:
                readyToTakePicture(ImageIndex.car_front);
                break;
            case R.id.car_body_image:
                readyToTakePicture(ImageIndex.car_body);
                break;
            case R.id.car_back_image:
                readyToTakePicture(ImageIndex.car_back);
                break;
            case R.id.goods_image:
                readyToTakePicture(ImageIndex.goods);
                break;
            case R.id.video:
                readyToVideo();
                break;
        }
    }

    private void leaderLogon() {
        final View dialogView = LayoutInflater.from(SendInformationActivity.this).inflate(R.layout.dialog_validate, null);
        final EditText userName = (EditText) dialogView.findViewById(R.id.username);
        final EditText password = (EditText) dialogView.findViewById(R.id.password);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("提交验证")
                .setPositiveButton("验证", null).setNegativeButton("取消", null)
                .create();
        alertDialog.setView(dialogView);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserName = userName.getText().toString();
                String strPassword = password.getText().toString();
                if (!StringUtils.isNotNull(strUserName)) {
                    ToastUtils.showToastAtCenter(SendInformationActivity.this, "用户名不能为空!");
                    return;
                }

                if (!StringUtils.isNotNull(strPassword)) {
                    ToastUtils.showToastAtCenter(SendInformationActivity.this, "密码不能为空!");
                    return;
                }

                showProgressDialog("验证中...");
                new LeaderLogonRequest(SendInformationActivity.this, strUserName, strPassword, new StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        dismissProgressDialog();
                        alertDialog.dismiss();
                        LeaderLogonResponse logonResponse = new LeaderLogonResponse(response);
                        if (logonResponse.success) {
                            HistoryRecordEntity entity = getHistoryRecordEntity(logonResponse.data.id, false);
                            sendInformation(entity);
                        } else {
                            ToastUtils.showToastAtCenter(SendInformationActivity.this, logonResponse.message);
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        dismissProgressDialog();
                        ToastUtils.showToastAtCenter(SendInformationActivity.this, "网络链接失败，请稍后重试");
                    }
                }).execute();
            }
        });
    }

    private void sendInformation(final HistoryRecordEntity entity) {
        if (NetworkUtils.isNetworkConnected(SendInformationActivity.this)) {
            showProgressDialog("提交中...");
            new ArchiveRequest(this, entity, new StringResponseListener() {
                @Override
                public void onResponse(String response) {
                    dismissProgressDialog();
                    final ArchiveResponse archiveResponse = new ArchiveResponse(response);
                    if (archiveResponse.success) {
                        entity.isCommit = true;
                        historyRecordDao.save(entity);
                        resetComponents();
                    }
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ToastUtils.showToastAtCenter(SendInformationActivity.this, archiveResponse.message);
                        }
                    });
                }

                @Override
                public void onError(String errorMessage) {
                    dismissProgressDialog();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ToastUtils.showToastAtCenter(SendInformationActivity.this, "网络链接失败，请稍后重试");
                        }
                    });
                }
            }).execute();
        } else {
            entity.isCommit = false;
            historyRecordDao.save(entity);
        }
    }

    private HistoryRecordEntity getHistoryRecordEntity(long id, boolean isCommit) {
        final HistoryRecordEntity entity = new HistoryRecordEntity();
        entity.vehicleNumber = provinceSpinner.getSelectedItem().toString() + letterSpinner.getSelectedItem().toString() + carNumber.getText().toString();
        entity.entranceName = stationName.getText().toString();//入站名称
        entity.exitName = SharedUtil.getString(this, "StationName");//出站名称
        entity.recordDate = dateTime.getText().toString();//记录时间
        entity.amount = totalAmount.getText().toString();//价格
        entity.comment = comment.getText().toString();//备注
        entity.merchandiseType = ((GoodsNameEntity) goodsName.getSelectedItem()).name;//货物类别
        entity.vehicleType = carType.getSelectedItem().toString();//车型
        entity.channelNumber = stationChannel.getSelectedItem().toString();//车道
        entity.carBodyImage = (String) carBodyImage.getTag();//车身照片
        entity.carFrontImage = (String) carFrontImage.getTag();//车牌照片
        entity.carBackImage = (String) carBackImage.getTag();//车尾照片
        entity.goodsImage = (String) goodsImage.getTag();//获取照片
        entity.adjustAmount = adjustAmount.getText().toString();//追加金额
        entity.video = videoPath.getText().toString();//视频文件
        entity.isGreen = isGreenSpinner.getSelectedItem().toString().equals("是");//是否绿通
        entity.isCommit = isCommit;//是否已提交,true：已提交，false:未提交
        entity.operatorId = SharedUtil.getLong(this, Constants.SHARED_KEY.uid);//操作员Id
        entity.leaderId = id;//审核员Id
        entity.tollCollector = tollCollectorSpinner.getSelectedItem().toString();//收费员工号
        entity.channelType = channelTypeSpinner.getSelectedItem().toString();
        entity.operatorName = SharedUtil.getString(this, Constants.SHARED_KEY.name);//操作员名称
        return entity;
    }

    private boolean validateInput() {
        if (carFrontImage.getTag() == null) {
            ToastUtils.showToastAtCenter(this, "请添加车牌照片");
            return false;
        }

        if (carBodyImage.getTag() == null) {
            ToastUtils.showToastAtCenter(this, "请添加车身照片");
            return false;
        }

        if (carBackImage.getTag() == null) {
            ToastUtils.showToastAtCenter(this, "请添加车尾照片");
            return false;
        }

        if (goodsImage.getTag() == null) {
            ToastUtils.showToastAtCenter(this, "请添加货物照片");
            return false;
        }

        if (!StringUtils.isNotNull(dateTime.getText().toString())) {
            ToastUtils.showToastAtCenter(this, "请输入入站时间");
            return false;
        }

        if (!StringUtils.isNotNull(carNumber.getText().toString())) {
            ToastUtils.showToastAtCenter(this, "请输入车牌号码");
            return false;
        } else {
            if (carNumber.getText().toString().length() < 5) {
                ToastUtils.showToastAtCenter(this, "车牌号码输入长度错误");
                return false;
            }
        }

        if (adjustAmountLayout.isShown() && !StringUtils.isNotNull(adjustAmount.getText().toString())) {
            ToastUtils.showToastAtCenter(this, "请添加追缴金额");
            return false;
        }

        if (!StringUtils.isNotNull(totalAmount.getText().toString())) {
            ToastUtils.showToastAtCenter(this, "请输入免(缴)金额");
            return false;
        }
        return true;
    }

    private void readyToVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoFileName = storeFileDirPath + File.separator + "VIDEO_" + Calendar.getInstance().getTimeInMillis() + ".3gp";
        Uri uri = Uri.fromFile(new File(videoFileName));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, RECORD_VIDEO);
    }

    private void readyToTakePicture(int index) {
        takePictureIdx = index;
        captureFileName = storeFileDirPath + File.separator + "IMG_" + Calendar.getInstance().getTimeInMillis() + ".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(captureFileName)));
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    Bitmap captureImage = BitmapUtils.getBitmapWithNewSize(captureFileName, ScreenUtils.dp2px(this, 70),
                            ScreenUtils.dp2px(this, 70));
                    switch (takePictureIdx) {
                        case ImageIndex.car_back:
                            carBackImage.setTag(captureFileName);
                            carBackImage.setImageBitmap(captureImage);
                            break;
                        case ImageIndex.car_front:
                            carFrontImage.setTag(captureFileName);
                            carFrontImage.setImageBitmap(captureImage);
                            break;
                        case ImageIndex.car_body:
                            carBodyImage.setTag(captureFileName);
                            carBodyImage.setImageBitmap(captureImage);
                            break;
                        case ImageIndex.goods:
                            goodsImage.setTag(captureFileName);
                            goodsImage.setImageBitmap(captureImage);
                            break;
                    }
                    break;
                case RECORD_VIDEO:
                    videoPath.setText(videoFileName);
                    break;
                case SELECT_STATION:
                    StationEntity stationEntity = (StationEntity) data.getSerializableExtra("Station");
                    if (stationEntity != null) {
                        stationName.setText(stationEntity.name);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void resetComponents() {
        carBackImage.setImageBitmap(null);
        carBodyImage.setImageBitmap(null);
        carFrontImage.setImageBitmap(null);
        goodsImage.setImageBitmap(null);

        carBackImage.setTag(null);
        carBodyImage.setTag(null);
        carFrontImage.setTag(null);
        goodsImage.setTag(null);

        carBackImage.setBackgroundResource(R.drawable.add_image);
        carBodyImage.setBackgroundResource(R.drawable.add_image);
        carFrontImage.setBackgroundResource(R.drawable.add_image);
        goodsImage.setBackgroundResource(R.drawable.add_image);

        videoPath.setText(null);
        stationName.setText(null);
        dateTime.setText(null);
        carNumber.setText(null);
        totalAmount.setText(null);
        adjustAmount.setText(null);
        adjustAmountLayout.setVisibility(View.GONE);
        goodsNameLayout.setVisibility(View.VISIBLE);
        isGreenSpinner.setSelection(0);
        carType.setSelection(0);
        stationChannel.setSelection(0);
        provinceSpinner.setSelection(0);
        letterSpinner.setSelection(0);
        goodsCategory.setSelection(0);
        goodsName.setSelection(0);
        tollCollectorSpinner.setSelection(0);
        comment.setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "查看历史数据");
        menu.add(0, 1, 1, "注销用户");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startActivity(new Intent(this, HistoryRecordActivity.class));
                break;
            case 1:
                SQLiteManager.getInstance(SendInformationActivity.this).release();
                SharedUtil.clear(this);
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //初始化产品名称
    static {
        goodsNameList = new ArrayList<GoodsNameEntity>();

        GoodsNameEntity typeChineseCabbage = new GoodsNameEntity(1, "白菜类", new ArrayList<GoodsNameEntity>());
        typeChineseCabbage.children.add(new GoodsNameEntity(2, "大白菜"));
        typeChineseCabbage.children.add(new GoodsNameEntity(3, "普通白菜(油菜，小青菜)"));
        typeChineseCabbage.children.add(new GoodsNameEntity(4, "菜薹"));
        goodsNameList.add(typeChineseCabbage);

        GoodsNameEntity typeCabbage = new GoodsNameEntity(5, "甘蓝类", new ArrayList<GoodsNameEntity>());
        typeCabbage.children.add(new GoodsNameEntity(6, "菜花"));
        typeCabbage.children.add(new GoodsNameEntity(7, "芥蓝"));
        typeCabbage.children.add(new GoodsNameEntity(8, "西兰花"));
        typeCabbage.children.add(new GoodsNameEntity(9, "结球甘蓝"));
        goodsNameList.add(typeCabbage);

        GoodsNameEntity rootVegetables = new GoodsNameEntity(10, "根菜类", new ArrayList<GoodsNameEntity>());
        rootVegetables.children.add(new GoodsNameEntity(11, "萝卜"));
        rootVegetables.children.add(new GoodsNameEntity(12, "胡萝卜"));
        rootVegetables.children.add(new GoodsNameEntity(13, "芜菁"));
        goodsNameList.add(rootVegetables);

        GoodsNameEntity greenLeafyVegetables = new GoodsNameEntity(14, "绿叶菜类", new ArrayList<GoodsNameEntity>());
        greenLeafyVegetables.children.add(new GoodsNameEntity(15, "芹菜"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(16, "菠菜"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(17, "莴笋"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(18, "生菜"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(19, "空心菜"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(20, "香菜"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(21, "茼蒿"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(22, "茴香"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(23, "苋菜"));
        greenLeafyVegetables.children.add(new GoodsNameEntity(24, "木耳菜"));
        goodsNameList.add(greenLeafyVegetables);

        GoodsNameEntity onionAndGarlic = new GoodsNameEntity(25, "葱蒜类", new ArrayList<GoodsNameEntity>());
        onionAndGarlic.children.add(new GoodsNameEntity(26, "洋葱"));
        onionAndGarlic.children.add(new GoodsNameEntity(27, "大葱"));
        onionAndGarlic.children.add(new GoodsNameEntity(28, "香葱"));
        onionAndGarlic.children.add(new GoodsNameEntity(29, "蒜苗"));
        onionAndGarlic.children.add(new GoodsNameEntity(30, "蒜苔"));
        onionAndGarlic.children.add(new GoodsNameEntity(31, "韭菜"));
        onionAndGarlic.children.add(new GoodsNameEntity(32, "大蒜"));
        onionAndGarlic.children.add(new GoodsNameEntity(33, "生姜"));
        goodsNameList.add(onionAndGarlic);

        GoodsNameEntity solanaceousFruit = new GoodsNameEntity(34, "茄果类", new ArrayList<GoodsNameEntity>());
        solanaceousFruit.children.add(new GoodsNameEntity(35, "茄子"));
        solanaceousFruit.children.add(new GoodsNameEntity(36, "青椒"));
        solanaceousFruit.children.add(new GoodsNameEntity(37, "辣椒"));
        solanaceousFruit.children.add(new GoodsNameEntity(38, "西红柿"));
        goodsNameList.add(solanaceousFruit);

        GoodsNameEntity beans = new GoodsNameEntity(39, "豆类", new ArrayList<GoodsNameEntity>());
        beans.children.add(new GoodsNameEntity(40, "扁豆"));
        beans.children.add(new GoodsNameEntity(41, "荚豆"));
        beans.children.add(new GoodsNameEntity(42, "豇豆"));
        beans.children.add(new GoodsNameEntity(43, "豌豆"));
        beans.children.add(new GoodsNameEntity(44, "四季豆"));
        beans.children.add(new GoodsNameEntity(45, "毛豆"));
        beans.children.add(new GoodsNameEntity(46, "蚕豆"));
        beans.children.add(new GoodsNameEntity(47, "豆芽"));
        beans.children.add(new GoodsNameEntity(48, "豌豆苗"));
        beans.children.add(new GoodsNameEntity(49, "四棱豆"));
        goodsNameList.add(beans);

        GoodsNameEntity melons = new GoodsNameEntity(50, "瓜类", new ArrayList<GoodsNameEntity>());
        melons.children.add(new GoodsNameEntity(51, "黄瓜"));
        melons.children.add(new GoodsNameEntity(52, "丝瓜"));
        melons.children.add(new GoodsNameEntity(53, "冬瓜"));
        melons.children.add(new GoodsNameEntity(54, "西葫芦"));
        melons.children.add(new GoodsNameEntity(55, "苦瓜"));
        melons.children.add(new GoodsNameEntity(56, "南瓜"));
        melons.children.add(new GoodsNameEntity(57, "佛手瓜"));
        melons.children.add(new GoodsNameEntity(58, "蛇瓜"));
        melons.children.add(new GoodsNameEntity(59, "节瓜"));
        melons.children.add(new GoodsNameEntity(60, "瓠瓜"));
        goodsNameList.add(melons);

        GoodsNameEntity aquaticVegetables = new GoodsNameEntity(61, "水生蔬菜", new ArrayList<GoodsNameEntity>());
        aquaticVegetables.children.add(new GoodsNameEntity(62, "莲藕"));
        aquaticVegetables.children.add(new GoodsNameEntity(63, "荸荠"));
        aquaticVegetables.children.add(new GoodsNameEntity(64, "水芹"));
        aquaticVegetables.children.add(new GoodsNameEntity(65, "茭白"));
        goodsNameList.add(aquaticVegetables);

        GoodsNameEntity freshFungus = new GoodsNameEntity(66, "新鲜食用菌", new ArrayList<GoodsNameEntity>());
        freshFungus.children.add(new GoodsNameEntity(67, "平菇"));
        freshFungus.children.add(new GoodsNameEntity(68, "原菇"));
        freshFungus.children.add(new GoodsNameEntity(69, "金针菇"));
        freshFungus.children.add(new GoodsNameEntity(70, "滑菇"));
        freshFungus.children.add(new GoodsNameEntity(71, "蘑菇"));
        freshFungus.children.add(new GoodsNameEntity(72, "木耳(不含干木耳)"));
        goodsNameList.add(freshFungus);

        GoodsNameEntity mixedVegetables = new GoodsNameEntity(73, "多年生和杂类蔬菜", new ArrayList<GoodsNameEntity>());
        mixedVegetables.children.add(new GoodsNameEntity(74, "竹笋"));
        mixedVegetables.children.add(new GoodsNameEntity(75, "芦笋"));
        mixedVegetables.children.add(new GoodsNameEntity(76, "金针菜(黄花菜)"));
        mixedVegetables.children.add(new GoodsNameEntity(77, "香椿"));
        goodsNameList.add(mixedVegetables);

        GoodsNameEntity benevolenceFruits = new GoodsNameEntity(78, "仁果类", new ArrayList<GoodsNameEntity>());
        benevolenceFruits.children.add(new GoodsNameEntity(79, "苹果"));
        benevolenceFruits.children.add(new GoodsNameEntity(80, "梨"));
        benevolenceFruits.children.add(new GoodsNameEntity(81, "海棠"));
        benevolenceFruits.children.add(new GoodsNameEntity(82, "山楂"));
        goodsNameList.add(benevolenceFruits);

        GoodsNameEntity drupe = new GoodsNameEntity(83, "核果类", new ArrayList<GoodsNameEntity>());
        drupe.children.add(new GoodsNameEntity(84, "桃"));
        drupe.children.add(new GoodsNameEntity(85, "李"));
        drupe.children.add(new GoodsNameEntity(86, "杏"));
        drupe.children.add(new GoodsNameEntity(87, "杨梅"));
        drupe.children.add(new GoodsNameEntity(88, "樱桃"));
        goodsNameList.add(drupe);

        GoodsNameEntity berries = new GoodsNameEntity(89, "浆果类", new ArrayList<GoodsNameEntity>());
        berries.children.add(new GoodsNameEntity(90, "葡萄"));
        berries.children.add(new GoodsNameEntity(91, "提子"));
        berries.children.add(new GoodsNameEntity(92, "草莓"));
        berries.children.add(new GoodsNameEntity(93, "猕猴桃"));
        berries.children.add(new GoodsNameEntity(94, "石榴"));
        berries.children.add(new GoodsNameEntity(95, "桑葚"));
        goodsNameList.add(berries);

        GoodsNameEntity citrus = new GoodsNameEntity(96, "柑橘类", new ArrayList<GoodsNameEntity>());
        citrus.children.add(new GoodsNameEntity(97, "橙"));
        citrus.children.add(new GoodsNameEntity(98, "桔"));
        citrus.children.add(new GoodsNameEntity(99, "柑"));
        citrus.children.add(new GoodsNameEntity(100, "柚"));
        citrus.children.add(new GoodsNameEntity(101, "柠檬"));
        goodsNameList.add(citrus);

        GoodsNameEntity fruit = new GoodsNameEntity(102, "热带及亚热带水果", new ArrayList<GoodsNameEntity>());
        fruit.children.add(new GoodsNameEntity(103, "香蕉"));
        fruit.children.add(new GoodsNameEntity(104, "菠萝"));
        fruit.children.add(new GoodsNameEntity(105, "龙眼"));
        fruit.children.add(new GoodsNameEntity(106, "荔枝"));
        fruit.children.add(new GoodsNameEntity(107, "橄榄"));
        fruit.children.add(new GoodsNameEntity(108, "枇杷"));
        fruit.children.add(new GoodsNameEntity(109, "椰子"));
        fruit.children.add(new GoodsNameEntity(110, "芒果"));
        fruit.children.add(new GoodsNameEntity(111, "杨桃"));
        fruit.children.add(new GoodsNameEntity(112, "木瓜"));
        fruit.children.add(new GoodsNameEntity(113, "火龙果"));
        fruit.children.add(new GoodsNameEntity(114, "番石榴"));
        fruit.children.add(new GoodsNameEntity(115, "莲雾"));
        goodsNameList.add(fruit);

        GoodsNameEntity assortedMelon = new GoodsNameEntity(116, "什果类", new ArrayList<GoodsNameEntity>());
        assortedMelon.children.add(new GoodsNameEntity(117, "枣"));
        assortedMelon.children.add(new GoodsNameEntity(118, "柿子"));
        assortedMelon.children.add(new GoodsNameEntity(119, "无花果"));
        goodsNameList.add(assortedMelon);

        GoodsNameEntity melonAndFruit = new GoodsNameEntity(120, "瓜果类", new ArrayList<GoodsNameEntity>());
        melonAndFruit.children.add(new GoodsNameEntity(121, "西瓜"));
        melonAndFruit.children.add(new GoodsNameEntity(122, "甜瓜"));
        melonAndFruit.children.add(new GoodsNameEntity(123, "哈密瓜"));
        melonAndFruit.children.add(new GoodsNameEntity(124, "香瓜"));
        melonAndFruit.children.add(new GoodsNameEntity(125, "伊丽莎白瓜"));
        melonAndFruit.children.add(new GoodsNameEntity(126, "华莱士瓜"));
        goodsNameList.add(melonAndFruit);

        GoodsNameEntity freshAquaticProducts = new GoodsNameEntity(127, "鲜活水产品", new ArrayList<GoodsNameEntity>());
        freshAquaticProducts.children.add(new GoodsNameEntity(128, "鱼类"));
        freshAquaticProducts.children.add(new GoodsNameEntity(129, "虾类"));
        freshAquaticProducts.children.add(new GoodsNameEntity(130, "贝类"));
        freshAquaticProducts.children.add(new GoodsNameEntity(131, "蟹类"));
        goodsNameList.add(freshAquaticProducts);

        GoodsNameEntity otherAquaticProducts = new GoodsNameEntity(132, "其它水产品", new ArrayList<GoodsNameEntity>());
        otherAquaticProducts.children.add(new GoodsNameEntity(133, "海带"));
        otherAquaticProducts.children.add(new GoodsNameEntity(134, "紫菜"));
        otherAquaticProducts.children.add(new GoodsNameEntity(135, "海蜇"));
        otherAquaticProducts.children.add(new GoodsNameEntity(136, "海参"));
        goodsNameList.add(otherAquaticProducts);

        GoodsNameEntity livestock = new GoodsNameEntity(137, "家畜", new ArrayList<GoodsNameEntity>());
        livestock.children.add(new GoodsNameEntity(138, "猪"));
        livestock.children.add(new GoodsNameEntity(139, "牛"));
        livestock.children.add(new GoodsNameEntity(140, "羊"));
        livestock.children.add(new GoodsNameEntity(141, "马"));
        livestock.children.add(new GoodsNameEntity(142, "驴(骡)"));
        goodsNameList.add(livestock);

        GoodsNameEntity poultry = new GoodsNameEntity(143, "家禽", new ArrayList<GoodsNameEntity>());
        poultry.children.add(new GoodsNameEntity(144, "鸡"));
        poultry.children.add(new GoodsNameEntity(145, "鸭"));
        poultry.children.add(new GoodsNameEntity(146, "鹅"));
        poultry.children.add(new GoodsNameEntity(147, "家兔"));
        poultry.children.add(new GoodsNameEntity(148, "食用蛙类"));
        goodsNameList.add(poultry);

        GoodsNameEntity other = new GoodsNameEntity(149, "其他", new ArrayList<GoodsNameEntity>());
        other.children.add(new GoodsNameEntity(150, "蜜蜂(转地放蜂)"));
        other.children.add(new GoodsNameEntity(151, "其他"));
        goodsNameList.add(other);

        GoodsNameEntity eggs = new GoodsNameEntity(152, "新鲜的肉、蛋、奶", new ArrayList<GoodsNameEntity>());
        eggs.children.add(new GoodsNameEntity(153, "新鲜的鸡蛋"));
        eggs.children.add(new GoodsNameEntity(154, "鸭蛋"));
        eggs.children.add(new GoodsNameEntity(155, "鹅蛋"));
        eggs.children.add(new GoodsNameEntity(156, "鹌鹑蛋"));
        eggs.children.add(new GoodsNameEntity(157, "新鲜的家畜肉和家禽肉"));
        eggs.children.add(new GoodsNameEntity(158, "新鲜奶"));
        goodsNameList.add(eggs);
    }
}
