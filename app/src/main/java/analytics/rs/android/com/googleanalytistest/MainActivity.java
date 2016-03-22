package analytics.rs.android.com.googleanalytistest;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Analytics Integration
        // Obtain the shared Tracker instance.
        GoogleAnalyticsApplication application = (GoogleAnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Button bNextScreen = (Button) findViewById(R.id.bNextScreen);
        Button bFragment = (Button) findViewById(R.id.bFragment);
        Button bEvent = (Button) findViewById(R.id.bEvent);
        Button bException = (Button) findViewById(R.id.bException);
        Button bCrashApp = (Button) findViewById(R.id.bCrashApp);
        bFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment myFragment = (Fragment) fragmentManager.findFragmentByTag("TAG_FRAGMENT");
                if (myFragment == null) {

                    myFragment = new FragmentDemo();

                    fragmentTransaction.replace(R.id.container, myFragment, "TAG_FRAGMENT");
                    fragmentTransaction.commit();

                } else {
                    fragmentTransaction.remove(myFragment).commit();

                }

            }
        });


        bEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get tracker.
                Tracker t = ((GoogleAnalyticsApplication) getApplication()).getDefaultTracker();

                // Build and send an Event.
                t.send(new HitBuilders.EventBuilder()
                        .setCategory(getString(R.string.categoryId))
                        .setAction(getString(R.string.actionId))
                        .setLabel(getString(R.string.labelId))
                        .build());
                Toast.makeText(MainActivity.this, "Event is recorded. Check Google Analytics!", Toast.LENGTH_LONG).show();
            }
        });


        bCrashApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Get Ready for App Crash!", Toast.LENGTH_LONG).show();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        startCrash();

                    }
                    public void startCrash() {
                        //Manually throwing nullPointer Exception using throw keyword
                        throw null;
                    }

                };
                Handler h = new Handler();
                h.postDelayed(r, 1500);
            }
        });

        bException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exception e = null;

                try{
                    int num[]={1,2,3,4};
                    System.out.println(num[5]);
                }catch (Exception f){
                    e = f;
                }
                if( e != null){

                    Toast.makeText(MainActivity.this, "The Exception is: " + e, Toast.LENGTH_LONG).show();

                    Tracker t = ((GoogleAnalyticsApplication) getApplication()).getDefaultTracker();
                    t.send(new HitBuilders.ExceptionBuilder()
                            .setDescription(new StandardExceptionParser(MainActivity.this, null).getDescription(Thread.currentThread().getName(), e))
                            .setFatal(false)
                            .build());
                }



            }
        });





        bNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, NextScreen.class);
                startActivity(i);


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        mTracker.setScreenName("Main Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }
}
