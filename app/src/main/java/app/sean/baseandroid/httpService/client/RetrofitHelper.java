package app.sean.baseandroid.httpService.client;

import android.content.Context;
import android.text.TextUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sean on 16/12/9.
 */
public class RetrofitHelper {

    private static Retrofit.Builder builder = null;

    public static Retrofit.Builder getInstance( Context context ) {
        if( builder == null ) {
            builder = new Retrofit.Builder( );
            OkHttpClient.Builder clientBuilder = ServiceClient.getInstance( context );
            builder.client( clientBuilder.build( ) ).addConverterFactory(
                    GsonConverterFactory.create( ) ).addCallAdapterFactory(
                    RxJavaCallAdapterFactory.create( ) );
        }

        return builder;
    }

    /**
     * 配置基地址
     *
     * @param baseUrl
     */
    public void UrlConfig( String baseUrl ) {
        if( !TextUtils.isEmpty( baseUrl ) && builder != null ) {
            builder.baseUrl( baseUrl );
        }
    }


    /**
     * @param
     */
    public void clientConfig( OkHttpClient client ) {
        if( builder != null ) {
            builder.client( client );
        }
    }

    public static Retrofit.Builder getRetrofitBuilder() {
        return builder;
    }

    /**
     * 网络构造器
     *
     * @return
     */
    public static OkHttpClient.Builder getClientBuilder() {
        return ServiceClient.getClientBuilder( );
    }
}
