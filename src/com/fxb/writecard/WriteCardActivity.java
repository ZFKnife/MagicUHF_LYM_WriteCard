package com.fxb.writecard;

import android.app.Activity;
import android.hardware.uhf.magic.IUHFView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    //  private Handler mHandler = new WriteCardActivity.MainHandler();
    private String m_strresult = "";

    /*
     *设置EPC参数
     * */
    private byte btMemBank;
    private int nadd;
    private int ndatalen;
    private String mimaStr;
    private ReaderWritePresenter mReaderPresenter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writecard);

//        初始化界面
        initView();
//        注册 hander
//    reader.reg_handler(mHandler);
        mReaderPresenter = new ReaderWritePresenter(this);
        mReaderPresenter.setEPCtext();
//        事件监听
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

    /**
     * 读取EPC
     */
    private void readEpc() {

    }

//  /**
//   * 读取tag
//   */
//  private void readTag() {
//    if ("".equals(reader.m_strPCEPC)) {
//      Toast.makeText(WriteCardActivity.this, "Please select the EPC tags",
//        Toast.LENGTH_SHORT).show();
//      return;
//    }
//
//    //操作区域 EPC(代码为1)
//    btMemBank = (byte) 1;
//    //起始地址 2
//    nadd = 2;
//    //读取长度
//    ndatalen = 12;
//    //密码
//    mimaStr = "00000000";
//
//    if (mimaStr == null || mimaStr.equals("")) {
//      m_strresult += "Please enter your 8 - digit password!!\n";
//      tv_ResultView.setText(m_strresult);
//      return;
//    }
//    byte[] passw = reader.stringToBytes(mimaStr);
//    byte[] epc = reader.stringToBytes(reader.m_strPCEPC);
//    if (null != epc) {
//      if (btMemBank == 1) {
//        reader.ReadLables(passw, epc.length, epc,
//          (byte) btMemBank, nadd, ndatalen);
//      } else {
//        reader.ReadLables(passw, epc.length, epc,
//          (byte) btMemBank, nadd, ndatalen);
//      }
//    }
//  }

    /*
    * 写tag
    * */
    private void writeTag() {
//    if ("".equals()) {
//      Toast.makeText(WriteCardActivity.this, "Please select the EPC tags",
//        Toast.LENGTH_SHORT).show();
//      return;
//    }
//    if (TextUtils.isEmpty(et_Coding.getText().toString().trim())) {
//      ToastUtil.getShortToastByString(WriteCardActivity.this, "编码不能为空");
//      return;
//    } else if (TextUtils.isEmpty(et_CarNum.getText().toString().trim())) {
//      ToastUtil.getShortToastByString(WriteCardActivity.this, "车牌号不能为空");
//      return;
//    } else {
        //操作区域 EPC(代码为1)
//      btMemBank = (byte) 1;
        //起始地址 2
//      nadd = 2;
        //读取长度
//      ndatalen = 12;
//      //密码
//      mimaStr = "00000000";
//
//      if (mimaStr == null || mimaStr.equals("")) {
//        m_strresult += "Please enter your 8 - digit password!!\n";
//        tv_ResultView.setText(m_strresult);
//        return;
//      }
//      byte[] passw = reader.stringToBytes(mimaStr);
//      byte[] pwrite = new byte[ndatalen * 2];

        String pn = et_CarNum.getText().toString();
//      String a = pn.substring(0, 1);
//      String m = "";
//      if ("晋".equals(a)) {
//        m = pn.replace("晋", "1");
//      } else if ("陕".equals(a)) {
//        m = pn.replace("陕", "2");
//      } else if ("豫".equals(a)) {
//        m = pn.replace("豫", "3");
//      } else if ("冀".equals(a)) {
//        m = pn.replace("冀", "4");
//      } else if ("鲁".equals(a)) {
//        m = pn.replace("鲁", "5");
//      } else if (StringUtils.isNumeric(a) && Integer.parseInt(a) > 0 && Integer.parseInt(a) < 5) {
//        m = pn;
//      }
//
//      String dataE = et_Coding.getText().toString().trim() + "," + pn + ",";
        String name = "侯马永济第二煤场";
        String dataE = "000000000000000000000000000000000000000";
        Log.e("写入的数据=================", dataE);
        mReaderPresenter.writeCard(dataE);
//      System.arraycopy(myByte, 0, pwrite, 0,
//        myByte.length > ndatalen * 2 ? ndatalen * 2
//          : myByte.length);
//      byte[] epc = reader.stringToBytes(reader.m_strPCEPC);
//      {
//        reader.Writelables(passw, epc.length, epc, btMemBank,
//          (byte) nadd, (byte) ndatalen * 2, pwrite);
//      }
    }

//  }

    @Override
    public void setEPCtext(String EPC) {
        tv_Epc.setText(EPC);
    }

    @Override
    public void showToast(String text) {

    }

//  /*
//  * 注册 hander
//  * */
//  private class MainHandler extends Handler {
//    @Override
//    public void handleMessage(Message msg) {
//      if (msg.what == reader.editepcsmsg) {
//        tv_ResultView.setText((String) msg.obj);
//      }
//      if (msg.what == reader.msgreadwrireepc) {
//        if (msg.obj != null) {
//          m_strresult = (String) msg.obj;
//          tv_ResultView.setText(StringUtils.toStringHex(m_strresult));
//        }
//      }
//      if (msg.what == reader.msgreadwrite) {
//        if (msg.obj != null) {
//          m_strresult = (String) msg.obj;
//          tv_ResultView.setText(m_strresult);
//        }
//      }
//      //读卡信息
//      if (msg.what == reader.msgreadepc) {
//        String readerdata = (String) msg.obj;
//        tv_Epc.setText(readerdata);
//        reader.m_strPCEPC = readerdata;
//
//      }
//
//    }
//  }

}
