package is.robertreynisson.icequake.presenter_layer.map;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import is.robertreynisson.icequake.domain_layer.MapViewModel;
import is.robertreynisson.icequake.utils.RxUIBinderUtil;

/**
 * Created by robert on 22.2.2016.
 */
public class Mapview extends FrameLayout {

    private RxUIBinderUtil rxUIBinderUtil = new RxUIBinderUtil(this);

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
        rxUIBinderUtil.clear();
        if (viewModel != null)
            rxUIBinderUtil.bindProperty(viewModel.quakeData(), this::updateUI);

    }

    //This will be the method called by the observable
    //and is passed into the RxUIBinderUtil via this::updateUI
    //syntax (using Java 1.8 and RetroLambda Method Reference syntax)
    private void updateUI(String string) {


    }
}
