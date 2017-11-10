package com.example.kebohan.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

/**
 * Created by 柯博瀚 on 2017/11/8.
 */

public class DataBase_Helper extends SQLiteOpenHelper {

    private static final int DBVersion = 1;

    private static final String Userconfig = "Userconfig.db";
    private static final String TableName = "User_info";  //表格名稱3
    private static final String Ticket_info = "Ticket_info";
    private static final String Store_info = "Store_info";



    public DataBase_Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Userconfig, factory, DBVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String Userconfig = "CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_Preference_id INTEGER," +
                "_Canteen_id INTEGER," +
                "_Data_type INTEGER"+
                ");";
        final String Ticket = "CREATE TABLE IF NOT EXISTS " + Ticket_info + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_Ticket_name VARCHAR(50), " +
                "_Canteen_id INTEGER," +
                "_Achieve_date DATE," +
                "_End_date DATE"+
                ");";
        final String Store = "CREATE TABLE IF NOT EXISTS " + Store_info + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_Store_name VARCHAR(50), " +
                "_Address VARCHAR(100)," +
                "_Price_level INTEGER," +
                ");";
        db.execSQL(Userconfig);
        db.execSQL(Ticket);
        db.execSQL(Store);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
