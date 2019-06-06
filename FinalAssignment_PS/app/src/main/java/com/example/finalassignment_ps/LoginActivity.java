package com.example.finalassignment_ps;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button register, login;
    DatabaseConfig myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DatabaseConfig(this);
        username = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.pass);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(username.getText().toString(),
                        password.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(LoginActivity.this, "Successfully registered. You can login now!", Toast.LENGTH_LONG).show();
                    // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    // startActivity(intent);
                } else
                    Toast.makeText(LoginActivity.this, "Failed to register", Toast.LENGTH_LONG).show();

            }
        });
//to check the data in the database use the code below
        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Username :"+ res.getString(1)+"\n");
                    buffer.append("Password :"+ res.getString(2)+"\n");
                }
                // Show all data
                showMessage("Data",buffer.toString());
            }
        });
    }*/


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = myDb.getUserData(username.getText().toString());
                if (res == 0) {
                    showMessage("Error", "User not found");
                    return;
                }else{
                    showMessage("Error","User found");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

    private void showMessage(String error, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(error);
        builder.setMessage(Message);
        builder.show();

    }
}
