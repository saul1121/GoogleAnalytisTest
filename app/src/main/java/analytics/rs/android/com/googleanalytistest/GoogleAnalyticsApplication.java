package analytics.rs.android.com.googleanalytistest;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by superadmin on 3/22/16.
 */
public class GoogleAnalyticsApplication extends Application {

    private Tracker mTracker;

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // Setting mTracker to Analytics Tracker declared in our xml Folder
            mTracker = analytics.newTracker(R.xml.analytics_tracker);
        }
        return mTracker;
    }

}