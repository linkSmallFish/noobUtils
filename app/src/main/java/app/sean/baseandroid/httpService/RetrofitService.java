package app.sean.baseandroid.httpService;

import android.content.Context;

import app.sean.baseandroid.httpService.client.RetrofitHelper;
import app.sean.baseandroid.httpService.service.HttpService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 创建服务
 * Created by sean on 16/12/12.
 */

public class RetrofitService {

    private static Retrofit retrofit;

    public static void getInstance( Context context ) {
        if( retrofit == null ) {
            retrofit = RetrofitHelper.getInstance( context ).build( );
        }
    }

    /**
     * retrofit 配置
     *
     * @return Retrofit.Builder
     */
    public static Retrofit.Builder getRetrofitBuilder() {
        return RetrofitHelper.getRetrofitBuilder( );
    }

    /**
     * 网络请求配置类
     *
     * @return OkHttpClient.Builder
     */
    public static OkHttpClient.Builder getClientBuild() {
        return RetrofitHelper.getClientBuilder( );
    }


    /**
     * 创建服务
     *
     * @param service
     * @return
     */
    public Object createService( HttpService service ) {
        if( retrofit != null ) {
            return retrofit.create( service.getClass( ) );
        }
        return null;
    }
}
