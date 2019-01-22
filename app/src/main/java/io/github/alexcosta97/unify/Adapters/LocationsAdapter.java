package io.github.alexcosta97.unify.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Location;
import io.github.alexcosta97.unify.Presenters.ListLocationsPresenter;
import io.github.alexcosta97.unify.R;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder> {
    private List<Location> mDataset;

    public static class LocationsViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public LocationsViewHolder(TextView v){
            super(v);
            mTextView = v;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    ListView modeListView = new ListView(v.getContext());
                    String[] modes = new String[] {"View Details", "Edit Location", "Delete Location"};
                    ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(v.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, modes);
                    modeListView.setAdapter(modeAdapter);
                    builder.setView(modeListView);
                    final Dialog dialog = builder.create();
                    dialog.show();
                    modeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //edit reminder
                            if(position == 0){
                                ListLocationsPresenter.itemClicked(getAdapterPosition());
                            }
                            else if(position == 1){
                                ListLocationsPresenter.editItem(getAdapterPosition());
                            }
                            //delete reminder
                            else{
                                ListLocationsPresenter.deleteItem(getAdapterPosition());
                            }
                            dialog.dismiss();
                        }
                    });
                }
            });
        }
    }

    public LocationsAdapter(List<Location> dataset){
        mDataset = dataset;
    }

    //Creating new views

    @NonNull
    @Override
    public LocationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //Create a new view
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);

        LocationsViewHolder vh = new LocationsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationsViewHolder holder, int position) {
        String name = mDataset.get(position).locationName;
        holder.mTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}