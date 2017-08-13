package com.mobile.songlist.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.mobile.songlist.DataPasser;
import com.mobile.songlist.models.DownloadJSON;
import com.mobile.songlist.R;
import com.mobile.songlist.viewmodels.Track;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WelcomeActivity extends AppCompatActivity {

    private AsyncTask getTracksJSON;
    private final String TAG = getClass().getName();
    Handler mainHandler;
    Context context;


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
    TypingTextView typingText;

    private View mControlsView;

    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

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

        context = this;

            // hide action bar
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

            // set the Views
        setContentView(R.layout.activity_welcome);
        typingText = (TypingTextView) findViewById(R.id.typing_text);
        welcomeImage = (ImageView) findViewById(R.id.fullscreen_image);
        welcomeImage.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        /*  animation apparently only work on main UI thread, doesn't even work
            on Async's onPostExecute, which is supposed to be in the UI thread
        */

        mainHandler = new Handler(context.getMainLooper());

        Runnable fadeRunnable = new Runnable() {
            @Override
            public void run() {
                welcomeImage.startAnimation(getFadeOutAnimation());
            }
        };

        mainHandler.post(fadeRunnable);

            //start async task to get JSON data
        getTracksJSON = new AsyncTask<Void, Void, ArrayList<Track>>() {
            Thread animate;


            @Override
            protected ArrayList<Track> doInBackground(Void... voids) {

                // the animate thread should wait for the Typing thread to finish
                animate = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //run function to do animation here

                        typingText.setText("");
                        typingText.setCharacterDelay(470);
                        typingText.displayTextWithAnimation("welcome...");

                        try{
                            Thread.sleep(4500);
                        } catch (InterruptedException e){
                            Log.d("Animation typing thread", " was interrupted");
                        }

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

                    // pass list of tracks into next activity
                DataPasser.getInstance().save("track-list", list);


                Intent songlist = new Intent(WelcomeActivity.this, HomeListActivity.class);
                WelcomeActivity.this.startActivity(songlist);

            }
        }.execute();

        mVisible = true;
        mControlsView = findViewById(R.id.text_animation);

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

    private void hide() {  }

    @SuppressLint("InlinedApi")
    private void show() {

    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public Animation getFadeOutAnimation(){
        Log.d("Fade Out", "Should be starting");
        Animation fadeOut = new AlphaAnimation(1, (long)0.5);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(5000);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                welcomeImage.setVisibility(View.GONE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        return fadeOut;
    }

}
