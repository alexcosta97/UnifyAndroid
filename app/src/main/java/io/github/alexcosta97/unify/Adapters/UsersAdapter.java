package io.github.alexcosta97.unify.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.User;
import io.github.alexcosta97.unify.R;

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, List<User> users){
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        User user = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_row_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.itemNameTextView);
        String userName = user.firstName + " " + user.lastName;
        name.setText(userName);

        return convertView;
    }
}
