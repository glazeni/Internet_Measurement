package internetmeasurement.android.fragment.second;

import com.jjoe64.graphview.GraphView;

import internetmeasurement.android.main.MainActivity;

/**
 * Created by jonas on 10.09.16.
 */
public abstract class BaseExample{
    //onCreate function is created in order to be Overriden by AdvancedLineGraph
    public abstract void onCreate(MainActivity fullscreenActivity);
    public abstract void initGraph(GraphView graph);

}
