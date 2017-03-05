package internetmeasurement.android.fragment.first;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import internetmeasurement.android.R;
import internetmeasurement.android.TCPClient.Connection;
import internetmeasurement.android.TCPClient.TCPClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    public static Spinner mSpinner1 = null;
    protected ArrayAdapter<String> mAdapter1 = null;
    private int mIndex1 = 0;
    private Button startButton;
    public static ProgressBar progressBar;
    private boolean isNagleDisable;
    public static boolean isAlgorithmDone = false;
    public static String pingValue=null;

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
        //Telephony manager
        final TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);

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
                if (mSpinner1.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), "Please Select a Connection Type", Toast.LENGTH_SHORT).show();
                }else {
                    //Test
                    Toast.makeText(getContext(), "Measurement Test Started!", Toast.LENGTH_SHORT).show();
                    TCPClient tcpClient = new TCPClient(isNagleDisable);
                    tcpClient.execute();

                    //Ping
                    pingValue = pingCommand("ping -c 1 -w 1 google.com", false);
//                RunTCPClient runTCPClient = new RunTCPClient();
//                runTCPClient.execute();
                }
            }
        });

        //Spinner String List
        String[] values = new String[]{"None", "Wi-Fi", "3G", "4G"};


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
                    //Log.d("SPINNER1", mSpinner1.getSelectedItem().toString());
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


                //Wi-Fi Selected
                if (position == 1) {
                    if (connectManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED || connectManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("You need to turn off mobile data to perform Wi-Fi measurements")
                                .setMessage("Do you want to turn it off?")
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);

                                    }
                                }).create().show();
                    } else if (!wifiManager.isWifiEnabled()) {
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
                    if (wifiManager.isWifiEnabled()) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("You need to turn off Wi-Fi to perform Cellular measurement")
                                .setMessage("Do you want to turn it off?")
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);

                                    }
                                }).create().show();
                    } else if (connectManager.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Mobile Data is needed to perform operation")
                                .setMessage("Do you want to turn it on?")
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);

                                    }
                                }).create().show();
                    }
                }
                //4G Selected
                if (position == 3) {
                    if (wifiManager.isWifiEnabled()) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("You need to turn off Wi-Fi to perform Cellular measurement")
                                .setMessage("Do you want to turn it off?")
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);

                                    }
                                }).create().show();
                    } else if (connectManager.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Mobile Data is needed to perform operation")
                                .setMessage("Do you want to turn it on?")
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);

                                    }
                                }).create().show();
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        //CheckBox's
        CheckBox checkBoxNagle = (CheckBox) firstView.findViewById(R.id.checkbox_nagle);
        checkBoxNagle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isNagleDisable = isChecked;
            }
        });

        return firstView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Restore the fragment's state
        if (savedInstanceState != null) {
            mIndex1 = savedInstanceState.getInt("SpinnerPosition1");
            //progressStatus = savedInstanceState.getInt("ProgressBarStatus");
            //updateProgressBar_PacketTrainUP();
            mSpinner1.setSelection(mIndex1);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        outState.putInt("SpinnerPosition1", mSpinner1.getSelectedItemPosition());
        //outState.putInt("ProgressBarStatus", progressStatus);
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
            String part1=null;
            String[] parts=null;
            String res = "";
            while ((s = stdInput.readLine()) != null) {
                part1 = s.replaceAll("\\s+", "");
                if(part1.contains("time=")){
                    parts = part1.split("time=");
                    break;
                }
            }
            p.destroy();
            return parts != null ? parts[1] : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static void updateProgressBar_up() {
        new Thread() {
            @Override
            public void run() {
                try {
                    progressBar.setProgress(0);
                    long end = System.currentTimeMillis() + Connection.runningTime;
                    long start = System.currentTimeMillis();
                    while (System.currentTimeMillis() < end) {
                        if (System.currentTimeMillis() >= (start + 1000)) {
                            progressBar.incrementProgressBy(3);
                            start = System.currentTimeMillis();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    progressBar.setProgress(0);
                }
            }
        }.start();
    }

    public static void updateProgressBar_down() {
        new Thread() {
            @Override
            public void run() {
                try {
                    progressBar.setProgress(0);
                    progressBar.setRotation(180);
                    long end = System.currentTimeMillis() + Connection.runningTime;
                    long start = System.currentTimeMillis();
                    while (System.currentTimeMillis() < end) {
                        if (System.currentTimeMillis() >= (start + 1000)) {
                            progressBar.incrementProgressBy(3);
                            start = System.currentTimeMillis();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    progressBar.setProgress(0);
                }
            }
        }.start();
    }
}


//Run Jar in Android
    /*public class RunTCPClient extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String arg0 = String.valueOf(BLOCKSIZE_UPLINK);
            String arg1 = String.valueOf(BLOCKSIZE_DOWNLINK);
            String arg2 = String.valueOf(SOCKET_RCV_BUFFER);
            String arg3 = String.valueOf(SOCKET_SND_BUFFER);
            String arg4 = String.valueOf(SO_TIMEOUT);
            String arg5 = String.valueOf(NUMBER_BLOCKS);

            String args[] = {arg0, arg1, arg2, arg3, arg4, arg5};
            //TCPClient.main(args);
*//*            Socket clientSocket;

            try {
                ServerSocket listenSocket = new ServerSocket(20001);
                clientSocket = listenSocket.accept();
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

                System.err.println("INT:" +dis.readInt());
            } catch (IOException e) {
                e.printStackTrace();
            }*//*

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
*/



