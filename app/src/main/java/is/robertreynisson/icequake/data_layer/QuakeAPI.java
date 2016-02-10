package is.robertreynisson.icequake.data_layer;

import is.robertreynisson.icequake.data_layer.models.QuakeResponse;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by robert on 9.2.2016.
 */
public interface QuakeAPI {

    @GET("/earthquake/is")
    Observable<QuakeResponse> getQuakes();

}
