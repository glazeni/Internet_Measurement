// TCPClient.java
// A client program implementing TCP socket
package internetmeasurement.android.TCPClient;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

//Java Client Main Class
public class TCPClient extends AsyncTask {

    private Socket s_up = null;
    private Connection connection = null;
    private TCP_Properties TCP_param = null;
    private DataMeasurement dataMeasurement = null;
    private int ID = 0;
    public boolean isNagleDisable;

    public TCPClient(boolean _isNagleDisable) {

        try {
            this.isNagleDisable = _isNagleDisable;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    protected Void doInBackground(Object... params) {
        try {
            //Data Measurement
            dataMeasurement = new DataMeasurement();
            //Socket Uplink + Connection
            s_up = new Socket(Constants.SERVER_IP, Constants.SERVERPORT);
            //Register Client in Server to get ID
            DataOutputStream dos = new DataOutputStream(s_up.getOutputStream());
            dos.writeInt(ID);
            dos.flush();
            TCP_param = new TCP_Properties(s_up, isNagleDisable);
            //Receive Client ID from Server
            DataInputStream dis = new DataInputStream(s_up.getInputStream());
            ID = dis.readInt();
            dos.writeBoolean(isNagleDisable);
            dos.flush();
            dos.writeInt(Constants.NUMBER_PACKETS);
            dos.flush();
            dos.writeInt(Constants.PACKETSIZE_DOWNLINK);
            dos.flush();
            dos.writeInt(Constants.BUFFERSIZE);
            dos.flush();
            dos.writeInt(Constants.SOCKET_RCVBUF);
            dos.flush();
            dos.writeInt(Constants.SOCKET_SNDBUF);
            dos.flush();
            System.err.println("isNagleDisable: " + isNagleDisable);
            connection = new Connection(ID, s_up, dataMeasurement, isNagleDisable);
            connection.start();
            connection.join();

            System.out.println("Client started connected to Port: " + Constants.SERVERPORT + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
