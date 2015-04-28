package com.stardust.express.app.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.R;
import com.stardust.express.app.entity.UserEntity;
import com.stardust.express.app.utils.ToastUtils;

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
    private Spinner goodsName;
    private EditText comment;
    private TextView operator;

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
        goodsName = (Spinner) findViewById(R.id.goods_name);
        comment = (EditText) findViewById(R.id.comment);
        operator = (TextView) findViewById(R.id.operator);
        findViewById(R.id.submit_button).setOnClickListener(this);
        findViewById(R.id.reset_button).setOnClickListener(this);
    }

    @Override
    protected void fillData() {
        UserEntity userEntity = (UserEntity) getIntent().getSerializableExtra("Operator");
        operator.setText(userEntity.userName);
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
        }
    }

    private void resetComponents() {
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
}
