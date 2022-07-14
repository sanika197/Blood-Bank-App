package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity5 extends AppCompatActivity {

    Button submit;
    EditText passw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        submit = findViewById(R.id.buttonSubmit);
        passw = findViewById(R.id.pass);

    }

    public void onSubmit(View view) {
        String password = passw.getText().toString();
        if(password.equals("admin123")) {
            Intent in = new Intent(MainActivity5.this, MainActivity3.class);
            startActivity(in);
        }
        else
        {
            Toast t;
            t = Toast.makeText(this, "Wrong Password!", Toast.LENGTH_SHORT);
            t.show();
        }

    }
}