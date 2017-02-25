package internetmeasurement.android.fragment.second;

/**
 * Created by glazen on 24/02/17.
 */
public class Data {
    private String connection;
    private String algorithm;
    private String date;
    private String average;
    private String ping;

    public Data(String _connection, String _algorithm, String _date, String _average, String _ping) {
        connection = _connection;
        algorithm = _algorithm;
        date = _date;
        average = _average;
        ping = _ping;

    }

    public String getConnection() {
        return connection;
    }

    public String getAlgorithm(){
        return algorithm;
    }

    public String getDate(){
        return date;
    }

    public String getAverage(){
        return average;
    }

    public String getPing(){
        return ping;
    }
}