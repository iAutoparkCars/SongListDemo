package com.mobile.songlist;

import android.support.v7.widget.RecyclerView;

import com.mobile.songlist.databinding.SportDataBinding;



public class ViewHolder extends RecyclerView.ViewHolder {

    private SportDataBinding mDataBinding;

    public ViewHolder(SportDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.mDataBinding = dataBinding;
    }

    public void bind (SportViewModel model)
    {
        this.mDataBinding.setViewModel(model);
    }

    public SportDataBinding getDataBinding()
    {
        return mDataBinding;
    }

}
