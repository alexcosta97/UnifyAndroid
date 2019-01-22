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

import io.github.alexcosta97.unify.Models.Database.Supplier;
import io.github.alexcosta97.unify.Presenters.ListSuppliersPresenter;
import io.github.alexcosta97.unify.R;

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.SuppliersViewHolder> {
    private List<Supplier> mDataset;

    public static class SuppliersViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public SuppliersViewHolder(TextView v){
            super(v);
            mTextView = v;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    ListView modeListView = new ListView(v.getContext());
                    String[] modes = new String[] {"View Details", "Edit Supplier", "Delete Supplier"};
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
                                ListSuppliersPresenter.itemClicked(getAdapterPosition());
                            }
                            else if(position == 1){
                                ListSuppliersPresenter.editItem(getAdapterPosition());
                            }
                            //delete reminder
                            else{
                                ListSuppliersPresenter.deleteItem(getAdapterPosition());
                            }
                            dialog.dismiss();
                        }
                    });
                }
            });
        }
    }

    public SuppliersAdapter(List<Supplier> dataset){
        mDataset = dataset;
    }

    //Creating new views

    @NonNull
    @Override
    public SuppliersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //Create a new view
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);

        SuppliersViewHolder vh = new SuppliersViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SuppliersViewHolder holder, int position) {
        String name = mDataset.get(position).supplierName;
        holder.mTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
