package com.crazydeveloper.agro20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Dashboard_buyer extends AppCompatActivity {
    private Button logOutButton;
    private FirebaseAuth firebaseAuth;
    private TextView userDisplay;
    FirebaseFirestore fstore;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_buyer);

      //  getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userDisplay = findViewById(R.id.userName);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        phoneNumber = user.getPhoneNumber();
        //userDisplay.setText(user.getPhoneNumber());

        DocumentReference db= fstore.collection("users").document(phoneNumber);

        db.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                StringBuilder detail = new StringBuilder("");
                detail.append("Name : "+documentSnapshot.getString("fname")+documentSnapshot.getString("lname"));
                detail.append("\nEmail : "+documentSnapshot.getString("lname"));
                detail.append("\nPhone : "+phoneNumber);

                userDisplay.setText(detail.toString());
                Log.i("details : ",detail.toString());
            }
        });

        logOutButton = findViewById(R.id.sign_out_button);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(Dashboard_buyer.this,LoginActivity.class));
                finish();
            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}

