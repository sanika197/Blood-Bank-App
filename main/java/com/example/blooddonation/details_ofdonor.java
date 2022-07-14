package com.example.blooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class details_ofdonor extends AppCompatActivity {

    EditText t3;
    EditText t1;
    EditText t2;
    EditText email_d,contact_d;
    TextView v1;
    Spinner sl;
    Spinner sl2;
    public String loc;
    public String Bldgrp;
    public boolean hbcheckedflag = false;
    public boolean agecheckedflag = false;
    public boolean validcheckedflag = false;
    public boolean wtcheckedflag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_ofdonor);

        t1=(EditText)findViewById(R.id.std_name);
        sl=(Spinner) findViewById(R.id.spinner1loc);
        sl2 = (Spinner)findViewById(R.id.spinner1BloodGrp);
        email_d=(EditText) findViewById(R.id.email_d);
        contact_d=(EditText) findViewById(R.id.phone_d);
        final String l []={"Kothrud","Paud Road","Warje","Hadapsar","Karvenagar","Baner","Pimpri Chinchwad","Aundh","Wagholi","Akurdi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, l);
        sl.setAdapter(adapter);
        sl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc = l[position];
                //Toast.makeText(parent.getContext(),loc,Toast.LENGTH_LONG).show();
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
                //Toast.makeText(parent.getContext(),Bldgrp,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    DatabaseHandler db = new DatabaseHandler(this);

    public  void ADD_DONOR(View view ){
        boolean isvalid = true;
        if(t1.getText().toString().isEmpty()){
            isvalid = false;
        }

        String name =  t1.getText().toString();
        if( hbcheckedflag == true && agecheckedflag == true && validcheckedflag== true && wtcheckedflag == true) {
            if(isvalid == false){
                Toast.makeText(details_ofdonor.this, "Full Name is Mandatory", Toast.LENGTH_LONG).show();
            }
            else {
                if (db.adddonor(new Donors(name, Bldgrp, loc, email_d.getText().toString(), contact_d.getText().toString())) == true) {
                    Toast.makeText(details_ofdonor.this, "You are now registered donor.", Toast.LENGTH_LONG).show();
                    hbcheckedflag = false;
                    agecheckedflag = false;
                    validcheckedflag = false;
                    wtcheckedflag = false;
                } else {
                    Toast.makeText(details_ofdonor.this, "Sorry!! Not Registered", Toast.LENGTH_LONG).show();
                }
            }
        }
        else{
            Toast.makeText(details_ofdonor.this, "ALL CONDITIONS MUST BE ACCEPTED", Toast.LENGTH_LONG).show();
        }
    }

    public void viewalldata(View view) {
        Cursor res = db.getALLdata();
        if (res.getCount() == 0){
            showmsg("Error","Nothing Found");
            return;
        }
        StringBuffer s = new StringBuffer();
        while(res.moveToNext()){
            s.append("ID:" + res.getString(0)+"\n");
            s.append("name:"+res.getString(1)+"\n");
            s.append(("Blood_group: "+ res.getString(4)+"\n"));
            s.append(("Location: "+ res.getString(6)+"\n"));
            s.append(("Contact Details:" + res.getString(3) + "\n"));
            s.append("Date Of Donation"+res.getString(5)+"\n");
            s.append(("Email:" + res.getString(2) + "\n\n\n"));

        }
        showmsg("Data",s.toString());
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.hb:

                if(checked) {
                    // Remove the meat
                    hbcheckedflag = true;

                }
                else{
                    Toast.makeText(details_ofdonor.this, "Hb should be minimum 12.5", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.age:

                if(checked ) {
                    // I'm lactose intolerant
                    agecheckedflag = true;

                }
                else {
                    Toast.makeText(details_ofdonor.this, "Age should be between 18 to 65 ", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.valid:

                if(checked ) {
                    // I'm lactose intolerant
                    validcheckedflag = true;

                }
                else {
                    Toast.makeText(details_ofdonor.this, "Acceptance of condition is must", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.wt:

                if(checked ) {
                    // I'm lactose intolerant
                    wtcheckedflag = true;

                }
                else {
                    Toast.makeText(details_ofdonor.this, "Acceptance of condition is must", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
    public void showmsg(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

}