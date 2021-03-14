package com.crazydeveloper.agro20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity123 extends AppCompatActivity {

    private ImageView getProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main123);
        getProfile = findViewById(R.id.get_profile);
        getProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity123.this,ProfileActivity.class);
                startActivity(intent);
            }

        });
    }



    public void chooseOption(View view) {

        String tag = view.getTag().toString().trim();
        if(tag.equals("buyProduct")){
            Intent intent = new Intent();//fill intent
            startActivity(intent);
        }
        if(tag.equals("sellProduct")){
            Intent intent = new Intent();//fill intent
            startActivity(intent);
        }
        if(tag.equals("my_product")){
            Intent intent = new Intent();//fill intent
            startActivity(intent);
        }
        if(tag.equals("transections")){
            Intent intent = new Intent();//fill intent
            startActivity(intent);
        }
        if(tag.equals("tutorials")){
            Intent intent = new Intent();//fill intent
            startActivity(intent);
        }
        if(tag.equals("schemes")){
            Intent intent = new Intent();//fill intent
            startActivity(intent);
        }

    }
}
