package internetmeasurement.android.fragment.third;

/**
 * Created by glazen on 27/12/16.
 */

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;
//import org.apache.http.conn.util.InetAddressUtils;

public class NetworkInterfacesUtils {
    private static InetAddress DefaultGatewayAddress = null;

    /**
     * Convert byte array to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sbuf = new StringBuilder();
        for (int idx = 0; idx < bytes.length; idx++) {
            int intVal = bytes[idx] & 0xff;
            if (intVal < 0x10) sbuf.append("0");
            sbuf.append(Integer.toHexString(intVal).toUpperCase());
        }
        return sbuf.toString();
    }

    /**
     * Get utf8 byte array.
     *
     * @param str
     * @return array of NULL if error was found
     */
    public static byte[] getUTF8Bytes(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Load UTF8withBOM or any ansi text file.
     *
     * @param filename
     * @return
     * @throws java.io.IOException
     */
    public static String loadFileAsString(String filename) throws java.io.IOException {
        final int BUFLEN = 1024;
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(filename), BUFLEN);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFLEN);
            byte[] bytes = new byte[BUFLEN];
            boolean isUTF8 = false;
            int read, count = 0;
            while ((read = is.read(bytes)) != -1) {
                if (count == 0 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                    isUTF8 = true;
                    baos.write(bytes, 3, read - 3); // drop UTF8 bom marker
                } else {
                    baos.write(bytes, 0, read);
                }
                count += read;
            }
            return isUTF8 ? new String(baos.toByteArray(), "UTF-8") : new String(baos.toByteArray());
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Returns MAC address of the given interface name.
     *
     * @param interfaceName eth0, wlan0 or NULL=use first interface
     * @return mac address or empty string
     */
    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac == null) return "";
                StringBuilder buf = new StringBuilder();
                for (int idx = 0; idx < mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));
                if (buf.length() > 0) buf.deleteCharAt(buf.length() - 1);
                return buf.toString();
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
        /*try {
            // this is so Linux hack
            return loadFileAsString("/sys/class/net/" +interfaceName + "/address").toUpperCase().trim();
        } catch (IOException ex) {
            return null;
        }*/
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     */
    public static String getPrivateIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }


    public static String getDefaultGateway() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isAnyLocalAddress() && inetAddress instanceof Inet4Address) {
                        DefaultGatewayAddress = inetAddress;
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;

    }


    public static String getNetmask() {
        return getIPv4LocalNetMask(DefaultGatewayAddress, getNetPrefix()).toString();
    }


    public static int getMTU() {
        int MTU = 0;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                MTU = intf.getMTU();
                Log.e("MTU" + MTU + "Interface" + intf.getDisplayName(), String.valueOf(MTU));
                if (intf.getDisplayName() == "rmnet0") {
                    MTU = intf.getMTU();
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return MTU;
    }

    private static int getNetPrefix() {
        int netPrefix = 0;
        try {
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(DefaultGatewayAddress);
            //Get IpV4 InetAddres, get(0) to IpV6
            netPrefix = networkInterface.getInterfaceAddresses().get(1).getNetworkPrefixLength();
        } catch (
                Exception ex
                )

        {
            ex.printStackTrace();
        }

        return netPrefix;
    }

    /*
 * Get network mask for the IP address and network prefix specified...
 * The network mask will be returned has an IP, thus you can
 * print it out with .getHostAddress()...
 */
    private static InetAddress getIPv4LocalNetMask(InetAddress ip, int netPrefix) {

        try {
            // Since this is for IPv4, it's 32 bits, so set the sign value of
            // the int to "negative"...
            int shiftby = (1 << 31);
            // For the number of bits of the prefix -1 (we already set the sign bit)
            for (int i = netPrefix - 1; i > 0; i--) {
                // Shift the sign right... Java makes the sign bit sticky on a shift...
                // So no need to "set it back up"...
                shiftby = (shiftby >> 1);
            }
            // Transform the resulting value in xxx.xxx.xxx.xxx format, like if
            /// it was a standard address...
            String maskString = Integer.toString((shiftby >> 24) & 255) + "." + Integer.toString((shiftby >> 16) & 255) + "." + Integer.toString((shiftby >> 8) & 255) + "." + Integer.toString(shiftby & 255);
            // Return the address thus created...
            return InetAddress.getByName(maskString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Something went wrong here...
        return null;
    }


    static class getPublicIPAddress extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            String responseString = null;

            try {
                URL url = new URL("http://checkip.amazonaws.com/");
                //URL urlFullInfo = new URL("http://freegeoip.net/json/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                    // Do normal input or output stream reading
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        responseString = line;
                        Log.d("PUBLIC-IP", responseString);
                    }
                } else {
                    responseString = "FAILED"; // See documentation for more info on response handling
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
        }

    }

    public static String getPublicIP() {
        String response = null;
        try {
            String publicIP = new getPublicIPAddress().execute().get();
            response = publicIP;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }


}
