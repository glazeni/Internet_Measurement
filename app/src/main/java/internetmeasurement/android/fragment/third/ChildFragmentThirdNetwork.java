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
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        Log.d("CONNECTIVITY-MANAGER", activeNetInfo.toString());

        if (!activeNetInfo.isAvailable() && !activeNetInfo.isConnected()) {
            TextView tvConnectionType = (TextView) childThirdNetwork.findViewById(R.id.connection_type);
            tvConnectionType.setText("No active connection!");
        } else {

            //WI-FI TYPE!!
            if (activeNetInfo.getType() == connectivityManager.TYPE_WIFI) {
                TextView tvConnectionType = (TextView) childThirdNetwork.findViewById(R.id.connection_type);
                tvConnectionType.setText("Wi-Fi");

                //Mobile Mac Address
                TextView tvMacAddress = (TextView) childThirdNetwork.findViewById(R.id.mobile_mac_address);
                tvMacAddress.setText("MAC Address: " + NetworkInterfacesUtils.getMACAddress("wlan0"));

                //SSID - WI-FI name
                TextView tvEXTRAinfo = (TextView) childThirdNetwork.findViewById(R.id.extra_info);
                tvEXTRAinfo.setText("SSID: " + activeNetInfo.getExtraInfo().toString());


                //CELLULAR TYPE!!
            } else {
                TextView tvConnectionType = (TextView) childThirdNetwork.findViewById(R.id.connection_type);
                tvConnectionType.setText(getNetworkClass());



                //Mobile Mac Address / IMEI
                TextView tvMacAddress = (TextView) childThirdNetwork.findViewById(R.id.mobile_mac_address);
                tvMacAddress.setText("IMEI: " + telephonyManager.getDeviceId().toString());


                //Cellular System
                TextView tvEXTRAinfo = (TextView) childThirdNetwork.findViewById(R.id.extra_info);
                tvEXTRAinfo.setText("Cellular System: " + activeNetInfo.getExtraInfo().toString());

                //Network Operator
                TextView tvNetworkOperator = (TextView) childThirdNetwork.findViewById(R.id.network_operator);
                tvNetworkOperator.setText("Operator: "+ telephonyManager.getNetworkOperatorName());

                //Cell Location
                TextView tvCellLocation = (TextView) childThirdNetwork.findViewById(R.id.cell_location);
                tvCellLocation.setText("Cell Location: "+telephonyManager.getCellLocation().toString());


            }


            //Private IP address
            TextView tvPrivateMobileIp = (TextView) childThirdNetwork.findViewById(R.id.mobile_private_ip_address);
            tvPrivateMobileIp.setText("Private Ip Address:  " + NetworkInterfacesUtils.getPrivateIPAddress(true));

            //Public IP address
            TextView tvPublicMobileIp = (TextView) childThirdNetwork.findViewById(R.id.mobile_public_ip_address);
            tvPublicMobileIp.setText("Public Ip Address: " + NetworkInterfacesUtils.getPublicIP());

            //Server IP address
            TextView tvServerIp = (TextView) childThirdNetwork.findViewById(R.id.server_ip_address);
            tvServerIp.setText("Server Ip Address: 193.136.127.218");

            //Default Gateway
            TextView tvDefaultGateway = (TextView) childThirdNetwork.findViewById(R.id.default_gateway_address);
            tvDefaultGateway.setText("Default Gateway: " + NetworkInterfacesUtils.getDefaultGateway());

            //NetworkMask
            TextView tvnetMask = (TextView) childThirdNetwork.findViewById(R.id.network_mask);
            tvnetMask.setText("Network Mask: " + NetworkInterfacesUtils.getNetmask());

            //MTU Size
            TextView tvMTU = (TextView) childThirdNetwork.findViewById(R.id.mtu_size);
            tvMTU.setText("MTU Size: " + NetworkInterfacesUtils.getMTU());
        }
        return childThirdNetwork;
    }



    private String getNetworkClass() {
        int networkType = telephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G";
            default:
                return "Unknown";
        }
    }
}





