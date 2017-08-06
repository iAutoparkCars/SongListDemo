package com.mobile.songlist;

/**
 * Created by Steven on 8/6/2017.
 */

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Random;


public class TypingTextView extends android.support.v7.widget.AppCompatTextView {

    private int seqIndex;
    private CharSequence charSequence;
    private long delay = 100;   //init to 100ms

    public TypingTextView(Context context) {
        super(context);
    }

    public TypingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler handler = new Handler();
    public Runnable runnable = new Runnable() {

        @Override
        public void run() {

        setText(charSequence.subSequence(0, seqIndex++));

            // delay after each character
        if (seqIndex <= charSequence.length()) {
            handler.postDelayed(runnable, delayRandomizer(delay));
        }

        }
    };

    /*
     * Display text with type writer animation
     * @param txt content will be displayed
     */
    public void displayTextWithAnimation(CharSequence txt) {
        charSequence = txt;
        seqIndex = 0;

        setText("");

        handler.removeCallbacks(runnable);

            // delay before start typing
        handler.postDelayed(runnable, delayRandomizer(delay));
    }

    /*
     * Set the delay value with this method
     * @param delay
     */
    public void setCharacterDelay(long delay) {
        this.delay = delay;
    }


    /*
    * Returns delay equal to or less than the
    * delay specified by the user (randomized), to make the
    * "typing" seem more realistic
    *
    * @param randDelay, the Delay specified by the user.
    */
    private long delayRandomizer(long randDelay){

            // rand int between 0 and 10
        int randInt = (int)(new Random().nextDouble()*10);

            // with 8/11 chance, reduce delay
        if (randInt <= 3){

            //rand double between .6 and .9
            double delay = (60+(new Random().nextDouble()*30))/100.0;

            delay = randDelay*delay;
            randDelay = (long) delay;
            Log.d("Typing delay ","delay is " + randDelay);
        }

        return randDelay;
    }

}
