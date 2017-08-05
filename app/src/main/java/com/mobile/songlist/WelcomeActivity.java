package com.mobile.songlist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WelcomeActivity extends AppCompatActivity {

    private AsyncTask getTracksJSON;
    private final String TAG = getClass().getName();

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 6000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private ImageView welcomeImage;

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            welcomeImage = (ImageView) findViewById(R.id.fullscreen_image);

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            welcomeImage.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

            /*requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        }
    };
    private View mControlsView;

    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


            // hide action bar
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

            //start async task to get JSON data
        getTracksJSON = new AsyncTask<Void, Void, ArrayList<Track>>() {
            @Override
            protected ArrayList<Track> doInBackground(Void... voids) {

                Thread animate = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //run function to do animation here

                        try{
                            Thread.sleep(4500);
                        } catch (InterruptedException e){}

                        Log.d(TAG, "Subthread from AsyncTask started.");
                    }
                });
                animate.start();

                DownloadJSON downloadJSON = new DownloadJSON();
                try {
                    downloadJSON.cacheJSON();
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {
                    animate.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // pass array to onPostExecute
                return downloadJSON.tracks;
            }

            @Override
            protected void onPostExecute(ArrayList<Track> list) {

                //pass list of tracks into next activity
                DataPasser.getInstance().save("track-list", list);

                Intent songlist = new Intent(WelcomeActivity.this, MainActivity.class);
                WelcomeActivity.this.startActivity(songlist);

            }
        }.execute();



        setContentView(R.layout.activity_welcome);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);



        // Set up the user interaction to manually show or hide the system UI.
        /*welcomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //toggle();
            }
        });*/

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

        //START A THREAD TO LOAD IMAGE AND DO ANIMATION

        //HERE WAIT FOR ASYNC TO JOIN, AND ANIMATION THREAD TO JOIN.
        //ONCE JOINED, GO TO NEXT INTENT

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(0);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
       /* ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);*/
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
       /* welcomeImage.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);*/
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
