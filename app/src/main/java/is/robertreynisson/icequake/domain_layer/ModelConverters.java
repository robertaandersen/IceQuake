package is.robertreynisson.icequake.domain_layer;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                    q.lat = result.getLatitude();
                    q.longi = result.getLongitude();

                    return q;
                })
                .subscribe(doOnNext -> res.add(doOnNext));
        return res;
    }

    public static Date timeStampToDate(String iso8601string) {

        DateTime dt = new DateTime(iso8601string, DateTimeZone.forID("Iceland"));

        String year = String.valueOf(dt.get(DateTimeFieldType.year()));
        String month = String.valueOf(dt.get(DateTimeFieldType.monthOfYear()));
        String day = String.valueOf(dt.get(DateTimeFieldType.dayOfMonth()));
        String hour = String.valueOf(dt.get(DateTimeFieldType.hourOfDay()));
        String min = String.valueOf(dt.get(DateTimeFieldType.minuteOfHour()));
        String sec = String.valueOf(dt.get(DateTimeFieldType.secondOfMinute()));

        String dateString = year + "-";
        dateString += month + "-";
        dateString += day + " ";
        dateString += hour + ":";
        dateString += min + ":";
        dateString += sec;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new DateTime(iso8601string).toDate();
    }
}