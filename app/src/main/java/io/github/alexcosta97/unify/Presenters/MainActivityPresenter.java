package io.github.alexcosta97.unify.Presenters;

import io.github.alexcosta97.unify.AddCompanyActivity;
import io.github.alexcosta97.unify.SignInActivity;
import io.github.alexcosta97.unify.Views.MainActivityView;

public class MainActivityPresenter {
    MainActivityView view;

    public MainActivityPresenter(MainActivityView view){
        this.view = view;
    }

    public void signInButtonPressed(){
        view.launchNextActivity(SignInActivity.class);
    }

    public void signUpButtonPressed(){
        view.launchNextActivity(AddCompanyActivity.class);
    }
}
