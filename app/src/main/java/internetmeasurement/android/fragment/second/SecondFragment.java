package internetmeasurement.android.fragment.second;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import internetmeasurement.android.R;


public class SecondFragment extends Fragment {
    public static LineGraphSeries<DataPoint> series = null;
    private static List<Data> List = new ArrayList<>();
    public static GraphView graph = null;
    private static RecyclerView rv = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate View
        View secondView = inflater.inflate(R.layout.fragment_second, container, false);
        //Recycler View
        rv = (RecyclerView) secondView.findViewById(R.id.recycler_view);

        //Data Points Series
        series = new LineGraphSeries<>();

        //GraphStyle
        graph = (GraphView) secondView.findViewById(R.id.graph);
        //Fix Y-Axis Numbers from being cut off
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setPadding(64);
        //Add Series
        graph.addSeries(series);
        series.setDrawDataPoints(true);
        ///Activate vertical & horizontal scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        //Define Maximum of 2 decimal numbers
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumIntegerDigits(3);
        nf.setMaximumFractionDigits(1);
        glr.setLabelFormatter(new DefaultLabelFormatter(nf, nf));


        //Get Date
        //TextView textViewDate = (TextView) secondView.findViewById(R.id.date_info);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, \n yyyy \n h:mm a");
        String dateString = sdf.format(date);
        //textViewDate.setText(dateString);

        //rv.setHasFixedSize(true);
        List.add(new Data("1","2","3","4","5"));
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

}

