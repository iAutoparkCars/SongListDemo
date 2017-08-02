package com.mobile.songlist;



import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobile.songlist.databinding.SportDataBinding;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private String TAG = getClass().getName();
    private Context mContext;
    private FragmentManager fragmentmanager;
    private ArrayList<SportViewModel> mList;
    private LayoutInflater inflater;

    private DetailsFragment detailsFragment;

   /* public RecyclerViewAdapter(Context context, ArrayList<SportViewModel> list, FragmentManager fragmentManager)
    {
        this.mContext = context;
        this.mList = list;
    }*/

    public RecyclerViewAdapter(MainActivity context, ArrayList<SportViewModel> data) {
        this.mContext = context;
        this.mList = data;
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


    public int oldFragmentViewId = -1;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SportViewModel model = mList.get(position);

        //set fragment in clicked ViewHolder
        final int newContainerId = getUniqueId();
        holder.itemView.setId(newContainerId);// Set container id

        holder.bind(model);
        final SportDataBinding dataBinding = holder.getDataBinding();
        dataBinding.setCell(new TrackDetails(holder){
            @Override
            public void onTrackViewClick(){

                //get fragment manager
                detailsFragment = DetailsFragment.newInstance();
                MainActivity activity = (MainActivity)mContext;


                //delete old fragment
                Fragment oldFragment = activity.getFragmentManager().findFragmentById(oldFragmentViewId);
                if(oldFragment != null) {
                    activity.getFragmentManager().beginTransaction().remove(oldFragment).commit();
                }

                Log.d(TAG, "track view clicked");
                Toast.makeText(mContext, "track view clicked",Toast.LENGTH_SHORT).show();

               /* detailsFragment = DetailsFragment.newInstance();
                MainActivity activity = (MainActivity)mContext;*/
                activity.getFragmentManager().beginTransaction()
                        .replace(newContainerId, detailsFragment)
                        .addToBackStack("fragmentDetail")
                        .commit();

                oldFragmentViewId = newContainerId;
                //loadFragmentIntoRootView(detailsFragment, false, false);

               /* final FragmentTransaction fragmentTransaction = ((MainActivity)mContext).getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contentContainer, detailsFragment, TAG);
                fragmentTransaction.commit();*/


                /*DetailsFragment nextFrag = new DetailsFragment();
                MainActivity activity = (MainActivity)mContext;
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.track_cell, nextFrag)
                        .addToBackStack(null)
                        .commit();*/

                /*FragmentTransaction
                if(mContext instanceof MainActivity){
                    ((MainActivity)mContext).getFragmentManager().beginTransaction()
                            .replace(R.id.Layout_container, nextFrag,TAG_FRAGMENT)
                            .addToBackStack(null)
                            .commit();

                }*/
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

    public int getUniqueId(){
        return 111 + (int)(Math.random() * 9999);
    }


}
