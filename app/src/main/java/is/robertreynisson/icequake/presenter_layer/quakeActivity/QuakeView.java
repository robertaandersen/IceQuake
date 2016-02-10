package is.robertreynisson.icequake.presenter_layer.quakeActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import is.robertreynisson.icequake.R;
import is.robertreynisson.icequake.domain_layer.QuakeViewModel;
import is.robertreynisson.icequake.presenter_layer.MainActivity;
import is.robertreynisson.icequake.presenter_layer.models.Quake;
import is.robertreynisson.icequake.utils.ChartFormatter;
import is.robertreynisson.icequake.utils.RxUIBinderUtil;
import is.robertreynisson.icequake.utils.Utils;

/**
 * Created by robert on 9.2.2016.
 */
public class QuakeView extends FrameLayout {

    private static final String TAG = QuakeView.class.getSimpleName();

    //A util that contains this views subscriptions
    //and maintains them throughout the lifecycle.
    private RxUIBinderUtil rxUIBinderUtil = new RxUIBinderUtil(this);
    private QuakeViewModel viewModel;

    public QuakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //Butterknife bindings
    @Bind(R.id.quake_view_text)
    TextView quakeViewText;

    @Bind(R.id.chart)
    HorizontalBarChart quakeChart;

    @Bind(R.id.quake_view_progressbar)
    ProgressBar progressBar;

    @OnClick(R.id.quake_view_reload_button)
    public void reloadClick() {
        if(this.viewModel != null) {
            this.viewModel.unsubscribeFromDataStore();
            this.viewModel = null;
            this.viewModel = new QuakeViewModel();
            this.viewModel.subscribeToDataStore();
        }
        loadData();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        quakeViewText.setText(R.string.quake_view_header);

    }

    //This takes care of subscribing UI to the
    //viewModels observable objects via
    //the BinderUtil.
    public void setViewModel(QuakeViewModel viewModel) {
        this.viewModel = viewModel;
        if (viewModel != null)
            loadData();
    }

    private void loadData() {
        rxUIBinderUtil.clear();
        rxUIBinderUtil.bindProperty(this.viewModel.getTime(), this::updateUI);
        rxUIBinderUtil.bindProperty(this.viewModel.getQuakeStuff(), this::quakeResponse);
        quakeChart.invalidate();
    }

    private void quakeResponse(List<Quake> quakeResponse) {
        quakeChart.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        ChartFormatter.setQuakeChart(quakeChart, quakeResponse);
        quakeChart.invalidate();

    }

    //This will be the method called by the observable
    //and is passed into the RxUIBinderUtil via this::updateUI
    //syntax (using Java 1.8 and RetroLambda Method Reference syntax)
    private void updateUI(String string) {
        MainActivity.updateTime.setText(string);
    }
}
