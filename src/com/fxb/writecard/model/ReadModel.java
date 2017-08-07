package com.fxb.writecard.model;

import android.os.RemoteException;
import android.util.Log;

import com.fxb.writecard.util.StringUtils;
import com.olc.uhf.tech.IUhfCallback;

import java.util.Iterator;
import java.util.List;

/**
 * zf 2033152950
 * Created by Administrator on 2017/8/4 0004.
 */

public class ReadModel extends AbstractUHFModel {

    private String strEpc;

    @Override
    public String inventory() {
        IUhfCallback callback = new IUhfCallback.Stub() {
            @Override
            public void doInventory(List<String> str) throws RemoteException {
                for (int i = 0; i < str.size(); i++) {
                    String strepc = (String) str.get(i);
                    Log.d("wyt", "RSSI=" + strepc.substring(0, 2));
                    Log.d("wyt", "PC=" + strepc.substring(2, 6));
                    Log.d("wyt", "EPC=" + strepc.substring(2, 6) + strepc.substring(6));
                    strEpc = strepc.substring(2, 6) + strepc.substring(6);
                    byteEpc = stringToBytes(strEpc);
                }
            }

            @Override
            public void doTIDAndEPC(List<String> str) throws RemoteException {
                for (Iterator it2 = str.iterator(); it2.hasNext(); ) {
                    String strepc = (String) it2.next();
                    int nlen = Integer.valueOf(strepc.substring(0, 2), 16);
                }
            }
        };
        uhf_6c.inventory(callback);
        return strEpc;
    }

    @Override
    public void ReadDate(IResponse iResponse) {
        byte[] dataout = new byte[nDL * 2];
        if (byteEpc == null) {
            Log.d(TAG, "ReadDate: ---- byteEpc is null");
            return;
        }
        if (uhf_6c == null) {
            return;
        }
        Log.d(TAG, " ---- mimaStr=" + mimaStr + ",byteEpc=" + byteEpc + ",MemBank=" + MemBank + ",nSA=" + nSA + ",nDl=" + nDL);
        int result = uhf_6c.read(mimaStr, byteEpc.length, byteEpc, MemBank,
                nSA, nDL, dataout, 0, nDL);
        Log.i(TAG, "ReadDate: dataout="+dataout);
        iResponse.Response(result, StringUtils.toStringHex(BytesToString(dataout, 0, nDL)));
    }

    @Override
    public void WriteDate(byte[] date, IResponse iResponse) {
        if (byteEpc == null) {
            return;
        }
        Log.d(TAG, " --- WriteDate is start");
        int result = uhf_6c.write(mimaStr, byteEpc.length, byteEpc, MemBank,
                nSA, nDL, date);
        iResponse.Response(result, null);
    }
}
