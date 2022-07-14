package com.example.blooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class details_of_receiver extends AppCompatActivity {

    EditText t1;
    EditText email_r,contact_r;
    Spinner sl;
    Spinner sl2;
    //TextView v1;
    public String loc;
    public String Bldgrp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_receiver);

        t1=(EditText)findViewById(R.id.name);
        contact_r=findViewById(R.id.phone_r);
        email_r=findViewById(R.id.email_r);
        sl=(Spinner) findViewById(R.id.spinner1loc);
        sl2 = (Spinner)findViewById(R.id.spinner1BloodGrp);
        final String l []={"Kothrud","Paud Road","Warje","Hadapsar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, l);
        sl.setAdapter(adapter);
        sl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc = l[position];
                Toast.makeText(parent.getContext(),loc,Toast.LENGTH_LONG).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final String b []={"B+ve","B-ve","A+ve","A-ve","O+ve","O-ve","AB+ve","AB -ve"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, b);
        sl2.setAdapter(adapter2);
        sl2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bldgrp = b[position];
                Toast.makeText(parent.getContext(),Bldgrp,Toast.LENGTH_LONG).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    DatabaseHandler db = new DatabaseHandler(this);

    public  void ADD_RECIEVER(View view ){
        boolean isvalid = true;
        if(t1.getText().toString().isEmpty()&&email_r.getText().toString().isEmpty()&&contact_r.getText().toString().isEmpty()){
            //inputLayoutName.setError("Full name is mandatory");
            Toast.makeText(details_of_receiver.this, "Enter all the details", Toast.LENGTH_LONG).show();
            isvalid = false;
        }

        String name1 =  t1.getText().toString();
        String contact = contact_r.getText().toString();
        String email=email_r.getText().toString();
        if(isvalid == true) {
            if (db.addreciever(new Recievers(name1, Bldgrp, loc,email,contact)) == true) {
                Toast.makeText(details_of_receiver.this, " RData Inserted", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(details_of_receiver.this, "RData not Inserted", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void viewalldataOfReciever(View view) {
        Cursor res = db.getALLdataOfReciever();
        if (res.getCount() == 0){
            showmsg("Error","Nothing Found");
            return;
        }
        StringBuffer s = new StringBuffer();
        while(res.moveToNext()){
            s.append("ID" + res.getString(0)+"\n");
            s.append("name"+res.getString(1)+"\n");
            s.append(("Blood_group"+ res.getString(4)+"\n"));
            s.append(("Location"+ res.getString(5)+"\n"));
            s.append(("Email: "+ res.getString(2)+"\n"));
            s.append(("Contact Number: "+ res.getString(3)+"\n\n\n"));

        }
        showmsg("Data",s.toString());
    }

    public void FIND_DONOR(View view){

        Cursor res = db.FindData(Bldgrp);
        if (res.getCount() == 0) {
            showmsg("Sorry!!", "No Donors Found. Please check later");
            return;
        }
        StringBuffer s = new StringBuffer();
        while (res.moveToNext()) {
            s.append("ID" + res.getString(0)+"\n");
            s.append("name"+res.getString(1)+"\n");
            s.append(("Blood_group"+ res.getString(4)+"\n"));
            s.append(("Location"+ res.getString(5)+"\n"));
            s.append(("Email: "+ res.getString(2)+"\n"));
            s.append(("Contact Number: "+ res.getString(3)+"\n\n\n"));

        }
        String str = "No of donors available" + res.getCount();
        showmsg(str, s.toString());
        Toast.makeText(details_of_receiver.this, "Data Found", Toast.LENGTH_LONG).show();

    }
    public void FIND_DONOR_WITH_LOC_AND_BLOODGROUP(View view){

        Cursor res = db.FindDatawithLoc(Bldgrp, loc);
        if (res.getCount() == 0) {
            showmsg("Sorry!!", "No Donors Found. Please check later");
            return;
        }
        StringBuffer s = new StringBuffer();
        while (res.moveToNext()) {
            s.append("ID" + res.getString(0)+"\n");
            s.append("name"+res.getString(1)+"\n");
            s.append(("Blood_group"+ res.getString(4)+"\n"));
            s.append(("Location"+ res.getString(5)+"\n"));
            s.append(("Email: "+ res.getString(2)+"\n"));
            s.append(("Contact Number: "+ res.getString(3)+"\n\n\n"));

        }
        String str = "No of donors available" + res.getCount();
        showmsg(str, s.toString());
        Toast.makeText(details_of_receiver.this, "Data Found", Toast.LENGTH_LONG).show();
    }
    public void showmsg(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
    public void SendMail(View v)
    {
        Intent intent=new Intent(details_of_receiver.this,Mail.class);
        startActivity(intent);
    }
}