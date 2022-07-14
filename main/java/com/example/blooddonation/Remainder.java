package com.example.blooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Remainder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

    }
    public void showmsg(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
    public void FindRemTime(View view)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        Cursor res = db.dateDiff();
        if (res.getCount() == 0) {
            showmsg("Sorry!!", "No Donors Found. Please check later");
            return;
        }
        StringBuffer s = new StringBuffer();
        while (res.moveToNext()) {
            s.append("name"+res.getString(0)+"\n");
            String dt = res.getString(1);
            int dt1 = Integer.parseInt(dt);
            dt1 = dt1 + 90;
            s.append(res.getString(0)+" can donate after " +dt1+" days\n");
        }
        String str = "No of donors available" + res.getCount();
        showmsg(str, s.toString());
        Toast.makeText(Remainder.this, "Data Found", Toast.LENGTH_LONG).show();
    }
}