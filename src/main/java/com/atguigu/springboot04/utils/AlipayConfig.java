package com.atguigu.springboot04.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102100733029";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDDdeXqab+oFp7ginbXMvo1ibee11BIoe9sTJ39ITkjbspG+MWYaejGY3jJf45T6Xwu4CqHw/2nhjzGPuGncdsyojDQ4CA6kyxxi0+Ww/ng9qNYrCyO7T+GSaBPGMDQb9qE8iptbTdBbFEtUeQ1h0XcSNcNWqOoREDdSN4SlviQZSMZ/KZZa8GAdDT4FJDLrxfAbeAlYJFsKApmdyRrkfV7k9Z5TZy1MjE1A8UVqMi3rZoDu++7sRkhQ0FT3dElIfioQEM04xUF8pXwy2jEZxwmgDlNiJ1DAqQ4GN927oGUFQh3R69O7hkZAB89vopPWlptFs2EdlIOtbuWFBgOeaKbAgMBAAECggEAcrnJ2Lx3PS8B1CQRuyB10mTiPT5+JhO+l1+lQAV3J0AJRH0SZfKMKZ4LMKJJJSGfvVfgPs4gHWtiUq5n/sOKeH7QjllvMVJfPMobTg/7pDrAB/UPNlk09xpSGPsbeXyd03jcxFvdme38+8/cVjrvG2w5peH0u3hMZlY12s14Wi+qq7JOFOj8dTH5UfSwRhYKN7efBWm6TMP8d0TJCKIys0ZmK3Wno3zYrb1xFv+hjtO6BN/k8g11uFLHMzCYHHLH+SdPDAQn6C2OYfqzyoik9CUPVY5Mdn+SB/PMF5hPMxiwxevgOWP1aS1VD6ykxeq8nSAV0EpXEt58+aHWyd0FgQKBgQD5pcHzFO5iFVoCOPu51url9LNwBAow3d2inESlovEXFsj5qQn+mKNrbTPb6hsJBf60PwschkiW1yC0W5TkawWodEOqDlWvhmDil/z69+ZKL8YoGQq9K15vEf4uGHV0bGJ1gGNjg72iVLCrrtNtx8cTwH73eLBsay5I8JaskpQXQQKBgQDIbyiG219owseh1vtiA9aG9JyBXCSlVtUJC/lhKnnjSefE5yHPLaa8f90Rqcgwll5zxstXCEVdd/cLB7ZwNDfgL4TwY7loKHpe8aVSeLuuTrF464PKpPs0o7ueUdUNXzpieLv3KNulhjvUqYHfQLV5DcrNCgj4jRI6C13iqig+2wKBgQCkfssYT89vg6AmXMPJRUrMMPvXMGJCpr52ej9lYMM5MoNEtlRmlM8BZxhHrK8ZuACHZg79QaIs+h2Lmdi21aoJwGohcIaXPJU0WW+RMq1kbqxEGZQQ57W2vAVe/DDQgOYzhGB43wKiJumuhmi8KmoLoshGahjdp5X868JsfbzkwQKBgAPHtOf2MzJymfk/aLZMhDs/WcRGzJohrRriku12M55JwolAIq8ZmU9syeqPXSstatKcKTGXAlfk70/R8WrkUTl1K/isqM6CFOy9efS4OjHrYgV5y56CxgoUHHG78VyzMxKwXDspFhtlELPODpj7S3DhBbYgPlMRtgpKNJcVxnSJAoGBAKBVVyPnKt1lD+41LGErhFJZA61OV6ztXL5PtnioF8PDm4WTFf4e65Egc/77kwOq5Jy+Tj0fXn+MZKbcWV/0M4fXNxn4fdbhAEMjn3evMyrcNYjqLYoFJFWLtquT9KYEKConaGvgUtMp2E89Havm4xU+W0fRpL5JFstwOQ/LK6Sb";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7X0wUs1jmCdI4GCZ4SE5mQS7yvvGF3xuPTKGTXdzKTo5HTVWu6nmRtIdjREwETlHBDPy0E5gdw3xE3UQnJy4mwxMyMc4J0lg7zo0pvE/HJTz1c0ShyYzgicWPYllipIKvaeN+wEycnZv8h982z5rbfQRBqj+oHttwf4SGYCn/KmR38umWI5d3aDa0KQRgII6sgpx1+x61aWfA8ujQI4UOt7EyyKwN8Bf1/AmkSbkmBq+oCS9nvd7PpnQaB3FOdK07E3QKSz+sB8nabAZe+aNkL+0IPr4pLY7XW478OykcRJbKkAkgpRrqyD0e7WVrU0Jq61G3gQr2ZWkEAZcdAanLQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/pay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问,工程公网访问地址
    public static String return_url = "http://localhost:8080/pay";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    //日志路径
    public static String log_path = "D:\\log";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

