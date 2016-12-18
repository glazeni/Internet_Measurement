package internetmeasurement.android;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapterThirdFragment pagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View thirdView = inflater.inflate(R.layout.fragment_third,container,false);
        //TabLayout
        tabLayout = (TabLayout) thirdView.findViewById(R.id.tabs);

        //Initializing View Pager
        viewPager = (ViewPager) thirdView.findViewById(R.id.viewpager);

        //Creating PagerAdapter
        pagerAdapter = new PagerAdapterThirdFragment(getFragmentManager(),2);

        //Adding adapter to pager
        viewPager.setAdapter(pagerAdapter);

        //Wiring tabLayout with Viewpager
        tabLayout.setupWithViewPager(viewPager);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

        if(tabLayout != null) {
            setupTabTitles();
        }
        return thirdView;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    private void setupTabTitles(){
        tabLayout.getTabAt(0).setText("Network Info");
        tabLayout.getTabAt(1).setText("Settings");
    }
}
