package com.fxb.writecard.view;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public interface IWriteCardView extends IUHFViewBase {

    public String getCarNum();

    public String getCardNum();

    public void setText(String str);
}
