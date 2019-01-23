package io.github.alexcosta97.unify.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Product;
import io.github.alexcosta97.unify.R;

public class ProductsAdapter extends ArrayAdapter<Product> {
    public ProductsAdapter(Context context, List<Product> products){
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_row_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.itemNameTextView);
        String productName = product.productName;
        name.setText(productName);

        return convertView;
    }
}
