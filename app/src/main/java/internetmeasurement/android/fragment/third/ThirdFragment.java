package internetmeasurement.android.fragment.third;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import internetmeasurement.android.R;
import internetmeasurement.android.main.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {

    private static FragmentManager childFM;

    ChildFragmentThirdNetwork mChildFragmentThirdNetwork = null;
    ChildFragmentThirdSettings mChildFragmentThirdSettings = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //    Inflate the layout for this fragment
        View thirdView = inflater.inflate(R.layout.fragment_third, container, false);

        //Toolbar
        Toolbar toolbar = (Toolbar) thirdView.findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(0);
        toolbar.setTitle("");

        //Set toolbar to Main Activity
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);


        return thirdView;


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        childFM= getChildFragmentManager();
        if ((mChildFragmentThirdNetwork == null || mChildFragmentThirdSettings == null) && savedInstanceState == null) {
            mChildFragmentThirdNetwork = new ChildFragmentThirdNetwork();
            mChildFragmentThirdSettings = new ChildFragmentThirdSettings();
        } else {
            mChildFragmentThirdNetwork = (ChildFragmentThirdNetwork) getChildFragmentManager().findFragmentById(R.id.fragment_childthird_network);
            mChildFragmentThirdSettings = (ChildFragmentThirdSettings) getChildFragmentManager().findFragmentById(R.id.fragment_childthird_settings);
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_network_info:

                //Toast.makeText(getContext(), "Selected : " + item.toString(), Toast.LENGTH_SHORT).show();
                childFM.beginTransaction().replace(R.id.fragment_third_container, mChildFragmentThirdNetwork).commit();
                //childFM.executePendingTransactions();
                break;

            case R.id.menu_variable_settings:
                //Toast.makeText(getContext(), "Selected : " + item.toString(), Toast.LENGTH_SHORT).show();
                childFM.beginTransaction().replace(R.id.fragment_third_container, mChildFragmentThirdSettings).commit();
                //childFM.executePendingTransactions();
                break;

        }
        return true;
    }

    //Options Menu needs toolbar to exist
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
