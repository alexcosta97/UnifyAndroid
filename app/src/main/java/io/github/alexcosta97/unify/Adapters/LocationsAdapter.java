package io.github.alexcosta97.unify.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Location;
import io.github.alexcosta97.unify.R;

public class LocationsAdapter extends ArrayAdapter<Location> {
    public LocationsAdapter(Context context, List<Location> locations){
        super(context, 0, locations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Location location = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_row_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.itemNameTextView);
        String locationName = location.locationName;
        name.setText(locationName);

        return convertView;
    }
}
