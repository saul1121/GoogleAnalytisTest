package analytics.rs.android.com.googleanalytistest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class NextScreen extends AppCompatActivity {

        private static final String TAG = NextScreen.class.getSimpleName();
        private Tracker mTracker;
        private String name = "Next Screen";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_next_screen);

            //Analytics Integration
            // Obtain the shared Tracker instance.
            GoogleAnalyticsApplication application = (GoogleAnalyticsApplication) getApplication();
            mTracker = application.getDefaultTracker();

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        @Override
        protected void onResume() {
            super.onResume();
            Log.i(TAG, "Setting screen name: " + name);
            mTracker.setScreenName(name);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
}
