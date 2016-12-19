package internetmeasurement.android.fragment.second;

import android.graphics.Color;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import internetmeasurement.android.main.MainActivity;
import internetmeasurement.android.R;

/**
 * Created by jonas on 10.09.16.
 */
public class AdvancedLineGraph extends BaseExample {
    private Series mSeries;


    public AdvancedLineGraph(Series series) {
        this.mSeries = series;
    }

    @Override
    public void onCreate(MainActivity activity) {
        GraphView graph = (GraphView) activity.findViewById(R.id.graph);
        initGraph(graph);
    }


    @Override
    public void initGraph(GraphView graph) {

        try {
            // first series
        /*LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 2)
        });*/

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
            series2.setDrawBackground(true);
            series2.setColor(Color.argb(255, 255, 60, 60));
            series2.setBackgroundColor(Color.argb(100, 204, 119, 119));
            series2.setDrawDataPoints(true);
            graph.addSeries(series2);

            // legend
            graph.getLegendRenderer().setVisible(true);
            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
