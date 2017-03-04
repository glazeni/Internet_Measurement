/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetmeasurement.android.TCPClient;

import com.jjoe64.graphview.series.DataPoint;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import internetmeasurement.android.fragment.second.SecondFragment;

/**
 * @author glazen
 */
public class ReminderClient extends Thread {

    private Timer timer = null;
    private DataMeasurement dataMeasurement = null;
    private RTInputStream RTin = null;
    private int i = 0;
    private static final AtomicInteger count = new AtomicInteger(0);

    public ReminderClient(int seconds, DataMeasurement _dataMeasurement, RTInputStream _RTin) {
        this.dataMeasurement = _dataMeasurement;
        this.RTin = _RTin;
        timer = new Timer();
        //timer.schedule(new RemindTask(), 0, seconds);
        timer.scheduleAtFixedRate(new RemindTask(), 0, (seconds * 1000));

    }

    public void cancelTimer() {
        timer.cancel();
    }

    class RemindTask extends TimerTask {
        SimpleDateFormat sdf=null;
        public RemindTask() {
            //Do nothing in constructor
        }

        @Override
        public void run() {
            try {
                dataMeasurement.add_SampleSecond_down(RTin.getBytes2Bits());
                System.out.println("REMINDER CLIENT" + i + " with " + "bits=" + RTin.getBytes2Bits());
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (i == 0) {
                    SecondFragment.series.appendData(new DataPoint(count.get(), 0), true, 40);
                } else {
                    SecondFragment.series.appendData(new DataPoint(count.incrementAndGet(), RTin.getBitsConversion()), true, 40);
                }
                RTin.clearBytes();
                i++;
            }
        }
    }
}
