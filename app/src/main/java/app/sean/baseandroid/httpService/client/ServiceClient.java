package app.sean.baseandroid.httpService.client;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.sean.baseandroid.BuildConfig;
import app.sean.baseandroid.helper.NetworkHelper;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttpClient builder
 * Created by sean on 16/12/12.
 */
public class ServiceClient {

    private static OkHttpClient.Builder clientBuilder;

    public static OkHttpClient.Builder getInstance( Context context ) {
        if( clientBuilder == null ) {
            clientBuilder = new OkHttpClient( ).newBuilder( );
            LogConfig( BuildConfig.DEBUG );
            CacheConfig( new CacheConfig( ), context );
            NetWorkConfig( new NetworkConfig( ) );
        }
        return clientBuilder;
    }

    /**
     * log信息配置
     *
     * @param isDebug
     */
    public static void LogConfig( boolean isDebug ) {
        if( !isDebug && clientBuilder == null ) {
            return;
        }
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor( );
        loggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BASIC );
        clientBuilder.addInterceptor( loggingInterceptor );
    }

    /**
     * 缓存配置
     *
     * @param cacheConfig
     */
    public static void CacheConfig( CacheConfig cacheConfig, final Context context ) {
        if( cacheConfig == null || clientBuilder == null ) {
            return;
        }
        Cache cache = cacheConfig.generateCacheFile( );

        Interceptor cacheInterceptor = new Interceptor( ) {
            @Override
            public Response intercept( Chain chain ) throws IOException {
                Request request = chain.request( );
                if( !NetworkHelper.isNetworkConnected( context ) ) {
                    request = request.newBuilder( ).cacheControl( CacheControl.FORCE_CACHE )
                                     .build( );
                }
                Response response = chain.proceed( request );
                if( NetworkHelper.isNetworkConnected( context ) ) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder( ).header( "Cache-Control", "public, max-age=" + maxAge )
                            .removeHeader( "Pragma" ).build( );
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder( ).header( "Cache-Control",
                            "public, only-if-cached, max-stale=" + maxStale ).removeHeader(
                            "Pragma" ).build( );
                }
                return response;
            }
        };

        clientBuilder.addNetworkInterceptor( cacheInterceptor );
        clientBuilder.addInterceptor( cacheInterceptor );
        clientBuilder.cache( cache );
    }


    /**
     * 网络配置
     *
     * @param config
     */
    public static void NetWorkConfig( NetworkConfig config ) {
        if( config == null || clientBuilder == null ) {
            return;
        }
        clientBuilder.connectTimeout( config.getConnectTime( ), TimeUnit.SECONDS );
        clientBuilder.readTimeout( config.getReadTime( ), TimeUnit.SECONDS );
        clientBuilder.writeTimeout( config.getWriteTime( ), TimeUnit.SECONDS );
    }

    public static OkHttpClient.Builder getClientBuilder() {
        return clientBuilder;
    }
}
