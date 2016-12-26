package app.sean.baseandroid.httpService.client;

import android.text.TextUtils;

import java.io.File;

import app.sean.baseandroid.httpService.constant.HttpConstant;
import okhttp3.Cache;

/**
 * 缓存配置
 * Created by sean on 16/12/12.
 */
public class CacheConfig {

    private boolean isCache;

    private String cacheFilePath;

    private long cacheFileSize;

    public String getCacheFilePath() {
        return TextUtils.isEmpty( cacheFilePath ) ? cacheFilePath : HttpConstant.FILE_DIR;
    }

    public void setCacheFilePath( String cacheFilePath ) {
        this.cacheFilePath = cacheFilePath;
    }


    public void setCacheFileSize( long cacheFileSize ) {
        this.cacheFileSize = cacheFileSize;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache( boolean cache ) {
        isCache = cache;
    }

    public long getCacheFileSize() {
        return cacheFileSize > 0 ? cacheFileSize : HttpConstant.FILE_CACHE_SIZE;
    }

    /**
     * 生成缓存文件
     *
     * @return
     */
    public Cache generateCacheFile() {
        if( isCache && TextUtils.isEmpty( getCacheFilePath( ) ) && getCacheFileSize( ) > 0 ) {
            File file = new File( getCacheFilePath( ) );
            if( file.exists( ) ) {
                return new Cache( file, getCacheFileSize( ) );
            }
        }
        return null;
    }
}