package io.github.alexcosta97.unify.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Subcategory;
import io.github.alexcosta97.unify.R;

public class SubcategoriesAdapter extends ArrayAdapter<Subcategory> {
    public SubcategoriesAdapter(Context context, List<Subcategory> subcategories){
        super(context, 0, subcategories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Subcategory subcategory = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_row_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.itemNameTextView);
        String subcategoryName = subcategory.subcategoryName;
        name.setText(subcategoryName);

        return convertView;
    }
}