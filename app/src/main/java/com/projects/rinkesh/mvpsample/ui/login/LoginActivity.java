package com.projects.rinkesh.mvpsample.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projects.rinkesh.mvpsample.Data.DataManager;
import com.projects.rinkesh.mvpsample.MvpApp;
import com.projects.rinkesh.mvpsample.R;

import com.projects.rinkesh.mvpsample.utils.CommonUtils;
import com.projects.rinkesh.mvpsample.ui.base.BaseActivity;
import com.projects.rinkesh.mvpsample.ui.main.MainActivity;

/**
 * Created by Rinkesh on 04/12/17.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView {

    LoginPresenter loginPresenter;

    EditText editTextEmail, editTextPassword;

    Button button;


    public static Intent getStartIntent(Context context) {

        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        loginPresenter = new LoginPresenter(dataManager);

        loginPresenter.onAttach(this);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick();
            }
        });
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginButtonClick() {

        String emailId = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!CommonUtils.isEmailValid(emailId)) {
            Toast.makeText(this, "Enter correct Email", Toast.LENGTH_LONG).show();
            return;
        }

        if (password == null || password.isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show();
            return;
        }

        loginPresenter.startLogin(emailId);

    }



}
