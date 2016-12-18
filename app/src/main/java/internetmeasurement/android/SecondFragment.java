package internetmeasurement.android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class SecondFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*Layout Customization
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 200);
        params.addRule(RelativeLayout.ALIGN_TOP, RelativeLayout.TRUE);

        //In the Graph
        graph.setLayoutParams(params);
        */


        View graphView = inflater.inflate(R.layout.fragment_second, container, false);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 2)
        });
        series.setTitle("first line");
        GraphView graph = (GraphView) graphView.findViewById(R.id.graph);
        GraphViewStyle(graph);

        new AdvancedLineGraph(series).initGraph(graph);
        return graphView;
    }

    private void GraphViewStyle(GraphView graph){
        //Activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);
        //Activate horizontal scrolling
        graph.getViewport().setScrollable(true);
        //Activate horizontal and vertical zooming and scrolling
        graph.getViewport().setScalableY(true);
        //Activate vertical scrolling
        graph.getViewport().setScrollableY(true);
        //Set graph title
        graph.setTitle("Bandwidth Estimation");
        graph.setTitleColor(Color.WHITE);
        graph.setTitleTextSize(70);
        //set Y Axis Title
        graph.getGridLabelRenderer().setVerticalAxisTitle("Bandwidth");
        //set X Axis Title
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Time/s");
        //set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.5);
        graph.getViewport().setMaxX(3.5);
        //set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.computeScroll();
        graph.getViewport().setMinY(3.5);
        graph.getViewport().setMaxY(8);
        int margin = graph.getGraphContentTop();
        Log.d("MARGIN TOP", String.valueOf(margin));

    }
}
