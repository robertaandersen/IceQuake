package is.robertreynisson.icequake.domain_layer;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by robert on 22.2.2016.
 */
public class MapViewModel extends AbstractViewModel {

    private static final String TAG = MapViewModel.class.getSimpleName();
    BehaviorSubject<String> quakeData = BehaviorSubject.create();

    @Override
    protected void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription) {

    }

    public Observable<String> quakeData() {
        return quakeData;
    }
}
