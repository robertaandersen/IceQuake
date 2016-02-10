package is.robertreynisson.icequake;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import is.robertreynisson.icequake.utils.Utils;

/**
 * Created by robert on 9.2.2016.
 */
public class IceQuake extends Application {
    private static final String TAG = IceQuake.class.getSimpleName();

    private static IceQuake instance;
    public static IceQuake getInstance() { return instance; }


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.logger(TAG, "onCreate");
        instance = this;
        JodaTimeAndroid.init(this);
    }

    public static String getServerInfo() { return "http://apis.is";}

}
