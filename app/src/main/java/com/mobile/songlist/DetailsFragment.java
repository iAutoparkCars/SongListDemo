package com.mobile.songlist;



import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Steven on 8/1/2017.
 */

//CONSIDER EXTENDING DIALOG FRAGMENT

public class DetailsFragment extends DialogFragment {

    private View viewContent;
    int info;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get info from the Activity that created this fragment
        info = getArguments().getInt("title");
        //setStyle(DialogFragment.STYLE_NORMAL, android.R.styl.);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //super.onCreateView(inflater, container,savedInstanceState);
        View currentView = inflater.inflate(R.layout.fragment_detail, container, false);

        // use currentView.findViewById to setWhatever ,setOnWhateverClick listener in this dialog fragment

       viewContent = currentView.findViewById(R.id.fullscreen_content);

        currentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //currentView.dispatchWindowSystemUiVisiblityChanged();
        return currentView;
    }

    public static DetailsFragment newInstance(int title) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

}
