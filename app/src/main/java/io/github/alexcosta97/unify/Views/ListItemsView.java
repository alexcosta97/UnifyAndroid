package io.github.alexcosta97.unify.Views;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.User;

public interface ListItemsView {
    void launchNextActivity(Class activity);
    void launchNextActivity(Class activity, int itemId);
}
