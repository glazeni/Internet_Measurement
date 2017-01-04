package internetmeasurement.android.fragment.third;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import internetmeasurement.android.R;

/**
 * Created by glazen on 23/12/16.
 */
public class ChildFragmentThirdNetwork extends Fragment {
    ConnectivityManager connectivityManager = null;
    TelephonyManager telephonyManager = null;
    WifiManager wifiManager = null;
    //NetworkInfo mWifi = null;
    //mWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate View
        View childThirdNetwork = inflater.inflate(R.layout.fragment_child_third_network, container, false);

        //Telephony Manager
        telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);

        //Wi-Fi Manager
        wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        Log.d("WIFI-MANAGER", wifiManager.getConnectionInfo().toString());

        //Connectivity Manager
        connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //Connection type
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        Log.d("CONNECTIVITY-MANAGER", netInfo.toString());

        if (netInfo.getType() == connectivityManager.TYPE_WIFI && netInfo.isConnected()) {
            TextView tvConnectionType = (TextView) childThirdNetwork.findViewById(R.id.connection_type);
            tvConnectionType.setText("Wi-Fi");

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


            Log.d("Detailed-State", networkInfo.getDetailedState().toString());
            Log.d("Active Network", netInfo.toString());
            //Log.d("Detailed-State", String.valueOf(networkInfo.getType()));
            //Log.d("Detailed-State", String.valueOf(networkInfo.getType()));




        } else {
            TextView tvConnectionType = (TextView) childThirdNetwork.findViewById(R.id.connection_type);
            tvConnectionType.setText("3G/4G");
        }

        //Private IP address
        TextView tvPrivateMobileIp = (TextView) childThirdNetwork.findViewById(R.id.mobile_private_ip_address);
        tvPrivateMobileIp.setText("Private Ip Address:  " + NetworkInterfacesUtils.getPrivateIPAddress(true));

        //Public IP address
        TextView tvPublicMobileIp = (TextView) childThirdNetwork.findViewById(R.id.mobile_public_ip_address);
        tvPublicMobileIp.setText("Public Ip Address: " + NetworkInterfacesUtils.getPublicIP());

        //Mobile Mac Address
        TextView tvMacAddress = (TextView) childThirdNetwork.findViewById(R.id.mobile_mac_address);
        if (netInfo.getType() == connectivityManager.TYPE_WIFI && netInfo.isConnected()) {
            tvMacAddress.setText("MAC Address: " + NetworkInterfacesUtils.getMACAddress("wlan0"));
        } else {
            tvMacAddress.setText("MAC Address: " + NetworkInterfacesUtils.getMACAddress("eth0"));
        }


        //Server IP address
        TextView tvServerIp = (TextView) childThirdNetwork.findViewById(R.id.server_ip_address);
        tvServerIp.setText("Server Ip Address: ");

        //Default Gateway
        TextView tvDefaultGateway = (TextView) childThirdNetwork.findViewById(R.id.default_gateway_address);
        tvDefaultGateway.setText("Default Gateway: " + NetworkInterfacesUtils.getDefaultGateway());

        //NetworkMask
        TextView tvnetMask = (TextView) childThirdNetwork.findViewById(R.id.network_mask);
        tvnetMask.setText("Network Mask: " + NetworkInterfacesUtils.getNetmask());

        //MTU Size
        TextView tvMTU = (TextView) childThirdNetwork.findViewById(R.id.mtu_size);
        tvMTU.setText("MTU Size: " + NetworkInterfacesUtils.getMTU());


        return childThirdNetwork;
    }


}


