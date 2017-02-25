package internetmeasurement.android.fragment.second;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import internetmeasurement.android.R;

/**
 * Created by glazen on 29/01/17.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;
    private List<Data> mDataList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvConnection;
        private TextView tvAlgorithm;
        private TextView tvDate;
        private TextView tvAverage;
        private TextView tvPing;

        public MyViewHolder(View v) {
            super(v);
            tvConnection = (TextView) v.findViewById(R.id.connection);
            tvAlgorithm = (TextView) v.findViewById(R.id.algorithm);
            tvDate = (TextView) v.findViewById(R.id.date);
            tvAverage = (TextView) v.findViewById(R.id.average);
            tvPing = (TextView) v.findViewById(R.id.ping);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Data> _list) {

        //mDataset = myDataset;
        mDataList = _list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_results, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data data = mDataList.get(position);
        holder.tvConnection.setText(data.getConnection());
        holder.tvAlgorithm.setText(data.getAlgorithm());
        holder.tvDate.setText(data.getDate());
        holder.tvAverage.setText(data.getAverage());
        holder.tvPing.setText(data.getPing());

    }

    @Override
    public int getItemCount() {

        //return mDataset.length;
        return mDataList.size();
    }
}

