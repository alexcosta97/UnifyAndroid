package io.github.alexcosta97.unify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.github.alexcosta97.unify.Presenters.MainActivityPresenter;
import io.github.alexcosta97.unify.Views.MainActivityView;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    Button signInButton;
    Button signUpButton;
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);

        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signInButtonPressed();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signUpButtonPressed();
            }
        });
    }

    public void launchNextActivity(Class newActivity){
        Intent intent = new Intent(MainActivity.this, newActivity);
        startActivity(intent);
    }
}
