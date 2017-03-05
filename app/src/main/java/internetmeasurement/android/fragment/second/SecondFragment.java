package internetmeasurement.android.fragment.second;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import internetmeasurement.android.TCPClient.Connection;
import internetmeasurement.android.TCPClient.ReminderClient;
import internetmeasurement.android.fragment.first.FirstFragment;


public class SecondFragment extends Fragment {
    public static LineGraphSeries<DataPoint> series = null;
    private List<Data> List = new ArrayList<>();
    private GraphView graph = null;
    private MyAdapter adapter = null;
    private RecyclerView rv = null;
    private LinearLayoutManager llm = null;
    private Button resultsButton = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate View
        View secondView = inflater.inflate(R.layout.fragment_second, container, false);
        //Recycler View & LayoutManager
        rv = (RecyclerView) secondView.findViewById(R.id.recycler_view);
        llm = new LinearLayoutManager(getContext());


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
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d HH:mm:ss");
        String dateString = sdf.format(date);

        //rv.setHasFixedSize(true);

        //adapter= new MyAdapter(List);
        //rv.setAdapter(adapter);
        resultsButton = (Button) secondView.findViewById(R.id.results_button);
        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirstFragment.isAlgorithmDone) {
                    String algorithm = null;
                    if(Connection.METHOD.equalsIgnoreCase("MV_Report")){
                        algorithm = "1SecThread";
                    }else if(Connection.METHOD.equalsIgnoreCase("MV_Report_readVector")){
                        algorithm = "SampleTime";
                    }else if(Connection.METHOD.equalsIgnoreCase("PT_Report")){
                        algorithm = "PacketTrain";
                    }else{
                        algorithm = "None";
                    }
                    List.add(new Data(FirstFragment.mSpinner1.getSelectedItem().toString()+" ", algorithm+" ", dateString+" ",
                            String.valueOf(Math.round(ReminderClient.average/35)*100/100.0)+" ", FirstFragment.pingValue));
                    adapter = new MyAdapter(List);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(llm);
                    FirstFragment.isAlgorithmDone=false;
                    ReminderClient.average = 0;
                }
            }
        });


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

