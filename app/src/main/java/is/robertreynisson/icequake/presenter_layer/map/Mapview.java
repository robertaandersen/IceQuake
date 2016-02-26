package is.robertreynisson.icequake.presenter_layer.map;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import is.robertreynisson.icequake.R;
import is.robertreynisson.icequake.domain_layer.MapViewModel;
import is.robertreynisson.icequake.utils.RxUIBinderUtil;

/**
 * Created by robert on 22.2.2016.
 */
public class Mapview extends FrameLayout implements OnMapReadyCallback {

    private RxUIBinderUtil rxUIBinderUtil = new RxUIBinderUtil(this);
    private GoogleMap googleMap;

    public Mapview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }


    //This takes care of subscribing UI to the
    //viewModels observable objects via
    //the BinderUtil.
    public void setViewModel(MapViewModel viewModel) {
        this.googleMap = getMapInstance();
        test();
        rxUIBinderUtil.clear();
        if (viewModel != null)
            rxUIBinderUtil.bindProperty(viewModel.quakeData(), this::updateUI);

    }

    private GoogleMap getMapInstance() {
        if(this.googleMap != null) return this.googleMap;
        if(MapFragment.fragmentManager != null){
            SupportMapFragment supportMapFragment = (SupportMapFragment) MapFragment.fragmentManager.findFragmentById(R.id.location_map);
            if(supportMapFragment != null ) supportMapFragment.getMapAsync(getMap());
        }
        return null;
    }

    private OnMapReadyCallback getMap() {
        return new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap _googleMap) {
                googleMap = _googleMap;
            }
        };
    }

    private void test(){
        LatLng latLng = new LatLng(66,0);
        MarkerOptions options = new MarkerOptions().position(latLng);
        options.title("Halló");

        options.icon(BitmapDescriptorFactory.defaultMarker());
//        this.googleMap.addMarker(options);
//        this.googleMap.getUiSettings().setAllGesturesEnabled(true);
    }

    //This will be the method called by the observable
    //and is passed into the RxUIBinderUtil via this::updateUI
    //syntax (using Java 1.8 and RetroLambda Method Reference syntax)
    private void updateUI(String string) {


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        String xxx = "xxx";
    }
}
