package com.example.internet.interfaces;

import com.example.internet.entity.App;
import com.example.internet.entity.Data;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*根据服务器接口的功能进行归类，创建不同种类的接口文件，并在其中定义对应具体服务器接口的方法*/
//由于Apache 服务器上其实只有一个获取JSON数据的接口，因此这里只需要定义一个接口文件，并包含一个方法即可
public interface AppService {
    //当调用getAppData()方法时Retrofit 会发起一条GET请求，请求的地址就是我们在@GET注解中传入的具体参数
    @GET("get_data.json")
    //返回值必须声明成Retrofit 中内置的Call类型，并通过泛型来指定服务器响应的数据应该转换成什么对象
    Call<List<App>> getAppData();

    /*Retrofit 还提供了强大的Call Adapters 功能来允许我们自定义方法返回值的类型，比如Retrofit 结合RxJava 使用就可以将返回值声明成
Observable、Flowable等类型，不过这些内容就不在此的讨论范围内了*/

/*处理复杂的接口地址类型*/
    //一、GET http://example.com/<page>/get_data.json（在这个接口当中，<page>部分代表页数，我们传入不同的页数，服务器返回的数据也会不同）
    @GET("{page}/get_data.json")
    Call<Data> getData(@Path("page") int page);

    //二、GET http://example.com/get_data.json?u=<user>&t=<token>
        /*这是一种标准的带参数GET请求的格式。接口地址的最后使用问号来连接参数部分，每个参数都是一个使用等号连接的键值对，多个参数之间使用“&”符号进行分隔。那么很显然，在上述地址中，服务器要求我们传入user和token这两个参数的值。
        对于这种格式的服务器接口，我们可以使用刚才所学的@Path注解的方式来解决，但是这样会有些麻烦，Retr ofit 针对这种带参数的GET请求，专门提供了一种语法支持：*/
    @GET("get_data.json")
    Call<Data> getData(@Query("u") String user,@Query("t") String token);

    //三、DELETE http://example.com/data/<id>
        /*由于POST、PUT 、PATCH、DELETE这几种请求类型与GET请求不同，它们更多是用于操作服务器上的数据，而不是获取服务器上的数据，所以通常它们对于服务器响应的数据并不关心。
        这个时候就可以使用ResponseBody，表示Retrofit 能够接收任意类型的响应数据，并且不会对响应数据进行解析。*/
    @DELETE("data/{id}")
    Call<ResponseBody> deleteData(@Path("id") String id);

    //四、POST http://example.com/data/create
        //{"id": 1, "content": "The description for this data."}
        /*这样当Retr ofit 发出POST请求时，就会自动将Data对象中的数据转换成JSON格式的文本，并放到HTTP请求的body部分，服务器在收到请求之后只需要从body中将这部分数
        据解析出来即可。这种写法同样也可以用来给PUT、PATCH、DELETE类型的请求提交数据*/
    @POST("data/create")
    Call<ResponseBody> createData(@Body Data data);

    //五、GET http://example.com/get_data.json
        //User-Agent: okhttp
        //Cache-Control: max-age=0
    /*静态header声明*/
    @Headers({"User-Agent: okhttp", "Cache-Control: max-age=0"})
    @GET("get_data.json")
    Call<Data> getData();
    /*动态指定header的值*/
        /*当发起网络请求的时候，Retr ofit 就会自动将参数中传入的值设置到User-Agent 和Cache-Control这两个header当中，从而实现了动态指定header值的功能。*/
    @GET("get_data.json")
    Call<Data> getData2(@Header("User-Agent") String userAgent,@Header("Cache-Control")String cacheControl);
}
