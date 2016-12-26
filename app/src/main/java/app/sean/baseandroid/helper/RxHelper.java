package app.sean.baseandroid.helper;

import app.sean.baseandroid.httpService.service.HttpResponse;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sean on 16/12/16.
 */

public class RxHelper {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>( ) {
            @Override
            public Observable<T> call( Observable<T> observable ) {
                return observable.subscribeOn( Schedulers.io( ) ).observeOn(
                        AndroidSchedulers.mainThread( ) );
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<HttpResponse<T>, T> handleResult() {   //compose判断结果
        return new Observable.Transformer<HttpResponse<T>, T>( ) {
            @Override
            public Observable<T> call( Observable<HttpResponse<T>> httpResponseObservable ) {
                return httpResponseObservable.flatMap(
                        new Func1<HttpResponse<T>, Observable<T>>( ) {
                            @Override
                            public Observable<T> call( HttpResponse<T> response ) {
                                return createData( response.getValue( ) );
                            }
                        } );
            }
        };
    }


    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData( final T value ) {
        return Observable.create( new Observable.OnSubscribe<T>( ) {
            @Override
            public void call( Subscriber<? super T> subscriber ) {
                try {
                    subscriber.onNext( value );
                    subscriber.onCompleted( );
                }
                catch( Exception e ) {
                    subscriber.onError( e );
                }
            }
        } );
    }

}
