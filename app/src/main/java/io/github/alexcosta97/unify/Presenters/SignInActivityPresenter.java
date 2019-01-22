package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import io.github.alexcosta97.unify.MainMenu;
import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.RequestModels.LoginDetails;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.AppDatabase;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.SignInActivityView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivityPresenter {
    SignInActivityView view;
    Gson gson;
    Context context;

    public SignInActivityPresenter(SignInActivityView view, Context context){
        this.view = view;
        gson = new Gson();
        this.context = context;
    }

    public void login(String email, String password){
        final LoginDetails details = new LoginDetails();
        details.email = email;
        details.password = password;
        final APIClient client = ServiceGenerator.createService();

        Call<Authorization> call = client.login(details);
        call.enqueue(new Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, final Response<Authorization> response) {
                switch(response.code()){
                    case 200:
                        final AppDatabase db = AppDatabase.getDatabase(context);
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                db.authorizationDao().insertAuthorization(response.body());
                                view.launchNextActivity(MainMenu.class);
                            }
                        });
                        break;
                    default:
                        view.displayErrorDialog("Login Error", "Invalid Email or Password");
                        break;
                }
            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                view.displayErrorDialog("Error", "Something went wrong");
            }
        });
    }
}
