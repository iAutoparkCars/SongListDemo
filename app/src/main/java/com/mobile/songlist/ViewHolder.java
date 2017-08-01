package com.mobile.songlist;

import android.support.v7.widget.RecyclerView;

import com.mobile.songlist.databinding.SportDataBinding;



public class ViewHolder extends RecyclerView.ViewHolder {

    private SportDataBinding binding;

    public ViewHolder(SportDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.binding = dataBinding;
    }

    public void bind (SportViewModel model)
    {
        this.binding.setViewModel(model);
        //this.binding.setHandler(new FavImgClickHandler());

        //this.binding.executePendingBindings();
    }

    public SportDataBinding getDataBinding()
    {
        return binding;
    }

}
