package internetmeasurement.android.fragment.third;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import internetmeasurement.android.R;
import internetmeasurement.android.TCPClient.Constants;

/**
 * Created by glazen on 23/12/16.
 */
public class ChildFragmentThirdSettings extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //Inflate view
        View childThird = inflater.inflate(R.layout.fragment_child_third_settings, container, false);

        //Number of Packets
        TextView tvNumPackets = (TextView) childThird.findViewById(R.id.number_packets);
        SeekBar numPacketsBar = (SeekBar) childThird.findViewById(R.id.number_packets_bar);
        numPacketsBar.setProgress(50);
        numPacketsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvNumPackets.setText("Number of Packets in the Train: " + String.valueOf(progress));
                Constants.NUMBER_PACKETS = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        //Packet Size Uplink
        TextView tvMTUsizeUP = (TextView) childThird.findViewById(R.id.mtu_size_up);
        SeekBar mtuBarUP = (SeekBar) childThird.findViewById(R.id.mtu_bar_up);
        mtuBarUP.setProgress(512);
        mtuBarUP.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvMTUsizeUP.setText("Packet Size Uplink: " + String.valueOf(progress) + " bytes");
                Constants.PACKETSIZE_UPLINK = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        //Packet Size Downlink
        TextView tvMTUsizeDOWN = (TextView) childThird.findViewById(R.id.mtu_size_down);
        SeekBar mtuBarDOWN = (SeekBar) childThird.findViewById(R.id.mtu_bar_down);
        mtuBarDOWN.setProgress(1024);
        mtuBarDOWN.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvMTUsizeDOWN.setText("Packet Size Downlink: " + String.valueOf(progress) + " bytes");
                Constants.PACKETSIZE_DOWNLINK = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        //Buffer Size
        TextView tvBufferSize = (TextView) childThird.findViewById(R.id.buffer_size);
        SeekBar bufferBar = (SeekBar) childThird.findViewById(R.id.buffer_bar);
        bufferBar.setProgress(256000);
        bufferBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvBufferSize.setText("Buffers Size: " + String.valueOf(progress) + " bytes");
                Constants.BUFFERSIZE = progress;
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
        SOrcvBar.setProgress(128000);
        SOrcvBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSOrcvBuffer.setText("Socket Receive Buffer: " + String.valueOf(progress) + " bytes");
                Constants.SOCKET_RCVBUF = progress;
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
        SOsndBar.setProgress(128000);
        SOsndBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSOsndBuffer.setText("Socket Send Buffer: " + String.valueOf(progress) + " bytes");
                Constants.SOCKET_SNDBUF = progress;

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
        SOtimeoutBar.setProgress(30000);
        SOtimeoutBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSOtimeout.setText("Socket Timeout: " + String.valueOf(progress) + " ms");
                Constants.SO_TIMEOUT = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return childThird;
    }


}
