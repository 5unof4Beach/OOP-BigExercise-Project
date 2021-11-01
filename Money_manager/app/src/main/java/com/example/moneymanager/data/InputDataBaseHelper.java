package com.example.moneymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.moneymanager.mainprocess.Input;

import java.util.Vector;

public class InputDataBaseHelper extends SQLiteOpenHelper {
    public static final String INPUT_TABLE = "INPUT";
    public static final String ID = "_id";
    public static final String AMOUNT  = "amount";
    public static final String NOTE  = "note";
    public static final String CATEGORY  = "category";
    public static final String TYPE  = "type";

    public static final String DATABASE_NAME  = "input";
    public static final int DATABASE_VERSION  = 1;


    public InputDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE  =
        String.format("create table %s( %s integer primary key autoincrement, %s integer, %s text, %s text, %s integer);",INPUT_TABLE,ID,AMOUNT,NOTE,CATEGORY,TYPE);
//        String.format("create table %s(%s int, %s text, %s text, %s int);",INPUT_TABLE,AMOUNT,NOTE,CATEGORY,TYPE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INPUT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addInput(Input input){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AMOUNT, input.getAmount());
        values.put(NOTE,input.getNote());
        values.put(CATEGORY,input.getCategory());
        values.put(TYPE, input.getType());

        db.insert(INPUT_TABLE,null,values);
        db.close();
    }

    public Vector<Input> getAllInput(){
        Vector<Input> inputs = new Vector<>();

        String query = String.format("select * from %s",INPUT_TABLE);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            Input input = new Input(cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            inputs.add(input);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return inputs;
    }

    public void printSomeThing(){
        System.out.println("Something");
    }
}
