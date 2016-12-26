package app.sean.baseandroid.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sean on 16/12/16.
 */

public class RxPresent<T extends BaseView> implements BasePresent<T> {

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;


    /**
     * 添加监听者
     *
     * @param subscription
     */
    protected void addSubscribe( Subscription subscription ) {
        if( mCompositeSubscription == null ) {
            mCompositeSubscription = new CompositeSubscription( );
        }
        mCompositeSubscription.add( subscription );
    }


    /**
     * 取消监听者
     */
    protected void unSubscribe() {
        if( mCompositeSubscription != null ) {
            mCompositeSubscription.unsubscribe( );
        }
    }


    @Override
    public void attachView( T view ) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe( );
    }
}
