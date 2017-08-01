package com.mobile.songlist;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageButton;


public class CustomSetters {

    @BindingAdapter("imgSrc")
    public static void setImgSrc(ImageButton view, int resId)
    {
        view.setImageDrawable(view.getContext().getDrawable(resId));
        //  Here you can load the Drawable with Glide or any other library for better performance

    }

}
