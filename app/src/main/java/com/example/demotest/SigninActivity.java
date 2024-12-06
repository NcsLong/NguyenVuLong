package com.example.demotest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demotest.database.UserDB;
import com.example.demotest.model.UserModel;

public class SigninActivity extends AppCompatActivity {
    UserDB userDB;
    UserModel userModel;
    EditText emailInput, passwordInput;
    Button signInButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_signin_layout);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        signInButton = findViewById(R.id.signInButton);

        userDB = new UserDB(SigninActivity.this);
        checkLoginWithDatabase();

    }

    private void checkLoginWithDatabase() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String pass = passwordInput.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    emailInput.setError("Email can be not empty");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    passwordInput.setError("Password can be not empty");
                    return;
                }
                userModel = userDB.getInfoUser(email, pass); // lay du lieu tu database
                assert userModel != null;
                if (userModel.getEmail() != null) {
                    // dang nhap thanh cong
                    Intent intent = new Intent(SigninActivity.this, MenuActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME_ACCOUNT", email);
                    bundle.putInt("ID_ACCOUNT", userModel.getId());
                    intent.putExtras(bundle);
                    startActivity(intent); // chuyen sang HomeActivity
                    finish();
                } else {
                    // dang nhap that bai
                    Toast.makeText(SigninActivity.this, "Account invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
