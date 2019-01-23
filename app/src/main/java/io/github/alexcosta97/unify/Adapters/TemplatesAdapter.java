package io.github.alexcosta97.unify.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Template;
import io.github.alexcosta97.unify.R;

public class TemplatesAdapter extends ArrayAdapter<Template> {
    public TemplatesAdapter(Context context, List<Template> templates){
        super(context, 0, templates);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Template template = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_row_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.itemNameTextView);
        String templateName = template.templateName;
        name.setText(templateName);

        return convertView;
    }
}
