package com.mobile.songlist;

import android.databinding.BindingAdapter;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.net.URL;


public class CustomSetters {

    @BindingAdapter("imgSrc")
    public static void setImgSrc(ImageView view, int resId)
    {
        //Consider running the async task to set the image here
        //Find out how to use this function.
        Log.d("CustomSetter", "Called setImgSrc");
        view.setImageDrawable(view.getContext().getDrawable(resId));

        /*
            for variable name=track, with type=Track

            <ImageView
            app:imageUrl="@{track.artworkUrl100}"
            app:error="@{@drawable/venueError}"/>

        Here you can load the Drawable with Glide or any other library for better performance*/

    }

    @BindingAdapter("imgUrl")
    public static void loadImage(ImageView view, URL url) {
        Log.d("CustomSetter", "trying to image with url " + url);
        //Picasso.with(view.getContext()).load(url).error(error).into(view);
    }
}
