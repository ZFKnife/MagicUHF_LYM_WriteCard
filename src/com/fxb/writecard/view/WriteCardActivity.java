package com.fxb.writecard.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fxb.writecard.R;
import com.fxb.writecard.presenter.ReaderWritePresenter;

/**
 * Created by DXL on 2017-06-24.
 */

public class WriteCardActivity extends Activity implements IUHFView {
    private static final String TAG = "WriteCard";

    private TextView tv_Epc;
    private EditText et_Coding;
    private EditText et_CarNum;
    private TextView tv_ResultView;

    private Button btn_ReadingEpc;
    private Button btn_Reading;
    private Button btn_writting;

    private String m_strresult = "";

    private ReaderWritePresenter mReaderPresenter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writecard);
        initView();
        mReaderPresenter = new ReaderWritePresenter(this);
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
                mReaderPresenter.setEPCtext();
            }
        });
        btn_Reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReaderPresenter.readCard();
            }
        });
        btn_writting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeTag();
            }
        });
    }


    private void writeTag() {
        String pn = et_CarNum.getText().toString();
        String name = "侯马永济第二煤场";
        String dataE = "KD00001,晋A56BF3，";
        Log.e("写入的数据=================", dataE);
        mReaderPresenter.writeCard(dataE);
    }
    @Override
    public void setEPCtext(String EPC) {
        tv_Epc.setText(EPC);
    }

    @Override
    public void showToast(String text) {

    }
}
