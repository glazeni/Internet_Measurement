package internetmeasurement.android.fragment.third;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import internetmeasurement.android.R;
import internetmeasurement.android.fragment.first.FirstFragment;

/**
 * Created by glazen on 23/12/16.
 */
public class ChildFragmentThirdSettings extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //Inflate view
        View childThird = inflater.inflate(R.layout.fragment_child_third_settings, container, false);

        //MTU Uplink Size
        TextView tvMTUuplinkSize = (TextView) childThird.findViewById(R.id.mtu_size_uplink);
        SeekBar mtuUplinkBar = (SeekBar) childThird.findViewById(R.id.mtu_uplink_bar);
        mtuUplinkBar.setProgress(1500);
        mtuUplinkBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvMTUuplinkSize.setText("MTU size Uplink: " + String.valueOf(progress) + " bytes");
                FirstFragment.BLOCKSIZE_UPLINK = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        //MTU Downlink Size
        TextView tvMTUdownlinkSize = (TextView) childThird.findViewById(R.id.mtu_size_downlink);
        SeekBar mtuDownlinkBar = (SeekBar) childThird.findViewById(R.id.mtu_downlink_bar);
        mtuDownlinkBar.setProgress(2600);
        mtuDownlinkBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvMTUdownlinkSize.setText("MTU size Downlink: " + String.valueOf(progress) + " bytes");
                FirstFragment.BLOCKSIZE_DOWNLINK = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        //Socket Rcv Buffer
        TextView tvSOrcvBuffer = (TextView) childThird.findViewById(R.id.socket_rcv_buffer);
        SeekBar SOrcvBar = (SeekBar) childThird.findViewById(R.id.socket_rcv_bar);
        SOrcvBar.setProgress(64000);
        SOrcvBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSOrcvBuffer.setText("Socket Receive Buffer: " + String.valueOf(progress) + " bytes");
                FirstFragment.SOCKET_RCV_BUFFER = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Socket Snd Buffer
        TextView tvSOsndBuffer = (TextView) childThird.findViewById(R.id.socket_snd_buffer);
        SeekBar SOsndBar = (SeekBar) childThird.findViewById(R.id.socket_snd_bar);
        SOsndBar.setProgress(64000);
        SOsndBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSOsndBuffer.setText("Socket Send Buffer: " + String.valueOf(progress) + " bytes");
                FirstFragment.SOCKET_SND_BUFFER = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Socket Timeout
        TextView tvSOtimeout = (TextView) childThird.findViewById(R.id.socket_timeout);
        SeekBar SOtimeoutBar = (SeekBar) childThird.findViewById(R.id.socket_timeout_bar);
        SOtimeoutBar.setProgress(5000);
        SOtimeoutBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSOtimeout.setText("Socket Timeout: " + String.valueOf(progress) + " ms");
                FirstFragment.SO_TIMEOUT = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setProgress(5000);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return childThird;
    }


}
