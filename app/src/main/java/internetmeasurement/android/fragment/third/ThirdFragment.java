package internetmeasurement.android.fragment.third;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    FragmentManager childFM = null;
    View mthirdView=null;
    ChildFragmentThirdNetwork mChildFragmentThirdNetwork = null;
    ChildFragmentThirdSettings mChildFragmentThirdSettings = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //    Inflate the layout for this fragment
        View thirdView = inflater.inflate(R.layout.fragment_third, container, false);
        mthirdView=thirdView;
        //Initialize NestedFragmentManager
        childFM = getChildFragmentManager();
        //Show Options Menu
        setHasOptionsMenu(true);

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
        if (savedInstanceState != null && mChildFragmentThirdNetwork != null) {
            mChildFragmentThirdNetwork = (ChildFragmentThirdNetwork) getChildFragmentManager().findFragmentById(R.id.fragment_childthird_network);
        } else if (savedInstanceState != null && mChildFragmentThirdSettings != null) {
            mChildFragmentThirdSettings = (ChildFragmentThirdSettings) getChildFragmentManager().findFragmentById(R.id.fragment_childthird_settings);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Restore Fragment's state

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state
    }

    //Options Menu needs toolbar to exist
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransactionNetwork = childFM.beginTransaction();
        FragmentTransaction fragmentTransactionSettings = childFM.beginTransaction();
        switch (item.getItemId()) {
            case R.id.menu_network_info:
                //Toast.makeText(getContext(), "Selected : " +
                if (mChildFragmentThirdNetwork == null) {
                    mChildFragmentThirdNetwork = new ChildFragmentThirdNetwork();
                    childFM.beginTransaction();
                    fragmentTransactionNetwork.replace(R.id.fragment_third_container, mChildFragmentThirdNetwork).commit();
                } else {
                    //mChildFragmentThirdNetwork = (ChildFragmentThirdNetwork) childFM.findFragmentById(R.id.fragment_childthird_network);
                    fragmentTransactionNetwork.replace(R.id.fragment_third_container, mChildFragmentThirdNetwork).commit();
                }
                childFM.executePendingTransactions();
                return true;
            case R.id.menu_variable_settings:
                //Toast.makeText(getContext(), "Selected : " + item.toString(), Toast.LENGTH_SHORT).show();
                if (mChildFragmentThirdSettings == null) {
                    mChildFragmentThirdSettings = new ChildFragmentThirdSettings();
                    childFM.beginTransaction();
                    fragmentTransactionSettings.replace(R.id.fragment_third_container, mChildFragmentThirdSettings).commit();
                } else {
                    //mChildFragmentThirdSettings = (ChildFragmentThirdSettings) childFM.findFragmentById(R.id.fragment_childthird_settings);
                    //mChildFragmentThirdSettings.getView();
                    fragmentTransactionSettings.replace(R.id.fragment_third_container, mChildFragmentThirdSettings).commit();
                }
                childFM.executePendingTransactions();
                return true;
            default:
                return false;
        }

    }


}
