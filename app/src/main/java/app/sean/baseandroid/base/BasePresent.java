package app.sean.baseandroid.base;

/**
 * present基类
 * Created by sean on 16/12/8.
 */

public interface BasePresent<T> {

    void attachView( T view );

    void detachView();
}
