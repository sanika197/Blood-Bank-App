package com.example.blooddonation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public  class DatabaseHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "BloodDonationdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "donors";
    private static final String TABLE_RECIEVERS = "recievers";
    private static final String TABLE_NAME1 = "donors1";
    // private static final String TABLE_RECIEVERS1 = "recievers1";

    // below variable is for our id column. and common coloumns
    private static final String ID_COL = "id";
    private static final String ID_RECIVER_COL = "id1";
    private static final String ID_COL1 = "idD";
    //private static final String ID_RECIVER_COL1 = "idR1";


    // below variable is for our course name column
    private static final String NAME_COL = "name_donor";
    private static final String NAME_RECIEVER_COL = "name_reciever";
    private static final String NAME_COL1 = "name_donor1";
    // private static final String NAME_RECIEVER_COL1 = "name_reciever1";

    // below variable id for our course duration column.
    //private static final String DURATION_COL = "duration";

    // below variable for our course description column.
    private static final String BLOODGROUP_COL = "Blood_group_donor";
    private static final String BLOODGROUP_RECIEVER_COL = "Blood_group_reciever";
    private static final String BLOODGROUP_COL1 = "Blood_group_donor1";
    private static final String BLOODGROUP_RECIEVER_COL1 = "Blood_group_reciever1";


    // below variable is for our course tracks column.
    private static final String LOCATION_COL = "Location_donor";
    private static final String LOCATION_RECIEVER_COL = "Location_reciever";
    private static final String LOCATION_COL1 = "Location_donor1";
    //private static final String LOCATION_RECIEVER_COL1 = "Location_reciever1";
    //private static final String CONTACT_DONOR = "ContactDonor";

    private static final String EMAIL_DONOR="Donor_email";
    private static final String EMAIL_RECEIVER="Receiver_email";

    private static final String CONTACT_DONOR="Donor_Contact";
    private static final String CONTACT_RECEIVER="Receiver_Contact";
    private static final String DATE_DONATION="Date_Donation";

    private static final String Hb_COL ="Hb";


    private static final String age_COL ="age";

    private static final String Contact_COL ="contact";

    private static final String Email_COL ="email";

    private static final String Gender_COL ="Gender";

    private static final String Vaccine_COL ="vaccine";

    private static final String query = "CREATE TABLE " + TABLE_NAME + " ("
            + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_COL + " TEXT,"
            + EMAIL_DONOR + " TEXT not null,"
            +CONTACT_DONOR+" TEXT not null,"
            + BLOODGROUP_COL + " TEXT,"
            + DATE_DONATION + " DATE not null,"
            + LOCATION_COL + " TEXT)";
    private static final String table_reciever = "CREATE TABLE " + TABLE_RECIEVERS + " ("
            + ID_RECIVER_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_RECIEVER_COL + " TEXT,"
            + EMAIL_RECEIVER + " TEXT not null,"
            +CONTACT_RECEIVER+" TEXT not null,"
            + BLOODGROUP_RECIEVER_COL + " TEXT,"
            + LOCATION_RECIEVER_COL + " TEXT)";

    private static final String query1 = "CREATE TABLE " + TABLE_NAME1 + " ("
            + ID_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_COL1 + " TEXT,"
            //+ CONTACT_DONOR + " TEXT,"
            + BLOODGROUP_COL1 + " TEXT,"
            + LOCATION_COL1 + " TEXT,"
            + Hb_COL + " TEXT,"
            + age_COL + " TEXT,"
            + Contact_COL + " TEXT,"
            + Email_COL + " TEXT,"
            + Gender_COL + " TEXT,"
            + Vaccine_COL + " TEXT)" ;
    // creating a constructor for our database handler.
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        db.execSQL(table_reciever);
        db.execSQL(query1);
    }
    public boolean addreciever(Recievers r) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase mydb = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values2 = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values2.put(NAME_RECIEVER_COL, r.getName());
        values2.put(BLOODGROUP_RECIEVER_COL, r.getBlood_grp());
        values2.put(EMAIL_RECEIVER,r.getEmail_r());
        values2.put(CONTACT_RECEIVER,r.getContact_r());
        values2.put(LOCATION_RECIEVER_COL, r.getLocation());

        // after adding all values we are passing
        // content values to our table.
        long res = mydb.insert(TABLE_RECIEVERS, null, values2);
        if (res==-1) {
            return false;
        }
        else {
            return true;
        }

        // at last we are closing our
        // database after adding database.
    }
    // this method is use to add new course to our sqlite database.
    public boolean adddonor(Donors d) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, d.getName());
        //values.put(DURATION_COL, courseDuration);
        values.put(BLOODGROUP_COL, d.getBlood_grp());
        values.put(LOCATION_COL, d.getLocation());
        values.put(EMAIL_DONOR,d.getEmail_d());
        values.put(DATE_DONATION,getDateTime());
        values.put(CONTACT_DONOR,d.getCon());

        // after adding all values we are passing
        // content values to our table.
        long r = db.insert(TABLE_NAME, null, values);
        if (r==-1) {
            return false;
        }
        else {
            return true;
        }

        // at last we are closing our
        // database after adding database.
    }


    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);

    }

    public Cursor dateDiff()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =db.rawQuery("SELECT name_donor,ROUND(( JULIANDAY(Date_Donation)- JULIANDAY(Date('now')))) from "+ TABLE_NAME,null);
        return  res;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIEVERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public Cursor getALLdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public Cursor getALLdataOfReciever() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_RECIEVERS, null);
        return res;
    }
    public  Cursor FindData(String bloodgroup){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =db.rawQuery("SELECT * FROM donors WHERE  Blood_group_donor = ?", new String[]{bloodgroup});
        return  res;
    }
    public Cursor FindDatawithLoc(String blg, String locan){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =db.rawQuery("SELECT * FROM donors WHERE  Blood_group_donor = ? AND  Location_donor = ? ", new String[]{blg,locan});
        return  res;
    }
    /*public void UPDATE_STUDENT(String id1, String name1
    ) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, name1);
        //values.put(DURATION_COL, courseDuration);
        values.put(BLOODGROUP_COL, d.getBlood_grp());
        values.put(LOCATION_COL, d.getLocation());
        values.put(EMAIL_DONOR,d.getEmail_d());
        values.put(DATE_DONATION,getDateTime());
        values.put(CONTACT_DONOR,d.getCon());


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_STUDENTS, values, "id=?", new String[]{id1});
        db.close();
    }*/

}