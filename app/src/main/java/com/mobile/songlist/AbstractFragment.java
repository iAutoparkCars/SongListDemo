package com.mobile.songlist;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class AbstractFragment extends Fragment {

    private static final String TAG = "AbstractFragment";

    private Context context;
    protected View rootView;

    /* Google wants us to stop using deprecated APIs yet uses them... */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        try {
            this.context = activity;
        } catch (final ClassCastException exception) {
            throw new ClassCastException(activity.toString() + " cannot be cast to a Context.  If you're seeing this, something is seriously wrong!");
        }
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        this.context = context;
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }*/
    }


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}