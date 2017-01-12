package internetmeasurement.android.fragment.first;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import Client.TCPClient;
import internetmeasurement.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    protected Spinner mSpinner1 = null;
    protected Spinner mSpinner2 = null;
    protected ArrayAdapter<String> mAdapter1 = null;
    protected ArrayAdapter<String> mAdapter2 = null;
    private int mIndex1 = 0;
    private int mIndex2 = 0;
    private Button startButton;
    private ProgressBar progressBar;
    private int progressStatus = 0;

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
        // Inflate the layout for this fragment
        View firstView = inflater.inflate(R.layout.fragment_first, container, false);
        //Wi-Fi manager
        final WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        //Network manager
        final ConnectivityManager connectManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //Network Info
        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
        //final String networkType = networkInfo.getTypeName();


        //Custom Font to SpinnerTitle
        //TextView spinner1_title = (TextView) firstView.findViewById(R.id.spinner_text_1);
        //Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/Eurosti.ttf");
        //spinner1_title.setTypeface(typeFace);

        //Progress Bar
        progressBar = (ProgressBar) firstView.findViewById(R.id.progress_bar);

        //Begin Button
        startButton = (Button) firstView.findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = mSpinner2.getSelectedItemPosition();
                if (pos == 0) {
                    if (progressBar.getRotation() == 180) {
                        progressBar.setRotation(0);
                    }
                    updateProgressBar(0);
                } else if (pos == 1) {
                    progressBar.setRotation(180);
                    updateProgressBar(0);
                }

                pingCommand("ping -c 1 -w 1 google.com", false);
            }
        });

        //Spinner String List
        String[] values = new String[]{"None", "Wi-Fi", "3G", "4G"};
        String[] algorithms = new String[]{"Uplink", "Downlink"};


        /********************************         SPINNER 1 DEFINITION     ***************************************/
        mAdapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                //Custom Font Spinner items
                //Typeface typeFace_item = Typeface.createFromAsset(getContext().getAssets(), "fonts/Eurosti.ttf");
                //tv.setTypeface(typeFace_item);

                if (position == mSpinner1.getSelectedItemPosition()) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.WHITE);
                }
                return view;
            }
        };


        mAdapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinner1 = (Spinner) firstView.findViewById(R.id.spinner1);
        mSpinner1.setPrompt("Select your connection type");
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
        mSpinner2 = (Spinner) firstView.findViewById(R.id.spinner2);
        mSpinner2.setPrompt("Select your measurement type");
        mSpinner2.setAdapter(mAdapter2);


        return firstView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Restore the fragment's state
        if (savedInstanceState != null) {
            mIndex1 = savedInstanceState.getInt("SpinnerPosition1");
            mIndex2 = savedInstanceState.getInt("SpinnerPosition2");
            progressStatus = savedInstanceState.getInt("ProgressBarStatus");
            updateProgressBar(progressStatus);
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
        outState.putInt("ProgressBarStatus", progressStatus);
    }


    public static String pingCommand(String cmd, boolean sudo) {
        try {
            Process p;
            if (!sudo)
                p = Runtime.getRuntime().exec(cmd);
            else {
                p = Runtime.getRuntime().exec(new String[]{"su", "-c", cmd});
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s;
            String res = "";
            while ((s = stdInput.readLine()) != null) {
                res += s + "\n";
            }
            Log.d("PING", res);
            p.destroy();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    private void updateProgressBar(int pbStatus) {
        new Thread() {
            @Override
            public void run() {
                //super.run();
                try {
                    progressStatus = pbStatus;
                    while (progressStatus <= 100) {

                        sleep(500);
                        progressBar.setProgress(progressStatus);
                        progressStatus += 1;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (progressStatus >= 100) {
                        progressBar.setProgress(0);
                    }
                }
            }
        }.start();
    }

    public class RunTCPClient extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String args[] = {"", ""};
            TCPClient.main(args);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}




