package io.github.alexcosta97.unify.Services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.github.alexcosta97.unify.R;

public class Network {
    public static boolean isAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo network = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(network != null && network.isConnected()){
            isAvailable = true;
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Network Error");
            builder.setMessage("Due to internet connectivity issues, you won't be able to use the full functionality of the app.");
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Just dismiss the alertDialog
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();
        }

        return isAvailable;
    }
}
