package com.example.internet.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
* 单例模式创建ApiService构造器
* */
public class RetrofitServiceCreator {

    private static final String BASE_URL = "http:172.20.10.3/";

    private static Retrofit retrofit;

    private RetrofitServiceCreator(){
        // 私有构造函数，防止外部实例化
    }

    public static <T> T createApiService(Class<T> serviceClass){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(serviceClass);
    }

/*------------------使用----------------*/
    /*ApiService apiService = RetrofitClient.createApiService(ApiService.class);
    Call<ResponseBody> call = apiService.getData();
     执行网络请求...*/

}
