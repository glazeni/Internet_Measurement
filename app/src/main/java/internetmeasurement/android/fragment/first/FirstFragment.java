package internetmeasurement.android.fragment.first;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import Client.TCPClient;
import internetmeasurement.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    ChildFragmentFirst mChildFragmentFirst = null;
    Button startButton;
    Handler handler = new Handler();
    ProgressBar progressBar;
    int progressStatus = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Will ignore onDestroy Method (Nested Fragments no need this if parent have it)
        //setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View firstView = inflater.inflate(R.layout.fragment_first, container, false);


        progressBar = (ProgressBar) firstView.findViewById(R.id.progress_bar);
        //startProgress(firstView);

        new Thread() {
            @Override
            public void run() {
                //super.run();
                try {
                    progressStatus = 0;
                    while (progressStatus < 100) {
                        sleep(1000);
                        progressBar.setProgress(progressStatus);
                        progressStatus++;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        startButton = (Button) firstView.findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RunTCPClient runTCPClient = new RunTCPClient();
                //runTCPClient.execute();
            }
        });
        return firstView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mChildFragmentFirst == null && savedInstanceState == null) {
            mChildFragmentFirst = new ChildFragmentFirst();
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_first_container, mChildFragmentFirst).commit();
            getChildFragmentManager().executePendingTransactions();
            Log.d("FIRST-FRAGMENT", "NEW CHILD");
        } else {
            mChildFragmentFirst = (ChildFragmentFirst) getChildFragmentManager().findFragmentById(R.id.fragment_childfirst_container);
            getChildFragmentManager().executePendingTransactions();
            Log.d("FIRST-FRAGMENT", "OLD CHILD");
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


    //TODO BEM FEITO TERIA QUE FAZER NUM SERVICO EM BACKGROUND PORQUE NAO TEM TEMPO PARA TERMINAR....
    private class RunTCPClient extends AsyncTask<Void, Void, String> {
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

    public void startProgress(View view) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        progressStatus = 0;
        progressBar.setProgress(progressStatus);

        //progressBar.setVisibility(View.VISIBLE);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {
                while (progressStatus < 100) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressStatus += 10;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
                return null;
            }

        }.execute();
    }
}
