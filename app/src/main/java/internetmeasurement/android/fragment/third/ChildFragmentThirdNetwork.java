package internetmeasurement.android.fragment.third;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import internetmeasurement.android.R;

/**
 * Created by glazen on 23/12/16.
 */
public class ChildFragmentThirdNetwork extends Fragment {
    //TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
//    WifiManager wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //Inflate View
        View childThird = inflater.inflate(R.layout.fragment_child_third_network, container, false);





        return childThird;
    }
}