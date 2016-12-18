package internetmeasurement.android;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragmentFirst extends Fragment {
    protected Spinner mSpinner1 = null;
    protected Spinner mSpinner2 = null;
    protected ArrayAdapter<String> mAdapter1 = null;
    protected ArrayAdapter<String> mAdapter2 = null;
    private int mIndex1 = 0;
    private int mIndex2=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //Wi-Fi manager
        final WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        //Network manager
        final ConnectivityManager connectManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //Network Info
        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
        //final String networkType = networkInfo.getTypeName();
        //Spinner String List
        String[] values = new String[]{"None","Wi-Fi", "3G", "4G"};
        String[] algorithms = new String[]{"1", "2", "3", "4"};
        // Inflate the layout for this fragment
        View ChildView = inflater.inflate(R.layout.fragment_child, container, false);


        /********************************         SPINNER 1 DEFINITION     ***************************************/
        mAdapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values) {
            /*@Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }*/

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.WHITE);
                }
                return view;
            }
        };


        mAdapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinner1 = (Spinner) ChildView.findViewById(R.id.spinner1);
        mSpinner1.setPrompt("Select your measurement type");
        mSpinner1.setAdapter(mAdapter1);
        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Notify the selected item text
                //String selectedItemText = (String) parent.getItemAtPosition(position);
                //Toast.makeText(getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                //Wi-Fi Selected
                if (position == 1) {
                    if (!wifiManager.isWifiEnabled()) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Wi-Fi is needed to perform operation")
                                .setMessage("Do you want to turn it on?")
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        wifiManager.setWifiEnabled(true);

                                    }
                                }).create().show();
                    }
                }
                //3G Selected
                if (position == 2) {


                }
                //4G Selected
                if (position == 3) {

                    //Toast.makeText(getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        /********************************         SPINNER 2 DEFINITION     ***************************************/
        mAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, algorithms);
        mAdapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinner2 = (Spinner) ChildView.findViewById(R.id.spinner2);
        mSpinner2.setPrompt("Select your algorithm");
        mSpinner2.setAdapter(mAdapter2);
        mSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Notify the selected item text
                //String selectedItemText = (String) parent.getItemAtPosition(position);
                //Toast.makeText(getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                //Wi-Fi Selected
                if (position == 1) {

                }
                //3G Selected
                if (position == 2) {

                }
                //4G Selected
                if (position == 3) {
                    //Toast.makeText(getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


        return ChildView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Restore the fragment's state
        if (savedInstanceState != null) {
            mIndex1 = savedInstanceState.getInt("SpinnerPosition1");
            mIndex2 = savedInstanceState.getInt("SpinnerPosition2");
            mSpinner1.setSelection(mIndex1);
            mSpinner2.setSelection(mIndex2);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        outState.putInt("SpinnerPosition1", mSpinner1.getSelectedItemPosition());
        outState.putInt("SpinnerPosition2", mSpinner2.getSelectedItemPosition());
    }

}

