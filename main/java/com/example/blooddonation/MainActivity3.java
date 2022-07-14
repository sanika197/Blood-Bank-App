package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import android.os.Bundle;

import java.util.Date;
import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {

    DBHelper DB;
    Button bt1, bt2, bt3;
    EditText et1, et2, et3, et4, et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        bt1 = findViewById(R.id.buttonInsert);
        bt2 = findViewById(R.id.buttonDelete);
        bt3 = findViewById(R.id.buttonUpdate);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        DB = new DBHelper(this);

    }

    public void adminInsert(View view) {
        String idText = et1.getText().toString();
        String nameText = et2.getText().toString();
        String bloodgrpText = et3.getText().toString();
        String dateText = et4.getText().toString();
        String locText = et5.getText().toString();

        Boolean checkInsertion = DB.insertData(idText, nameText, bloodgrpText, dateText, locText);
        if(checkInsertion == true)
        {
            Toast.makeText(MainActivity3.this, "New entry inserted!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity3.this, "New entry not inserted!", Toast.LENGTH_SHORT).show();
        }
    }

    public void adminUpdate(View view) {
        String idText = et1.getText().toString();
        String nameText = et2.getText().toString();
        String bloodgrpText = et3.getText().toString();
        String dateText = et4.getText().toString();
        String locText = et5.getText().toString();

        Boolean checkUpdation = DB.updateData(idText, nameText, bloodgrpText, dateText, locText);
        if(checkUpdation == true)
        {
            Toast.makeText(MainActivity3.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity3.this, "Could not update!", Toast.LENGTH_SHORT).show();
        }
    }

    public void adminDelete(View view) {
        String id = et1.getText().toString();
        Boolean checkDeletion = DB.deleteData();

        if(checkDeletion == true)
        {
            Toast.makeText(MainActivity3.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity3.this, "Could not delete!", Toast.LENGTH_SHORT).show();
        }
    }


    public void adminView(View view) {
        Cursor res = DB.ViewData();
        if(res.getCount()==0)
        {
            Toast.makeText(MainActivity3.this, "No data exists!", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer sb = new StringBuffer();
        while(res.moveToNext())
        {
            sb.append("ID: "+res.getString(0)+"\n");
            sb.append("Name: "+res.getString(1)+"\n");
            sb.append("Blood Group: "+res.getString(2)+"\n");
            sb.append("Date: "+res.getString(3)+"\n");
            sb.append("Location: "+res.getString(4)+"\n"+"\n");

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
        builder.setCancelable(true);
        builder.setTitle("Matching Records: ");
        builder.setMessage(sb.toString());
        builder.show();
    }

    public void BackHome(View view) {
        Intent in = new Intent(MainActivity3.this, MainActivity1.class);
        startActivity(in);
    }

    public void onClear(View view) {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");

    }

}