package io.github.alexcosta97.unify.Presenters;

import io.github.alexcosta97.unify.MainMenu;
import io.github.alexcosta97.unify.Models.LoginDetails;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.SignInActivityView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivityPresenter {
    SignInActivityView view;

    public SignInActivityPresenter(SignInActivityView view){
        this.view = view;
    }

    public void login(String email, String password){
        final LoginDetails details = new LoginDetails();
        details.email = email;
        details.password = password;
        APIClient client = ServiceGenerator.createService();

        Call<ResponseBody> call = client.login(details);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                switch(response.code()){
                    case 200:
                        view.launchNextActivity(MainMenu.class);
                        break;
                    default:
                        view.displayErrorDialog("Login Error", "Invalid Email or Password");
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.displayErrorDialog("Error", "Something went wrong");
            }
        });
    }
}
