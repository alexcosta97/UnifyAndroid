package io.github.alexcosta97.unify.Presenters;

import io.github.alexcosta97.unify.NewOrder;
import io.github.alexcosta97.unify.Reports;
import io.github.alexcosta97.unify.TemplateOrder;
import io.github.alexcosta97.unify.Views.MainMenuView;

public class MainMenuPresenter {
    MainMenuView view;

    public MainMenuPresenter(MainMenuView view){
        this.view = view;
    }

    public void newOrderClicked(){
        view.launchNextActivity(NewOrder.class);
    }

    public void templateOrderClicked(){
        view.launchNextActivity(TemplateOrder.class);
    }

    public void reportsClicked(){
        view.launchNextActivity(Reports.class);
    }
}
