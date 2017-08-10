package com.fxb.writecard.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fxb.writecard.R;
import com.fxb.writecard.presenter.WriteCardPresenter;

/**
 * zf 2033152950
 * Created by DXL on 2017-06-24.
 */

public class WriteCardActivity extends Activity implements IWriteCardView {

    private TextView tv_Epc;
    private EditText et_Coding;
    private EditText et_CarNum;
    private TextView tv_ResultView;

    private Button btn_ReadingEpc;
    private Button btn_Reading;
    private Button btn_writting;


    private WriteCardPresenter presenter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writecard);
        initView();
        presenter = new WriteCardPresenter(this);

        presenter.setEPCtext();

        onClick();
    }

    /*
    * 初始化界面
    * */
    private void initView() {
        tv_Epc = (TextView) findViewById(R.id.tv_writecard_epc);
        et_Coding = (EditText) findViewById(R.id.et_coding);
        et_CarNum = (EditText) findViewById(R.id.et_carnum);
        tv_ResultView = (TextView) findViewById(R.id.tv_resultView);

        btn_ReadingEpc = (Button) findViewById(R.id.btn_writecard_readepc);
        btn_Reading = (Button) findViewById(R.id.btn_writecard_reading);
        btn_writting = (Button) findViewById(R.id.btn_writecard_writting);
    }

    public void onClick() {
        btn_ReadingEpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setEPCtext();
            }
        });
        btn_Reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.readCard();
            }
        });
        btn_writting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.writeCard();
            }
        });
    }


    @Override
    public void setEPCtext(String EPC) {
        tv_Epc.setText(EPC);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDialog(String str) {

    }

    @Override
    public String getCarNum() {
        return et_CarNum.getText().toString().trim();
    }

    @Override
    public String getCardNum() {
        return et_Coding.getText().toString().trim();
    }

    @Override
    public void setText(String str) {
        tv_ResultView.setText(str);
    }
}
