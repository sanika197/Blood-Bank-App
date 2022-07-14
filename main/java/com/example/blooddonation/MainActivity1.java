package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
public class MainActivity1 extends AppCompatActivity {

    Button expStock, expAdmin, expInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        expStock = findViewById(R.id.stock);
        expStock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent in = new Intent(v.getContext(), MainActivity2.class);
                startActivity(in);
            }
        });

        expAdmin = findViewById(R.id.admin);
        expAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent in = new Intent(v.getContext(), MainActivity5.class);
                startActivity(in);
            }
        });

    }

    public void onInfo(View view) {
        Intent in = new Intent(MainActivity1.this, MainActivity4.class);
        startActivity(in);
    }
    }
