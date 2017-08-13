package com.mobile.songlist.views;



import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.songlist.DataPasser;
import com.mobile.songlist.R;
import com.mobile.songlist.viewmodels.Track;

import java.io.IOException;

/**
 * Created by Steven on 8/1/2017.
 */

//CONSIDER EXTENDING DIALOG FRAGMENT

public class DetailsFragment extends DialogFragment {

    // TextView's for details
    private TextView trackName;
    private TextView collectionName;
    private TextView trackNumber;
    private TextView trackLength;

    // Button and Player to stream preview
	private Button previewButton;
    private MediaPlayer player;

    // the Track that the user selected
    private Track track;

    int info;
    String previewUrl = "";
    private Thread streamThread;
    private boolean isPlaying = false;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get info from the Activity that created this fragment
        info = getArguments().getInt("title");

        this.track = (Track) DataPasser.getInstance().retrieve("trackData").get(0);
        this.previewUrl = this.track.getPreviewURL().toString();

        Log.d("DetailsFragment", "previewURL: " + previewUrl);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //super.onCreateView(inflater, container,savedInstanceState);
        View currentView = inflater.inflate(R.layout.fragment_detail, container, false);

        // button to start streaming audio
		previewButton = (Button) currentView.findViewById(R.id.preview_button);
        previewButton.setText("PLAY");

        // name, album, number, length
        setTextDetails(currentView);

        previewButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if (isPlaying){
                     resetPreviewFragment();
                     isPlaying = false;
                     getDialog().dismiss();
                     return;
                 }

                 if (previewUrl==null) {
                     // Make toast saying there's no Audio
                     Toast.makeText(getActivity(), "No preview for this track", Toast.LENGTH_SHORT);
                     return;
                 }

                 playMedia(previewUrl);
                 previewButton.setText("STOP");
                 isPlaying = true;
             }
         });



        currentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //currentView.dispatchWindowSystemUiVisiblityChanged();
        return currentView;
    }

    private void setTextDetails(View currentView) {
        // setting the TextViews for details
        trackName = (TextView) currentView.findViewById(R.id.track_name);
        collectionName = (TextView) currentView.findViewById(R.id.collection_name);
        trackNumber = (TextView) currentView.findViewById(R.id.track_number);
        trackLength = (TextView) currentView.findViewById(R.id.track_length);

        trackName.setText(this.track.getTrackName());
        collectionName.setText(this.track.getCollectionName());

        if (this.track.getTrackNumber()==0){
            trackNumber.setText("no track number");
        }
        else if(this.track.getTrackNumber() > this.track.getTrackCount()) {
            trackNumber.setText("bonus track");
        }else{
            trackNumber.setText("number " + this.track.getTrackNumber() + "/" + this.track.getTrackCount());
        }

        if (this.track.getTrackTimeMillis()==1){
            trackLength.setText("no time available");
        }
        else {
            double minutes = (this.track.getTrackTimeMillis() / 1000.0) / 60.0;   // ms to sec, sec to minutes
            double differenceSeconds = minutes - Math.floor(minutes);       // get the difference, value for seconds
            double seconds = (differenceSeconds*60.0);
            trackLength.setText("length    " + (int)minutes + ":" + (int)seconds);
        }
    }

    public static DetailsFragment newInstance(int title) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

	private void playMedia(final String url){

        player = new MediaPlayer();

        // fetch network audio via another thread (Don't want to network on UI thread)
        streamThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // set to stream music
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);

                // start playing from URL
                try {
                    player.setDataSource(url);
                    player.prepare();
                    player.start();
                }
                catch(IOException e) {
                    Log.e("DetailsFragment", "wasn't able to set preview URL");
                }
            }
        });
        streamThread.start();

        // reset when audio finishes
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                resetPreviewFragment();
                previewButton.setText("PLAY");
                isPlaying = false;
                
            }
        });


    }
	
    private void resetPreviewFragment(){

        previewButton.setText("PLAY");

        if (player != null) {
            player.release();
            player = null;
        }
        if (streamThread!=null)
            streamThread.interrupt();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        resetPreviewFragment();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (dialog != null) {
            dialog.dismiss();
            resetPreviewFragment();
        }
    }
}
