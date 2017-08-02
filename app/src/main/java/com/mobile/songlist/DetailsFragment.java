package com.mobile.songlist;



import android.os.Bundle;

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

public class DetailsFragment extends AbstractFragment {

    private View viewContent;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        container.requestFocus();
        //super.onCreateView(inflater, container,savedInstanceState);
        View currentView = inflater.inflate(R.layout.activity_welcome, container, false);
        //TextView detail1 = (TextView) rootView.findViewById(R.id.detail1);
        //TextView detail2 = (TextView) rootView.findViewById(R.id.detail2);

       viewContent = currentView.findViewById(R.id.fullscreen_content);

        currentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //currentView.dispatchWindowSystemUiVisiblityChanged();
        currentView.requestFocus();
        currentView.requestFocusFromTouch();
        return currentView;
    }

    public static DetailsFragment newInstance() {
        final Bundle args = new Bundle();
        final DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
