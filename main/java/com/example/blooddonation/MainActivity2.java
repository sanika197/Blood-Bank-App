package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    Spinner s1, s2;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        DB = new DBHelper(this);

    }

    public void checkAvail(View view)
    {
        String grp = (String) s1.getSelectedItem();
        String loc = (String) s2.getSelectedItem();
        display(grp,loc);
    }

    public void display(String grp, String loc)
    {
        Cursor res = DB.checkAvailability(grp, loc);
        if(res.getCount()==0)
        {
            Toast.makeText(MainActivity2.this, "No matching data found!", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("Total: "+res.getCount()+"\n"+"\n");

        while(res.moveToNext())
        {
            sb.append("ID: "+res.getString(0)+"\n");
            sb.append("Name: "+res.getString(1)+"\n");
            sb.append("Blood Group: "+res.getString(2)+"\n");
            sb.append("Date: "+res.getString(3)+"\n"+"\n");
            sb.append("Location: "+res.getString(4)+"\n"+"\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setCancelable(true);
        builder.setTitle("Matching Records: ");
        builder.setMessage(sb.toString());
        builder.show();

    }

}