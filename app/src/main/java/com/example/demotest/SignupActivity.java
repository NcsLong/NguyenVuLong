package com.example.demotest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demotest.database.UserDB;

public class SignupActivity extends AppCompatActivity {
    UserDB userDB;
    EditText nameInput, emailInput, passwordInput, confirmPasswordInput;
    Button signUpButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_signup_layout);
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        signUpButton = findViewById(R.id.signUpButton);
        userDB = new UserDB(SignupActivity.this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String confirmpassword = confirmPasswordInput.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    nameInput.setError("Name can not empty");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    emailInput.setError("Email can not empty");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    passwordInput.setError("Password can not empty");
                    return;
                }
                if (!password.equals(confirmpassword)){
                    confirmPasswordInput.setError("Password can not match");
                    return;
                }
                boolean checkUsername = userDB.checkUsernameEmail(name, 1);
                boolean checkEmail    = userDB.checkUsernameEmail(email, 2);
                if (checkUsername){
                    Toast.makeText(SignupActivity.this, "Username Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (checkEmail){
                    Toast.makeText(SignupActivity.this, "Email Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                long insert = userDB.addNewUser(name, password, email);
                if (insert == -1){
                    Toast.makeText(SignupActivity.this, "signup Fail", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "signup successfully", Toast.LENGTH_SHORT).show();
                    // quay ve man hinh dang nhap
                    Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
