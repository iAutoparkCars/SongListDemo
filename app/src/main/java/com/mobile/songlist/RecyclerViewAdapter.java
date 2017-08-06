package com.mobile.songlist;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
    private ArrayList<Track> mList;
    private LayoutInflater inflater;

    private DetailsFragment detailsFragment;

   /* public RecyclerViewAdapter(Context context, ArrayList<Track> list, FragmentManager fragmentManager)
    {
        this.mContext = context;
        this.mList = list;
    }*/

    public RecyclerViewAdapter(MainActivity context, ArrayList<Track> data) {
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

        // used to remove the old fragment, so only one fragment can be made at once
    public int oldFragmentViewId = -1;

        /*setData in this ViewHolder. In this case, bind(data)
        *
        *   SHould I do binding asynchronously here? Because here
        *   is where I'm supposed to set values, ie. set the image
        *
        *
        * */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Track model = mList.get(position);

        //set fragment in clicked ViewHolder
        final int newContainerId = getUniqueId();
        holder.itemView.setId(newContainerId);// Set container id

        holder.bind(model);
        final SportDataBinding dataBinding = holder.getDataBinding();
        dataBinding.setCell(new TrackDetails(holder){
            @Override
            public void onTrackViewClick(){

                // pass url to fragment
                showDialog(model);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public int getUniqueId(){
        return 111 + (int)(Math.random() * 9999);
    }

    void showDialog(Track track){

	
        MainActivity activity = (MainActivity)mContext;
        /*   support.v4.app.FragmentManager is for AppCompatActivity
        *    android.app.FragmentManager is for Activity
        *
        *    getSupportFragmentManager() for AppCompatActivity;
         *    getFragmentManager() for Activity
        * */
		
		
		
        //get fragment manager
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

		// Pass selected Track's details to fragment using ArrayList
        ArrayList<Track> temp = new ArrayList<Track>();
        temp.add(track);

        DataPasser.getInstance().save("trackData", temp);

		Bundle bundle = new Bundle();
        bundle.putString("previewURL", track.getPreviewURL().toString());

        //remove an old fragment/currently open fragment if it were started
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            transaction.remove(prev);
        }

        //allows fragment to be closed on back pressed
        transaction.addToBackStack(null);

        //start a new dialog fragment
        DialogFragment newFragment = DetailsFragment.newInstance(R.string.app_name);
		newFragment.setArguments(bundle);
        newFragment.show(fragmentManager, "dialog");

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
       /* FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = DetailsFragment.newInstance(mStackLevel);
        newFragment.show(ft, "dialog");*/
    }
}
