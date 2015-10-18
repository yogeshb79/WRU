package com.kce.androlatlon;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity
{
    Button btnLinkToLogin,btnRegister;
    EditText inputEmail,inputPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     // create a instance of SQLite Database

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLinkToLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnLinkToRegisterScreen);





        // Set On ClickListener
       btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=inputEmail.getText().toString();
                String password=inputPassword.getText().toString();


                // fetch the Password form database for respective user name

                    Toast.makeText(LoginActivity.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,
                            HomeActivity.class);
                    intent.putExtra("userName",userName);
                    startActivity(intent);
                    finish();
            }
        });



    }

}