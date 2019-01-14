package com.liujc.xpaydemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.xpay.XPay
import com.android.xpay.alipay.AliPayReq
import com.android.xpay.wechatpay.WechatPayReq
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 支付宝支付
        btn_alipay.setOnClickListener(View.OnClickListener { testAliPay() })
        // 支付宝支付安全
        btn_alipay_safely.setOnClickListener(View.OnClickListener { testAliPaySafely() })
        // 微信支付
        btn_wechatpay.setOnClickListener(View.OnClickListener { XPay.getInstance().sendPayRequest(WechatPayReq()) })
    }

    /**
     * 支付宝支付测试
     */
    fun testAliPay() {
        val rsa_private = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANR0fT+ZwctozdOMKUhcJQAZk819wl1PfY3uvXclDI+OjNGuuoo1TKHWCa61d18ZJJ/nKyOwMGb1/doO3xQkWXKN1MjFD9ZHGVDZs/u/Y8w7AWYyxYuIjinlmOj5UWPIFmUHm0+6j9g+k3YKJZQP1Wv3/gKa6/xaBk5zsH7UTbjBAgMBAAECgYEAw2PzALg3DAzyJxnjqcHc4Y+kaDu9srV1okAsDdSMDccraXiJ5KwKM1i3BO+tvk9QaKNhAGx8x2R7N72vmkWn4E6CsXxR6wmb0wkw5imaXKuMPYRwnIex7BzEArx99IkKSj/ZTKKV87MbYTfHEj3SU7+J6ELq+3c538AP5TADfBUCQQDzkuJVEnzgbDPyjA6D2y1W/GQpGQyyRE045XU52Ybfmxkkiw/DnrNeGpvK2RQptT1u6/tqrsyXgGsAz8xRvSTzAkEA30swaLqucpYbR8iDTlSGnyy+7UnM1GaZr9bY6/TIBzmA/sD5tbehLM8oOTlRiz0aj42m7FXyaiST5or2sn0oewJBALGD5EXiAnbBBR8I6e85BpM1wH2fTvyBANKtkEDIAx7l924Fl0iXWdwEgnRUvvgiqZI0k/hNSrhDlDh0OVF9CvcCQDQVvtH/EagK2Ywx+mbwEoLYliSVfWDiGeFJVUocy/fbcvp5mwXHMqJYJALNGvdGpoZrvU8NcUFPDOzO52KVDTcCQQCp2DfBncWndN/cQWEfDibfMbYvIMHhdpVhlYZgYboszjM9rRAppO+XtF7WDVoQFT5zRyJQZplUjuVel7ctR9WY"
        val rsa_public = "dsfsfewwe"
        val partner = "xxxxxxxxxxxxxx"
        val seller = "test@163.com"

        val activity = this
        val outTradeNo = "20180611"
        val price = "0.01"
        val orderSubject = "测试的商品"
        val orderBody = "该测试商品的详细描述"
        val callbackUrl = ""


        val rawAliOrderInfo = AliPayReq.AliOrderInfo()
                .setPartner(partner) //商户PID || 签约合作者身份ID
                .setSeller(seller)  // 商户收款账号 || 签约卖家支付宝账号
                .setOutTradeNo(outTradeNo) //设置唯一订单号
                .setSubject(orderSubject) //设置订单标题
                .setBody(orderBody) //设置订单内容
                .setPrice(price) //设置订单价格
                .setCallbackUrl(callbackUrl) //设置回调链接
                .createOrderInfo() //创建支付宝支付订单信息

        val aliPayReq = AliPayReq.Builder()
                .with(activity)//Activity实例
                .setRsaPrivate(rsa_private) //设置私钥
                .setRsaPublic(rsa_public)//设置公钥
                .setRawAliPayOrderInfo(rawAliOrderInfo)
                .create()
                .setOnAliPayListener(null)
        XPay.getInstance().sendPayRequest(aliPayReq)
    }

    /**
     * 安全的支付宝支付测试
     */
    fun testAliPaySafely() {
        val partner = "2088811260430944"
        val seller = "xxxxx@163.com"

        val activity = this
        val outTradeNo = "20180611"
        val price = "0.01"
        val orderSubject = "测试的商品"
        val orderBody = "该测试商品的详细描述"
        val callbackUrl = ""


        val rawAliOrderInfo = AliPayReq.AliOrderInfo()
                .setPartner(partner) //商户PID || 签约合作者身份ID
                .setSeller(seller)  // 商户收款账号 || 签约卖家支付宝账号
                .setOutTradeNo(outTradeNo) //设置唯一订单号
                .setSubject(orderSubject) //设置订单标题
                .setBody(orderBody) //设置订单内容
                .setPrice(price) //设置订单价格
                .setCallbackUrl(callbackUrl) //设置回调链接
                .createOrderInfo() //创建支付宝支付订单信息


        //TODO 这里需要从服务器获取用商户私钥签名之后的订单信息
        val signAliOrderInfo = getSignAliOrderInfoFromServer(rawAliOrderInfo)

        val aliPayReq = AliPayReq.Builder()
                .with(activity)
                .setRawAliPayOrderInfo(rawAliOrderInfo)
                .setSignedAliPayOrderInfo(signAliOrderInfo)
                .create()
                .setOnAliPayListener(null)
        XPay.getInstance().sendPayRequest(aliPayReq)
    }

    /**
     * 获取签名后的支付宝订单信息  (用商户私钥RSA加密之后的订单信息)
     * @param rawAliOrderInfo
     * @return
     */
    private fun getSignAliOrderInfoFromServer(rawAliOrderInfo: String): String {
        return "test"
    }

}
