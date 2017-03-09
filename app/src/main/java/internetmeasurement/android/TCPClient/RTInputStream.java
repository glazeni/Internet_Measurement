/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetmeasurement.android.TCPClient;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RTInputStream extends FilterInputStream {

    private int bytesTotal = 0;

    public RTInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int cnt = super.read();
        return cnt;
    }


    @Override
    public int read(byte data[]) throws IOException {
        int cnt = super.read(data);
        return cnt;
    }

    @Override
    public int read(byte data[], int off, int len) throws IOException {
        int count = super.read(data, off, len);

        bytesTotal += count;//Sum of all read bytes
        return count;
    }

    public int getBytes() {
        return bytesTotal;
    }

    public int getBytes2Bits() {
        return bytesTotal * 8;
    }

    public double getBitsConversion() {
        double bits = bytesTotal * 8;
//        if (bits > 1000000) {
//            bits = bits / 1000000;
//            //SecondFragment.tvGraphYLabel.setText("Bandwidth [MBits]");
//            return bits;
//        }else if(bits>1000){
//            bits = bits/1000;
//            //SecondFragment.tvGraphYLabel.setText("Bandwidth [KBits]");
//            return bits;
//        } else {
        bits = bits / 1000000;
        return bits;
    }



    public void clearBytes() {
        bytesTotal = 0;
    }
}
