package is.robertreynisson.icequake.data_layer;

import is.robertreynisson.icequake.data_layer.models.QuakeResponse;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by robert on 9.2.2016.
 */
public class ServiceAdapter {
    private static final String TAG = ServiceAdapter.class.getSimpleName();
    private final QuakeAPI quakeAPI;

    public ServiceAdapter(String endpoint) {
        RestAdapter.Builder restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
//                .setClient(new RetailCrestOkClient())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(request -> request.addHeader("Accept", "application/json"));
        RestAdapter adapter = restAdapter.build();
        quakeAPI = adapter.create(QuakeAPI.class);
    }

    public Observable<QuakeResponse> getQuakes(){ return quakeAPI.getQuakes();}
}
