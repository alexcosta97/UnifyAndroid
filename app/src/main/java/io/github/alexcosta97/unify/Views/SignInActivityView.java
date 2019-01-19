package io.github.alexcosta97.unify.Views;

import android.app.AlertDialog;

public interface SignInActivityView {
    void launchNextActivity(Class nextActivity);
    AlertDialog displayErrorDialog(String title, String message);
}
