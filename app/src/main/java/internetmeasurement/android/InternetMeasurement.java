package internetmeasurement.android;

import android.app.Application;

/**
 * Created by glazen on 21/12/16.
 */
public final class InternetMeasurement extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.setDefaultFont(getApplicationContext(), "MONOSPACE", "fonts/Eurosti.ttf");
    }
}
