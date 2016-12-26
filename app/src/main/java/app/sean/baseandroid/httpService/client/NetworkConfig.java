package app.sean.baseandroid.httpService.client;

import app.sean.baseandroid.httpService.constant.HttpConstant;

/**
 * Created by sean on 16/12/12.
 */

public class NetworkConfig {

    //网络链接超时时间
    private int connectTime;

    private int readTime;

    private int writeTime;
    //是否断线重连
    private boolean isRetry;

    public int getConnectTime() {
        return connectTime > 0 ? connectTime : HttpConstant.DEFAULT_CONNECT_TIME;
    }

    public void setConnectTime( int connectTime ) {
        this.connectTime = connectTime;
    }

    public boolean isRetry() {
        return isRetry;
    }

    public void setRetry( boolean retry ) {
        isRetry = retry;
    }

    public int getReadTime() {
        return readTime > 0 ? readTime : HttpConstant.DEFAULT_READ_TIME;
    }

    public void setReadTime( int readTime ) {
        this.readTime = readTime;
    }

    public int getWriteTime() {
        return writeTime > 0 ? writeTime : HttpConstant.DEFAULT_WRITE_TIME;
    }

    public void setWriteTime( int writeTime ) {
        this.writeTime = writeTime;
    }
}
