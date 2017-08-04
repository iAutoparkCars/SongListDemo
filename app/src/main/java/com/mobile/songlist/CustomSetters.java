package com.mobile.songlist;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
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

        if (url==null) {
            Log.d("CustomSetters imgUrl", "The URL was null. No Image set");
            return;
        }

        new DownloadSetImgTask(view).execute(url);
    }



    public static Bitmap fetchBitmap(URL url){
        Bitmap result = null;

        try {

            InputStream inputStream = url.openStream();
            result = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
