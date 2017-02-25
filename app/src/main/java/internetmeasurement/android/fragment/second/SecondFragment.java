package internetmeasurement.android.fragment.second;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import internetmeasurement.android.R;


public class SecondFragment extends Fragment {
    public static LineGraphSeries<DataPoint> series=null;
    public static String GraphYLabel="";
    private List<Data> List = new ArrayList<>();
    public static TextView tvGraphYLabel=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate View
        View secondView = inflater.inflate(R.layout.fragment_second, container, false);

        //Data Points Series 1
        //LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        series = new LineGraphSeries<>();

        //GraphStyle

        tvGraphYLabel = (TextView) secondView.findViewById(R.id.graph_Y_label);
        GraphView graph = (GraphView) secondView.findViewById(R.id.graph);
        //Fix Y-Axis Numbers from being cut off
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setPadding(32);
        //Add Series
        graph.addSeries(series);
        series.setDrawDataPoints(true);
        //GraphView Style
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        //GraphViewStyle(graph);


        /*graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxX(series.getHighestValueX());
        graph.getViewport().setMinX(series.getLowestValueX());
        graph.getViewport().setMaxY(series.getHighestValueY());
        graph.getViewport().setMinY(series.getLowestValueY());*/
        //graph.getViewport().setScalable(true);
        //graph.getViewport().setScrollable(true);

        //Initializing Plot
        //AdvancedLineGraph advancedLineGraph = new AdvancedLineGraph(secondView, series);
        //advancedLineGraph.initGraph();

        //Get Date
        //TextView textViewDate = (TextView) secondView.findViewById(R.id.date_info);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, \n yyyy \n h:mm a");
        String dateString = sdf.format(date);
        //textViewDate.setText(dateString);

        RecyclerView rv = (RecyclerView) secondView.findViewById(R.id.recycler_view);
        //rv.setHasFixedSize(true);
        List.add(new Data("ONE","TWO","THREE","FOUR","FIVE"));
        MyAdapter adapter = new MyAdapter(List);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);


        return secondView;
    }

    /*public View getView(GraphView graphViewFont) {

        Paint currentPainter = new Paint();
        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/Eurosti.ttf");
        currentPainter.setTypeface(typeFace);
        graphViewFont.setLayerType(View.LAYER_TYPE_HARDWARE,currentPainter);
        return graphViewFont;
    }*/

    /***
     * TODO create action bar
     **/
    private void GraphViewStyle(GraphView graph) {


        //Activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);
        //Activate horizontal scrolling
        graph.getViewport().setScrollable(true);

        //graph.getGridLabelRenderer().setLabelFormatter();
        //Activate horizontal and vertical zooming and scrolling
        //graph.getViewport().setScalableY(true);
        //Activate vertical scrolling
        //graph.getViewport().setScrollableY(true);
        //Custom graph title
        //graph.setTitleColor(Color.WHITE);
        //graph.setTitleTextSize(70);
        //set Y Axis Title
        //graph.getGridLabelRenderer().setVerticalAxisTitle("Bandwidth");
        //set X Axis Title
        //graph.getGridLabelRenderer().setHorizontalAxisTitle("Time/s");
        //set manual X bounds
        //graph.getViewport().setXAxisBoundsManual(true);
        //graph.getViewport().setMinX(0);
        //graph.getViewport().setMaxX(35);
        //set manual Y bounds
        /*graph.getViewport().setYAxisBoundsManual(true);
        //graph.computeScroll();
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(ReminderClient.scale);*/

    }

}

