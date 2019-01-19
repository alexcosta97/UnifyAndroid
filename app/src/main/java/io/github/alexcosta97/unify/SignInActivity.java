package io.github.alexcosta97.unify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.github.alexcosta97.unify.Presenters.SignInActivityPresenter;
import io.github.alexcosta97.unify.Views.SignInActivityView;

public class SignInActivity extends AppCompatActivity implements SignInActivityView {
    EditText emailEditText;
    EditText passwordEditText;
    Button signInButton;
    SignInActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        presenter = new SignInActivityPresenter(this);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    public void launchNextActivity(Class newActivity){
        Intent intent = new Intent(SignInActivity.this, newActivity);
        startActivity(intent);
    }

    public AlertDialog displayErrorDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                emailEditText.setText("");
                passwordEditText.setText("");
            }
        });

        AlertDialog errorDialog = builder.create();
        errorDialog.show();

        return errorDialog;
    }
}
