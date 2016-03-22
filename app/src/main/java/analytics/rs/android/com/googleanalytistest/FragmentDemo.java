package analytics.rs.android.com.googleanalytistest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Vamsi Tallapudi on 24-Oct-15.
 */
public class FragmentDemo extends Fragment {

    private static final String TAG = FragmentDemo.class.getSimpleName();
    private Tracker mTracker;
    String name = new String("Fragment Demo");

    public FragmentDemo(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleAnalyticsApplication application = (GoogleAnalyticsApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_demo, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName(name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }

}