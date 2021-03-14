package com.crazydeveloper.agro20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText phone;
    Button submitButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getSupportActionBar().hide();
        phone = findViewById(R.id.textPhone);

        progressDialog = new ProgressDialog(LoginActivity.this);

        submitButton = findViewById(R.id.login_button_buyer);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String phoneNumber = phone.getText().toString().trim();

                if(phoneNumber.isEmpty() || phoneNumber.length()!=13)
                {
                    progressDialog.hide();
                    phone.setError("Invalid phone number");
                    phone.requestFocus();
                    return;
                }else {
                    Log.i("Phone", phoneNumber);
                    Intent intent = new Intent(LoginActivity.this, VerifyPhoneActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);
                    progressDialog.hide();
                }
            }
        });
    }
    // to hide keyboard on touch
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}

