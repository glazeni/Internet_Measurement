package internetmeasurement.android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import Client.TCPClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    ChildFragmentFirst mChildFragmentFirst=null;
    Button startButton;
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

        startButton = (Button) firstView.findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CorreTCPClient correTCPClient = new CorreTCPClient();
                correTCPClient.execute();
            }
        });
        return firstView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mChildFragmentFirst==null && savedInstanceState ==null){
            mChildFragmentFirst = new ChildFragmentFirst();
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_first_container, mChildFragmentFirst).commit();
            getChildFragmentManager().executePendingTransactions();
            Log.d("FIRST-FRAGMENT", "NEW CHILD");
        }else{
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
    private class CorreTCPClient extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            String args[] = {"",""};
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
