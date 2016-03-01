package is.robertreynisson.icequake.domain_layer;

import java.util.List;

import is.robertreynisson.icequake.presenter_layer.MainActivity;
import is.robertreynisson.icequake.presenter_layer.models.Quake;
import rx.Observable;
import rx.Observer;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by robert on 22.2.2016.
 */
public class MapViewModel extends AbstractViewModel {

    private static final String TAG = MapViewModel.class.getSimpleName();
    BehaviorSubject<List<Quake>> quakeData = BehaviorSubject.create();

    @Override
    protected void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription) {
        compositeSubscription.add(
                MainActivity.serviceAdapter.getQuakes()
                        .map(ModelConverters::quakeModelFromAPI)
                        .subscribe(quakeData));

    }

    public Observable<List<Quake>> quakeData() {
        return quakeData;
    }
}
