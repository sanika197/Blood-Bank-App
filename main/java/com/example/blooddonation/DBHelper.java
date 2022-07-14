package com.example.blooddonation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "BloodBank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table BloodBank(id TEXT primary key, name TEXT, blood_grp TEXT, Date TEXT, location TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists BloodBank");
    }

    public  Cursor checkAvailability(String grp, String loc){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cur = DB.rawQuery("Select * from BloodBank where blood_grp=? and location=?",new String[]{grp, loc});
        return cur;
    }

    public boolean insertData(String id, String name, String blood_grp, String date, String location)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("name", name);
        cv.put("blood_grp", blood_grp);
        cv.put("date", date);
        cv.put("location", location);
        long result = DB.insert("BloodBank", null, cv);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }



    public boolean deleteData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cur = DB.rawQuery("Select * from BloodBank where JULIANDAY('now')-JULIANDAY(date) > 42", null);
        boolean res = false;
        if(cur.getCount()>0)
        {
            res = true;
            while(cur.moveToNext())
            {
                String reqID = cur.getString(0);
                DB.delete("BloodBank", "id=?", new String[]{reqID});
            }
        }

        return res;

    }




    public boolean updateData(String id, String name, String blood_grp, String date, String location)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("blood_grp", blood_grp);
        cv.put("date", date);
        cv.put("location", location);

        Cursor cur = DB.rawQuery("Select * from BloodBank where id=?", new String[] {id});
        if(cur.getCount()>0)
        {
            long result = DB.update("BloodBank", cv, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public int returnCount(String grp)
    {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cur = DB.rawQuery("Select count(*) from BloodBank where blood_grp=?", new String[]{grp});
        return cur.getCount();
    }

    public Cursor ViewData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cur = DB.rawQuery("Select * from BloodBank", null);
        return cur;
    }

}