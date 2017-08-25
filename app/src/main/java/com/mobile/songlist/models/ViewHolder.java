package com.mobile.songlist.models;

import android.support.v7.widget.RecyclerView;

import com.mobile.songlist.databinding.SportDataBinding;
import com.mobile.songlist.viewmodels.Track;

// this class is a View; it uses the data binding

public class ViewHolder extends RecyclerView.ViewHolder {

    private SportDataBinding binding;
    public int containerId = 0;

    public ViewHolder(SportDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.binding = dataBinding;
    }

    public void bind (Track model)
    {
        /*
            bind method is very strange.
            When you add a new "variable" in the XML, with name "track",
            binding is able to somehow setTrack to set the variable named "track"
        */

        this.binding.setTrack(model);

        //this.binding.setHandler(new FavImgClickHandler());

        //this.binding.executePendingBindings();
    }

    public SportDataBinding getDataBinding()
    {
        return binding;
    }

}
