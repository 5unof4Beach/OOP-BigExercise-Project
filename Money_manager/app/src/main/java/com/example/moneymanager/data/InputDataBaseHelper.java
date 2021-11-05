package com.example.moneymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.moneymanager.repositories.Input;

import java.util.Vector;

public class InputDataBaseHelper extends SQLiteOpenHelper {
    public static final String INPUT_TABLE = "INPUT";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String AMOUNT  = "amount";
    public static final String NOTE  = "note";
    public static final String CATEGORY  = "category";
    public static final String TYPE  = "type";

    public static final String DATABASE_NAME  = "input";
    public static final int DATABASE_VERSION  = 4;

    public static String query = "";

    public InputDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE  =
        String.format("create table %s(%s int, %s int, %s int, %s int, %s text, %s text, %s int);",INPUT_TABLE,YEAR,MONTH,DAY,AMOUNT,NOTE,CATEGORY,TYPE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INPUT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addInput2(Input input,int date, int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DAY, date);
        values.put(YEAR, year);
        values.put(MONTH, month);
        values.put(AMOUNT, input.getAmount());
        values.put(NOTE,input.getNote());
        values.put(CATEGORY,input.getCategory());
        values.put(TYPE, input.getType());


        db.insert(INPUT_TABLE,null,values);
        db.close();
    }
    public void setTimeForInput(Input input,Cursor cursor){
        input.setDate(cursor.getInt(2));
        input.setMonth(cursor.getInt(1));
        input.setYear(cursor.getInt(0));
    }
    public Vector<Input> getAllInput(int date, int month, int year){
        Vector<Input> inputs = new Vector<>();

        getDefaultQueryAndArgs(date, month, year);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Input input = new Input(cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
            setTimeForInput(input, cursor);
            inputs.add(input);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return inputs;
    }

    public Vector<Input> getMonthlyInput(int date, int month, int year){
        Vector<Input> inputs = new Vector<>();

        query = String.format("select * from %s where month = %d and year = %d",INPUT_TABLE,month,year);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Input input = new Input(cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
            setTimeForInput(input, cursor);
            inputs.add(input);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return inputs;
    }

    private void getQueryAndArgs(int date, int month, int year){
        query = String.format("select * from %s where month = %d and day = %d",INPUT_TABLE, month, date);
        if(month == 0 && date !=0){
            query = String.format("select * from %s where day = %d",INPUT_TABLE, date);
            print("month == 0 && date != 0");
        }
        else if(month != 0 && date == 0){
            query = String.format("select * from %s where month = %d",INPUT_TABLE, month);
            print("month != 0 && date == 0");
        }
        else if(month == 0 && date == 0){
            query = String.format("select * from %s",INPUT_TABLE);
            print("month == 0 && date == 0");
        }
    }

    private void getAllQuery(){
        query = String.format("select * from %s",INPUT_TABLE);
    }
    private void getDefaultQueryAndArgs(int date, int month, int year){
        query = String.format("select * from %s where month = %d and day = %d and year = %d",INPUT_TABLE,month,date,year);
    }

    private void print(String s){
        System.out.println(s);
    }
}
