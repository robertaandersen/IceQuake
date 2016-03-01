package is.robertreynisson.icequake.presenter_layer.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by robert on 9.2.2016.
 */
public class Quake implements Comparable<Quake>{

    public String location;
    public double Size;
    public Date time;
    public Double depth;
    public Double lat;
    public Double longi;
    public LatLng latLng;

    @Override
    public int compareTo(Quake another) {
        return time.getTime() < another.time.getTime() ?  1 : 0;
    }
}
