package com.example.lessonandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "inclassss.db";
    private static final int DATABASE_VERSION = 1;
    private static final String  TABLE_NAME = "students";
    private static final String ID ="id";
    private static final String NAME ="name";
    private static final String AGE ="age";
    private static final String EMAIL ="email";
    SQLiteDatabase db;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql= "CREATE TABLE " + TABLE_NAME + "(" +ID+ " INTEGER" + " PRIMARY KEY AUTOINCREMENT,"+
                NAME + " TEXT," + AGE + " TEXT," + EMAIL + " TEXT" + ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop TABLE if exists "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,"Saw");
        values.put(AGE,"20");
        db.insert(TABLE_NAME,null,values);
    }

    public String getdata(){
        db=this.getWritableDatabase();
        String[] columns=new String[]{ID,NAME,AGE,EMAIL};
        Cursor cursor= db.query(TABLE_NAME,null,null,null,null,null,null);
        int id=cursor.getColumnIndex(ID);
        int name= cursor.getColumnIndex(NAME);
        int age= cursor.getColumnIndex(AGE);
        int email=cursor.getColumnIndex(EMAIL);

        String result= "";

        for (cursor.moveToFirst(); ! cursor.isAfterLast(); cursor.moveToNext() ){
            result=result+cursor.getString(id);
            result=result+cursor.getString(name);
            result=result+cursor.getString(age);
            result=result+cursor.getString(email);
        }

        return result;
    }
}
