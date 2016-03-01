package is.robertreynisson.icequake.presenter_layer.map;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.ButterKnife;
import is.robertreynisson.icequake.R;
import is.robertreynisson.icequake.domain_layer.MapViewModel;
import is.robertreynisson.icequake.presenter_layer.models.Quake;
import is.robertreynisson.icequake.utils.RxUIBinderUtil;
import is.robertreynisson.icequake.utils.Utils;

/**
 * Created by robert on 22.2.2016.
 */
public class Mapview extends FrameLayout implements OnMapReadyCallback {

    private static final String TAG = Mapview.class.getSimpleName();
    private RxUIBinderUtil rxUIBinderUtil = new RxUIBinderUtil(this);
    private GoogleMap googleMap;
    private MapView mapView;

    public Mapview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(null);
        mapView.getMapAsync(this);
    }


    //This takes care of subscribing UI to the
    //viewModels observable objects via
    //the BinderUtil.
    public void setViewModel(MapViewModel viewModel) {
        rxUIBinderUtil.clear();
        if (viewModel != null)
            rxUIBinderUtil.bindProperty(viewModel.quakeData(), this::updateUI);

    }

    //This will be the method called by the observable
    //and is passed into the RxUIBinderUtil via this::updateUI
    //syntax (using Java 1.8 and RetroLambda Method Reference syntax)
    private void updateUI(List<Quake> quake) {
        for (int i = 0; i < quake.size(); i++)
            this.googleMap.addMarker(new MarkerOptions().position(quake.get(i).latLng).title(
                    Utils.PrettyDateFormatter(quake.get(i).time.getTime()) + " : " +
                    String.valueOf(quake.get(i).Size)
            ));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.googleMap = map;
        //this.googleMap.
    }

    public void onResume() {mapView.onResume();}

    public void onPause() {
        mapView.onPause();
    }

    public void onDestroy() {
        mapView.onDestroy();
    }

    public void onLowMemory() {
        mapView.onLowMemory();
    }
}
