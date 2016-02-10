package is.robertreynisson.icequake.utils;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by robert on 8.9.2015.
 * The BinderUtility is a parametrized class roughly equivalent
 * to something like this:

 * CompositeSubscription compositeSubscription = new CompositeSubscription();
 * compositeSubscription.add(
 * viewModel.getCompSaleBehaviorSubject()
 * .observeOn(AndroidSchedulers.mainThread())
 * .subscribe(new Subscriber<String>() {
 *
 * @Override public void onCompleted() {
 * <p>
 * }
 * @Override public void onError(Throwable e) {
 * @Override public void onNext(String s) {
 * setServerVersion(s);
 * }
 * })
 * );
 */
public class RxUIBinderUtil {
    final private String tag;

    //Subscription that represents a group of Subscriptions that are un-subscribed together.
    final private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public RxUIBinderUtil(Object target) {
        this.tag = target.getClass().getCanonicalName();
    }


    public void clear() {
        compositeSubscription.clear();
    }

    public <U> void bindProperty(final Observable<U> observable, final Action1<U> setter) {
        if (observable == null) return;
        compositeSubscription.add(subscribeSetter(observable, setter, tag));
    }

    static private <U> Subscription subscribeSetter(final Observable<U> observable, final Action1<U> setter, final String tag) {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SetterSubscriber<>(setter, tag));
    }


    static private class SetterSubscriber<U> extends Subscriber<U> {
        final static private String TAG = SetterSubscriber.class.getCanonicalName();

        final private Action1<U> setter;
        final private String tag;

        public SetterSubscriber(final Action1<U> setter, final String tag) {
            this.setter = setter;
            this.tag = tag;
        }

        @Override
        public void onCompleted() {
            Log.v(TAG, tag + "." + "onCompleted");

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, tag + "." + "onError", e);
        }

        @Override
        public void onNext(U u) {

            setter.call(u);

        }
    }
}
