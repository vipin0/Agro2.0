package com.crazydeveloper.agro20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private EditText fname,lname,street,cityOrTown,pincode,state,landmark,phone,address;
    private Button registerButton;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String userType;

    String phoneStr;

    FirebaseFirestore fstore;
    void register()
    {
        fname = findViewById(R.id.first_name);
        lname = findViewById(R.id.last_name);
        street = findViewById(R.id.street_name);
        cityOrTown = findViewById(R.id.cityortown);
        pincode = findViewById(R.id.pincode);
        state = findViewById(R.id.state_name);
        landmark = findViewById(R.id.landmark);
        address =findViewById(R.id.address);

        String fnameStr = fname.getText().toString().trim();
        String lnameStr = lname.getText().toString().trim();
        String streetNameStr = street.getText().toString().trim();
        String cityOrTownStr = cityOrTown.getText().toString().trim();
        String pincodeStr =  pincode.getText().toString().trim();
        String stateNameStr = state.getText().toString().trim();
        String landmarkStr = landmark.getText().toString().trim();
        String addressStr = address.getText().toString().trim();

        if(fnameStr==null || fnameStr.length()==0)
        {
            fname.setError("Invalid first name");
            fname.requestFocus();
            return;
        }
        if(lnameStr==null || lnameStr.length()==0)
        {
            lname.setError("Invalid Input");
            lname.requestFocus();
            return;
        }
        if(streetNameStr==null || streetNameStr.length()==0)
        {
            street.setError("Invalid Input");
            street.requestFocus();
            return;
        }
        if(cityOrTownStr==null || cityOrTownStr.length()==0)
        {
            cityOrTown.setError("Invalid Input");
            cityOrTown.requestFocus();
            return;
        }

        if(pincodeStr==null || pincodeStr.length()!=6)
        {
            pincode.setError("Invalid Input");
            pincode.requestFocus();
            return;
        }
        if(stateNameStr==null || stateNameStr.length()==0)
        {
            state.setError("Invalid Input");
            state.requestFocus();
            return;
        }
        if(landmarkStr==null || landmarkStr.length()==0)
        {
            landmark.setError("Invalid Input");
            lname.requestFocus();
            return;
        }
        if(addressStr==null || addressStr.length()==0){
            address.setError("Invalid Address");
            address.requestFocus();
            return;
        }

        User user = new User(fnameStr,lnameStr,streetNameStr,cityOrTownStr,pincodeStr,stateNameStr,landmarkStr,addressStr);
        //Toast.makeText(this, user.userType, Toast.LENGTH_SHORT).show();

        CollectionReference collectionReference = fstore.collection("users");
        collectionReference.document(phoneStr).set(user).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,MainActivity123.class);
                        startActivity(intent);
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //getSupportActionBar().hide();

        fstore = FirebaseFirestore.getInstance();

        phone = findViewById(R.id.textPhone);
        phoneStr = getIntent().getStringExtra("phone");
        phone.setText(phoneStr);
        phone.setEnabled(false);

        //radioGroup = findViewById(R.id.user_type);
        userType="Farmer";
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
////        {
////            @Override
////            public void onCheckedChanged(RadioGroup group, int checkedId) {
////                radioButton = group.findViewById(checkedId);
////                userType = radioButton.getText().toString().trim();
////
////                Toast.makeText(RegisterActivity.this, "user"+userType, Toast.LENGTH_SHORT).show();
////            }
////        });

        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
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
