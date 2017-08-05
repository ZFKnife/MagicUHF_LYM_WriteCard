package com.fxb.writecard;

import android.hardware.uhf.magic.AbstractUHFModel;
import android.hardware.uhf.magic.IResponse;
import android.hardware.uhf.magic.IUHFView;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.fxb.writecard.model.ReadModel;
import com.olc.uhf.tech.ISO1800_6C;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class ReaderWritePresenter {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private final String TAG = ReaderWritePresenter.class.getSimpleName();

    private IUHFView iuhfView;

    private ISO1800_6C uhf_6c;

    private AbstractUHFModel abstractUHFModel;

    private ReaderWritePresenter() {
    }

    public ReaderWritePresenter(IUHFView iuhfView) {
        this.iuhfView = iuhfView;
        this.abstractUHFModel = new ReadModel();
        setEPCtext();
    }

    public void setEPCtext() {
        String epc = abstractUHFModel.inventory();
        if (epc == null) {
            return;
        }
        iuhfView.setEPCtext(epc);
    }

    public void readCard() {
        abstractUHFModel.setnSA(2);
        abstractUHFModel.setnDL(32);
        new Thread(new Runnable() {
            @Override
            public void run() {
                abstractUHFModel.ReadDate(new IResponse() {
                    @Override
                    public void Response(int code, String str) {
                        Log.i(TAG, "Response: ------ " + str);
                        String[] spStr = str.split(",");
                        if (code == 0) {

                        } else {

                        }
                    }
                });
            }
        }).start();
    }

    public void writeCard(String sb) {
        if (sb == null) {
            Log.e(TAG, "sb is null");
            return;
        }
        int nDL = 20;
        byte[] pwrite = new byte[nDL * 2];
        byte[] date = abstractUHFModel.stringToBytes(StringUtils.toHexString(sb));
//        长度校对
        System.arraycopy(date, 0, pwrite, 0,
                date.length > nDL * 2 ? nDL * 2
                        : date.length);
        Log.d(TAG, " ---- date = " + pwrite);
        abstractUHFModel.setnSA(0);
        abstractUHFModel.setnDL(nDL);
        abstractUHFModel.WriteDate(pwrite, new IResponse() {
            @Override
            public void Response(int code, String str) {
                Log.i(TAG, "Response: -------");
                if (code == 0) {

                } else {

                }
            }
        });
    }
}



