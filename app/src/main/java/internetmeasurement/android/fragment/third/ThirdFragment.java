package internetmeasurement.android.fragment.third;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import internetmeasurement.android.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //    Inflate the layout for this fragment
        View fourthView = inflater.inflate(R.layout.fragment_fourth, container, false);
        Toolbar toolbar = (Toolbar) fourthView.findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(0);
        toolbar.setTitle("");

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);




        return fourthView;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
