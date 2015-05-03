package com.stardust.express.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.R;
import com.stardust.express.app.entity.UserEntity;
import com.stardust.express.app.utils.ToastUtils;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Gyb on 2015/4/28.
 */
public class SendInformationActivity extends BaseActivity implements View.OnClickListener {

    private ImageView carFrontImage;
    private ImageView carBodyImage;
    private ImageView carBackImage;
    private ImageView goodsImage;

    private TextView stationName;
    private EditText dateTime;
    private EditText carNumber;
    private EditText carType;
    private EditText totalAmount;
    private EditText stationChannel;
    private EditText goodsName;
    private EditText comment;
    private TextView operator;

    private static final int TAKE_PICTURE = 400;
    private String storeFileDirPath;
    private String captureFileName;
    private int takePictureIdx;

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
        carFrontImage = (ImageView) findViewById(R.id.car_front_image);
        carBodyImage = (ImageView) findViewById(R.id.car_body_image);
        carBackImage = (ImageView) findViewById(R.id.car_back_image);
        goodsImage = (ImageView) findViewById(R.id.goods_image);
        stationName = (TextView) findViewById(R.id.station_name);
        dateTime = (EditText) findViewById(R.id.date_time);
        carNumber = (EditText) findViewById(R.id.car_number);
        carType = (EditText) findViewById(R.id.car_type);
        totalAmount = (EditText) findViewById(R.id.total_amount);
        stationChannel = (EditText) findViewById(R.id.station_channel);
        goodsName = (EditText) findViewById(R.id.goods_name);
        comment = (EditText) findViewById(R.id.comment);
        operator = (TextView) findViewById(R.id.operator);
        findViewById(R.id.submit_button).setOnClickListener(this);
        findViewById(R.id.reset_button).setOnClickListener(this);
        carFrontImage.setOnClickListener(this);
        carBodyImage.setOnClickListener(this);
        carBackImage.setOnClickListener(this);
        goodsImage.setOnClickListener(this);
    }

    @Override
    protected void fillData() {
        UserEntity userEntity = (UserEntity) getIntent().getSerializableExtra("Operator");
        operator.setText(userEntity.userName);
        stationName.setText(userEntity.stationName);

        File dir = getExternalFilesDir("/captures");
        if (dir != null) {
            if (!dir.exists())
                dir.mkdirs();
            storeFileDirPath = dir.getAbsolutePath();
        }

        initGoodsNames();
    }

    private void initGoodsNames() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_button:
                ToastUtils.showToast(this, "提交成功");
                resetComponents();
                break;
            case R.id.reset_button:
                resetComponents();
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
        }
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
        if (resultCode == RESULT_OK && requestCode == TAKE_PICTURE) {
            Bitmap captureImage = zoomImg(captureFileName, 100, 100);
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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void resetComponents() {
        carBackImage.setImageBitmap(null);
        carBodyImage.setImageBitmap(null);
        carFrontImage.setImageBitmap(null);
        goodsImage.setImageBitmap(null);

        carBackImage.setBackgroundResource(R.drawable.add_image);
        carBodyImage.setBackgroundResource(R.drawable.add_image);
        carFrontImage.setBackgroundResource(R.drawable.add_image);
        goodsImage.setBackgroundResource(R.drawable.add_image);

        dateTime.setText(null);
        carNumber.setText(null);
        carType.setText(null);
        totalAmount.setText(null);
        stationChannel.setText(null);
        goodsName.setSelection(0);
        comment.setText(null);
    }

    // 缩放图片
    public static Bitmap zoomImg(String path, int newWidth, int newHeight) {
        Bitmap bm = BitmapFactory.decodeFile(path);
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "退出");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
