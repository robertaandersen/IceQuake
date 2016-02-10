package is.robertreynisson.icequake.utils;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import is.robertreynisson.icequake.IceQuake;
import is.robertreynisson.icequake.presenter_layer.models.Quake;
import rx.Observable;

/**
 * Created by robert on 9.2.2016.
 */
public class ChartFormatter {
    public static void setQuakeChart(HorizontalBarChart quakeChart, List<Quake> quakeResponse) {
        quakeChart.setData(getQuakeChartData(quakeResponse));
        quakeChart.getLegend().setEnabled(false);
        quakeChart.setDescription("");

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        quakeChart.setMarkerView(new ChartMarkerView(IceQuake.getInstance()));

        XAxis xAxis = quakeChart.getXAxis();
        xAxis.resetLabelsToSkip();
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);


        YAxis yAxis = quakeChart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(false);
        yAxis.setGridLineWidth(0.3f);
        yAxis.setLabelCount(4, true);
        yAxis.resetAxisMinValue();
        yAxis.resetAxisMaxValue();
        yAxis.setShowOnlyMinMax(true);
    }

    private static BarData getQuakeChartData(List<Quake> quakeResponse) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, -24);
        List<Quake> filtererList = new ArrayList<>();
        Observable.from(quakeResponse)
                .filter(quake1 -> quake1.Size > 0)
                .filter(quake1 -> quake1.time.getTime() > c.getTime().getTime())
                .subscribe(onNext -> filtererList.add(onNext));
        Collections.sort(filtererList);
        Collections.reverse(filtererList);

        List<String> quakeTime = new ArrayList<>();
        List<BarEntry> entries = new ArrayList<>();
        Observable.from(filtererList)
                .map(quake -> {
                    c.setTime(quake.time);
                    BarEntry e = new BarEntry((float)quake.Size, entries.size());
                    e.setData(quake);
                    entries.add(e);
                    quakeTime.add(Utils.getClock(new LocalDateTime(quake.time)) + " - (" + quake.location +")" );
                    return e;
                })
                .subscribe();

        BarDataSet magnitudes = new BarDataSet(entries, "Quakes");
        magnitudes.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(magnitudes);
        return new BarData(quakeTime, dataSets);
    }
}
