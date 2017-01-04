package internetmeasurement.android.fragment.second;

import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import internetmeasurement.android.R;

/**
 * Created by jonas on 10.09.16.
 */
public class AdvancedLineGraph {
    private Series mSeries;
    private View mView;

    public AdvancedLineGraph(View secondView, Series series) {
        this.mSeries = series;
        this.mView = secondView;

    }

    public void initGraph() {
        GraphView graph = (GraphView) mView.findViewById(R.id.graph);

        try {
            graph.addSeries(mSeries);

            // second series
            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[]{
                    new DataPoint(0, 3),
                    new DataPoint(1, 3),
                    new DataPoint(2, 6),
                    new DataPoint(3, 2),
                    new DataPoint(4, 5)
            });
            series2.setTitle("speed");
            //series2.setDrawBackground(true);
            //series2.setColor(Color.argb(255, 255, 60, 60));
            //series2.setBackgroundColor(Color.argb(100, 204, 119, 119));
            //series2.setDrawDataPoints(true);
            graph.addSeries(series2);

            // Caption
            graph.getLegendRenderer().setVisible(true);
            //graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
