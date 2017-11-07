package com.example.kebohan.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 柯博瀚 on 2017/11/8.
 */

public class DataBase_Helper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "userinfo";  //表格名稱

    public static final String NAME = "name";

    public static final String TEL = "tel";

    public static final String EMAIL = "email";


    public DataBase_Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
