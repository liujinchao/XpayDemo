package com.android.xpay.listen;

/**
 * 类名称：OnXPayListener
 * 创建者：Create by liujc
 * 创建时间：Create on 2018/6/11
 * 描述：支付监听
 */
public interface OnXPayListener {
    void onPaySuccess(String resultInfo);
    void onPayFailure(String resultStatus);
    void onPayConfirmimg(String resultInfo);
}