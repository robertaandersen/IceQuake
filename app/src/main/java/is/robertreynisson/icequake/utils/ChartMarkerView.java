package is.robertreynisson.icequake.utils;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import is.robertreynisson.icequake.IceQuake;
import is.robertreynisson.icequake.R;
import is.robertreynisson.icequake.presenter_layer.models.Quake;

/**
 * Created by robert on 9.2.2016.
 */
public class ChartMarkerView extends MarkerView {

    TextView timeOfQuake;
    TextView location;
    TextView depth;
    ChartMarkerView markerView;

    public ChartMarkerView(Context context) {
        super(context, R.layout.custom_marker_view);
        markerView = this;
        timeOfQuake = (TextView) findViewById(R.id.marker_view_time);
        location = (TextView) findViewById(R.id.marker_view_location);
        depth = (TextView) findViewById(R.id.marker_view_depth);

    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Quake q = (Quake)e.getData();
        timeOfQuake.setText(IceQuake.getInstance().getResources().getString(R.string.marker_view_title) + Utils.PrettyDateFormatter(q.time.getTime()));
        location.setText(IceQuake.getInstance().getResources().getString(R.string.marker_view_location) + q.location);
        depth.setText(IceQuake.getInstance().getResources().getString(R.string.marker_view_depth) + q.depth);
    }

    @Override
    public int getXOffset(float xpos) {
        return -(int)xpos + 30;
//        return -(getWidth());
    }

    @Override
    public int getYOffset(float ypos) {
        return -getHeight();
    }
}
