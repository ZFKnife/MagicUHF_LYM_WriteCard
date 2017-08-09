package com.fxb.writecard.presenter;

import com.fxb.writecard.view.IWriteCardView;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class WriteCardPresenter extends Presenter {

    private IWriteCardView iWriteCardView;

    public WriteCardPresenter(IWriteCardView iWriteCardView) {
        super(iWriteCardView);
        this.iWriteCardView = iWriteCardView;
    }

    public void readCard() {
        readTrue(0, 9);
    }

    public void writeCard() {
        StringBuilder sb = new StringBuilder();
        String cardNum = iWriteCardView.getCardNum();
        if (cardNum.equals("")) {
            sb.append(cardNum);
            iWriteCardView.showToast("卡牌编号不可以为空！");
            return;
        }
        String carNum = iWriteCardView.getCarNum();
        if (carNum.equals("")) {
            sb.append(carNum);
            iWriteCardView.showToast("车牌号不可以为空！");
            return;
        }
        writeTrue(sb.toString());
    }

    @Override
    void readResponse(String str) {
        iWriteCardView.setText(str);
        iWriteCardView.showToast("读取成功！");
    }

    @Override
    void writeResponse() {
        iWriteCardView.showToast("写入成功！");
    }


}
