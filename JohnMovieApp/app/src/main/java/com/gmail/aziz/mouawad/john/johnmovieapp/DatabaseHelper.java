package com.gmail.aziz.mouawad.john.johnmovieapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jo on 11/1/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student_db";
    public static final String TABLE_NAME = "Student_table";
    public static final String COL_ID = "ID";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_DESCRIBTION = "DESCRIBTION";
    public static final String COL_RELEASE_DATE = "RELEASE_DATE";
    public static final String COL_VOTE = "VOTE";
    public static final String COL_IMAGE = "IMAGE";
    public static final String COL_Reviewe = "REVIEWE";


    DatabaseHelper DBinfo;

    Context context;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 11);
        //SQLiteDatabase db = this.getWritableDatabase(); // to create data base and table

        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                " (" + COL_ID + " INTEGER , " +
                COL_TITLE + " TEXT , " +
                COL_DESCRIBTION + " TEXT , " +
                COL_VOTE + " INTEGER , " +
                COL_RELEASE_DATE + " TEXT ," + COL_IMAGE + " TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String ID, String Title, String Describtion, String Vote, String releasedate,String Image ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COL_ID, ID);
        contentValues.put(COL_TITLE, Title);
        contentValues.put(COL_DESCRIBTION, Describtion);
        contentValues.put(COL_VOTE, Vote);
        contentValues.put(COL_RELEASE_DATE, releasedate);
        contentValues.put(COL_IMAGE, Image);
        //contentValues.put(COL_Reviewe,Reviewe);
        long result = db.insert(TABLE_NAME, null, contentValues);
        Toast.makeText(context, "add to Fav", Toast.LENGTH_SHORT).show();

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }





    public List<Movie> viewData(){
        List<Movie> movies = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        String[] columns={this.COL_ID,this.COL_TITLE,this.COL_DESCRIBTION,this.COL_VOTE,this.COL_RELEASE_DATE,this.COL_IMAGE};

        Cursor cursor = sqLiteDatabase.query(DBinfo.TABLE_NAME,columns,null,null,null,null,null);

        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext()){

            int col_ID=cursor.getInt(0);
            String col_TITLE=cursor.getString(1);
            String col_DESCRIBTION=cursor.getString(2);
            int col_VOTE=cursor.getInt(3);
            String col_RELEASE_DATE=cursor.getString(4);
            String col_IMAGE=cursor.getString(5);
            // String col_REVIEWE=cursor.getString(6);
            movies.add(new Movie(String.valueOf(col_ID),String.valueOf(col_VOTE),col_RELEASE_DATE,col_TITLE,col_IMAGE,col_DESCRIBTION));
        }
        return movies;
    }





    public Cursor getListContents() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;

    }

    ////////////////////////////////////////////ID////////////////////////


    public List<String> getID() {
        List<String> movies = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select "+COL_ID+" from " + TABLE_NAME , null);
        if(res.moveToFirst()){
            do{
                movies.add(res.getString(res.getColumnIndex(COL_ID)));
            }while(res.moveToNext());
        }
        return movies;
    }


}
