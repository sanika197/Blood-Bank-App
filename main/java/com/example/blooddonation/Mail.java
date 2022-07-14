package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail extends AppCompatActivity {

    //Initialize variable
    EditText etTo,etSubject,etMessage;
    Button btSend;
    String sEmail,sPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);


        //Assign variable
        etTo=findViewById(R.id.et_to);
        etSubject=findViewById(R.id.et_subject);
        etMessage=findViewById(R.id.et_message);
        btSend=findViewById(R.id.bt_send);

        //Send email creatential
        sEmail="gayatri.chaudhari1281@gmail.com"; //e.g. example@gmail.com
        sPassword="Sharda@1975";

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize properties
                Properties properties=new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");

                //Initialize session
                Session session=Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail,sPassword);
                    }
                });

                try {
                    //Initialize email content

                    Message message=new MimeMessage(session);
                    //Sender email
                    message.setFrom(new InternetAddress(sEmail));
                    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(etTo.getText().toString().trim()));
                    //Email subject
                    message.setSubject(etSubject.getText().toString().trim());

                    //Email message
                    message.setText(etMessage.getText().toString().trim());

                    //send email.
                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class SendMail extends AsyncTask<Message,String,String> {
        //initialize progress dialog
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //create and show progress dialog
            progressDialog=ProgressDialog.show(Mail.this,"Please Wait","Sending Mail...",true,false);

        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Dismiss progress dialog
            progressDialog.dismiss();
            if(s.equals(("Success")))
            {
                //when Success
                //Initialize alert dialog
                AlertDialog.Builder builder=new AlertDialog.Builder(Mail.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail send Suceessfully");
                builder.setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    //clear all edit text
                    etTo.setText("");
                    etSubject.setText("");
                    etMessage.setText("");

                });
                //show alert dialog
                builder.show();
            }
            else
            {
                //when error
                Toast.makeText(getApplicationContext(), "Something went wrong?", Toast.LENGTH_SHORT).show();
            }

        }

    }
}