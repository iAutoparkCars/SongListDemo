package com.mobile.songlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobile.songlist.R;
import com.mobile.songlist.databinding.SportDataBinding;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private String TAG = getClass().getName();
    private Context mContext;
    private ArrayList<SportViewModel> mList;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, ArrayList<SportViewModel> list)
    {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        SportDataBinding trackViewBinding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_item, parent,false);


        SportDataBinding dataBinding = SportDataBinding.inflate(inflater, parent, false);
        return new ViewHolder(trackViewBinding);
        //return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SportViewModel model = mList.get(position);

        holder.bind(model);
        final SportDataBinding dataBinding = holder.getDataBinding();
        dataBinding.setCell(new TrackDetails(){
            @Override
            public void onTrackViewClick(){
                Log.d(TAG, "track view clicked");
                Toast.makeText(mContext, "track view clicked",Toast.LENGTH_SHORT).show();
            }
        });

        /*dataBinding.setHandler(new FavImgClickHandler(mContext) {

            @Override
            public void OnFavImgClick() {
                if (model.imgSrcId.get() == R.drawable.star)
                {
                    model.imgSrcId.set(R.drawable.star_outline);
                    Toast.makeText(mContext, "Sport removed from favourites",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    model.imgSrcId.set(R.drawable.star);

                    Toast.makeText(mContext, "Sport added to favourites",Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "imgView clicked");
            }

            @Override
            public void onTrackViewClick(){
                Log.d(TAG, "track view clicked");
                Toast.makeText(mContext, "track view clicked",Toast.LENGTH_SHORT).show();
            }

        });*/

       /* dataBinding.setHandler(new FavImgClickHandler(mContext){
            @Override
            public void onTrackViewClick(){
                Log.d(TAG, "track view clicked");
                Toast.makeText(mContext, "track view clicked",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
