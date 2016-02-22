package is.robertreynisson.icequake.domain_layer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import is.robertreynisson.icequake.presenter_layer.barchart.MainActivity;
import is.robertreynisson.icequake.presenter_layer.models.Quake;
import is.robertreynisson.icequake.utils.Utils;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by robert on 9.2.2016.
 */
public class BarChartViewModel extends AbstractViewModel {
    private static final String TAG = BarChartViewModel.class.getSimpleName();

    public final BehaviorSubject<String> time = BehaviorSubject.create();
    public final BehaviorSubject<List<Quake>> quakes = BehaviorSubject.create();

    @Override
    protected void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription) {
        Utils.logger(TAG, "Subscribed");
        compositeSubscription.add(Observable.interval(1, TimeUnit.SECONDS).map(s -> Utils.PrettyDateFormatter(new Date().getTime())).subscribe(time));
        compositeSubscription.add(
                MainActivity.serviceAdapter.getQuakes()
                        .map(ModelConverters::quakeModelFromAPI)
                        .subscribe(quakes));
    }

    public BehaviorSubject<String> getTime() { return time; }

    public Observable<List<Quake>> getQuakeStuff() {
        return quakes;
    }
}
