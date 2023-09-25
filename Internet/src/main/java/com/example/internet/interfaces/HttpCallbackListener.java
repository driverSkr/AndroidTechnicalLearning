package com.example.internet.interfaces;

/*
* 自定义http请求回调监听器
* */
public interface HttpCallbackListener {
    //表示当服务器成功响应我们请求的时候调用
        //参数代表服务器返回的数据
    void onFinish(String response);
    //当进行网络操作出现错误的时候调用
        //参数记录着错误的详细信息
    void onError(Exception e);
}
