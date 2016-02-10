package is.robertreynisson.icequake.domain_layer;

import is.robertreynisson.icequake.utils.Utils;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by robert on 9.2.2016.
 */
abstract public class AbstractViewModel {
    private static final String TAG = AbstractViewModel.class.getSimpleName();
    private CompositeSubscription compositeSubscription;


    final public void subscribeToDataStore() {
        Utils.logger(TAG, "subscribeToDataStore");
        unsubscribeFromDataStore();
        compositeSubscription = new CompositeSubscription();
        subscribeToDataStoreInternal(compositeSubscription);
    }

    public void unsubscribeFromDataStore() {
        Utils.logger(TAG, "unsubscribe FromDataStore");
        if (compositeSubscription != null) {
            compositeSubscription.clear();
            compositeSubscription = null;
        }
    }

    public void dispose() {
        if (compositeSubscription != null) {
            // Unsubscribe in case we are still for some reason subscribed
            unsubscribeFromDataStore();
        }
    }

    protected abstract void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription);


}
