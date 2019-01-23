package io.github.alexcosta97.unify.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Supplier;
import io.github.alexcosta97.unify.R;

public class SuppliersAdapter extends ArrayAdapter<Supplier> {
    public SuppliersAdapter(Context context, List<Supplier> suppliers){
        super(context, 0, suppliers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Supplier supplier = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_row_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.itemNameTextView);
        String supplierName = supplier.supplierName;
        name.setText(supplierName);

        return convertView;
    }
}