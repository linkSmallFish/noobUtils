package app.sean.baseandroid.httpService.constant;

import android.os.Environment;

/**
 * Created by sean on 16/12/12.
 */

public class HttpConstant {
    //缓存地址
    public static final String FILE_DIR = Environment.getExternalStorageDirectory( ) +
                                          "/beepMoney/Cache";
    //缓存大小
    public static final long FILE_CACHE_SIZE = 1024 * 1024 * 10;
    //默认链接时间
    public static final int DEFAULT_CONNECT_TIME = 10;
    //默认读取时间
    public static final int DEFAULT_READ_TIME = 20;
    //默认写入时间
    public static final int DEFAULT_WRITE_TIME = 20;

}
