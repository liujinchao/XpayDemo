package com.android.xpay;

/**
 * 类名称：XPay
 * 创建者：Create by liujc
 * 创建时间：Create on 2018/6/11
 * 描述：支付方法调用类
 */
public class XPay {
    private volatile static XPay mInstance;

    public static XPay getInstance(){
        if(mInstance == null){
            synchronized (XPay.class){
                if(mInstance == null){
                    mInstance = new XPay();
                }
            }
        }
        return mInstance;
    }


    /**
     * 支付请求
     * @param payReq  支付宝支付：AliPayReq  微信支付：WechatPayReq
     */
    public void sendPayRequest(IPayReq payReq){
        payReq.send();
    }
}

