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
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;

import internetmeasurement.android.R;


public class SecondFragment extends Fragment {

    private RecyclerView mRecyclerView=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate View
        View secondView = inflater.inflate(R.layout.fragment_second, container, false);

        //Data Points Series 1
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 2)
        });
        series.setTitle("Fire");

        //GraphStyle
        GraphView graph = (GraphView) secondView.findViewById(R.id.graph);
        GraphViewStyle(graph);

        //Initializing Plot
        AdvancedLineGraph advancedLineGraph = new AdvancedLineGraph(secondView, series);
        advancedLineGraph.initGraph();

        //Get Date
        //TextView textViewDate = (TextView) secondView.findViewById(R.id.date_info);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, \n yyyy \n h:mm a");
        String dateString = sdf.format(date);
        //textViewDate.setText(dateString);


        //Recycler View
        mRecyclerView = (RecyclerView) secondView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


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
        //Activate horizontal and vertical zooming and scrolling
        graph.getViewport().setScalableY(true);
        //Activate vertical scrolling
        graph.getViewport().setScrollableY(true);
        //Custom graph title
        //graph.setTitleColor(Color.WHITE);
        //graph.setTitleTextSize(70);
        //set Y Axis Title
        //graph.getGridLabelRenderer().setVerticalAxisTitle("Bandwidth");
        //set X Axis Title
        //graph.getGridLabelRenderer().setHorizontalAxisTitle("Time/s");
        //set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.5);
        graph.getViewport().setMaxX(3.5);
        //set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.computeScroll();
        graph.getViewport().setMinY(3.5);
        graph.getViewport().setMaxY(8);

    }

    private class resultsHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public resultsHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView;
        }
    }
}
