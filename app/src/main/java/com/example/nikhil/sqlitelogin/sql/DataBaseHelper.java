package com.example.nikhil.sqlitelogin.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.nikhil.sqlitelogin.model.User;

//import static android.provider.ContactsContract.Intents.Insert.EMAIL;

/**
 * Created by NIKHIL on 8/7/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="UserManager.db";

    private static final String TABLE_USER="user";
    private static final String COLUMN_USER_ID="user_id";
    private static final String COLUMN_USER_Name="user_name";
    private static final String COLUMN_USER_EMAIL="user_email";
    private static final String COLUMN_USER_PASSWORD="user_password";



    private String CREATE_USER_TABLE="TABLE_NAME"+ TABLE_USER +"("
            + COLUMN_USER_ID +"INTEGER PRIMARY KEY AUTOIMPLIMENT,"+ COLUMN_USER_Name +"TEXT,"
            + COLUMN_USER_EMAIL +"TEXT,"+COLUMN_USER_PASSWORD +"TEXT"+ ")";



    private String DROP_USER_TABLE="DROP_TABLE IF EXISTS"+ TABLE_USER;


    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion1)
    {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USER_Name,user.getName());


        values.put(COLUMN_USER_EMAIL,user.getEmail());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());
        db.insert(TABLE_USER,null,values);
        db.close();


    }

    public boolean CheckUser(String email)
    {
        String[] columns={

                COLUMN_USER_ID
        };

        SQLiteDatabase db=this.getWritableDatabase();
        String selection= COLUMN_USER_EMAIL +"= ?";
        String[] selectionArgs={email};

        Cursor cursor=db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,null,null,null);

        int CursorCount=cursor.getCount();
        cursor.close();
        db.close();


        if(CursorCount > 0)
        {
            return true;
        }
        return  false;
    }


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }




}
