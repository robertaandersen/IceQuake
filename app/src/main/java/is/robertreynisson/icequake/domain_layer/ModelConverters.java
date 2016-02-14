package is.robertreynisson.icequake.domain_layer;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import is.robertreynisson.icequake.data_layer.models.QuakeResponse;
import is.robertreynisson.icequake.presenter_layer.models.Quake;
import rx.Observable;

/**
 * Created by robert on 9.2.2016.
 */
public class ModelConverters {
    public static List<Quake> quakeModelFromAPI(QuakeResponse quakeResponse) {
        List<Quake> res = new ArrayList<>();
        Observable.from(quakeResponse.getResults())
                .map(result -> {
                    Quake q = new Quake();
                    q.location = result.getHumanReadableLocation();
                    q.Size = result.getSize();
                    q.time = timeStampToDate(result.getTimestamp());
                    q.depth = result.getDepth();
                    return q;
                })
                .subscribe(doOnNext -> res.add(doOnNext));
        return res;
    }

    public static Date timeStampToDate(String iso8601string) {
        return  new DateTime(iso8601string).toDate();
    }
}