/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internetmeasurement.android.TCPClient;

import android.util.Log;

import java.io.InputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Vector;

import internetmeasurement.android.fragment.second.SecondFragment;

public class RTInputStream extends FilterInputStream {

    private int bytesTotal = 0;
    public static int bytesGraph=0;
    public Vector<Long> readTimeVector = null;

    public RTInputStream(InputStream in) {
        super(in);
        readTimeVector = new Vector<Long>();
    }

    @Override
    public int read() throws IOException {
        long start = 0;
        int cnt = super.read();
        return cnt;
    }
    

    @Override
    public int read(byte data[]) throws IOException {
        long start = 0;
        int cnt = super.read(data);
        return cnt;
    }

    @Override
    public int read(byte data[], int off, int len) throws IOException {
        long start = System.currentTimeMillis();
        int count = super.read(data, off, len);

        bytesTotal += count;//Sum of all read bytes
        bytesGraph += count * 8; //Sum of all read bits to be shown in bandwidth graph
        return count;
    }
    
    public int getBytes(){
        return bytesTotal;
    }
    
    public int getBytes2Bits(){
        return bytesTotal*8;
    }

    public double getBitsConversion(){
        double bits = bytesTotal*8;
        if(bits>1000000){
            bits = bits/1000000;
            SecondFragment.GraphYLabel = "MBits";
            Log.d("MBs",String.valueOf(bits));
            return bits;
        }else if(bits>1000){
            bits = bits/1000;
            SecondFragment.GraphYLabel = "KBits";
            Log.d("KBs",String.valueOf(bits));
            return bits;
        }else{
            return bits;
        }
    }

    public void clearBytes(){
        bytesTotal=0;
    }
}
