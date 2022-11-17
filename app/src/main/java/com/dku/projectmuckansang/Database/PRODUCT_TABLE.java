package com.dku.projectmuckansang.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PRODUCT_TABLE {
    private static final String TABLE_NAME = "PRODUCT";
    private static final String CREATE_TABLE =
            "create table if not exists " + TABLE_NAME + " ( " +
                "productID      integer PRIMARY KEY autoincrement, " +
                "productName    text    not null," +
                "productCount   integer not null, " +
                "categoryID     integer not null, " +
                "FOREIGN KEY( categoryID ) REFERENCES DEFAULT_PERIOD( categoryID ) " +
            ")";

    public static void createTable(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    public static void insertItem(SQLiteDatabase database, int categoryID, String productName, int productCount) {
        database.execSQL(
                "insert into " + TABLE_NAME + " (categoryID, productName, productCount) " +
                        "values(" + categoryID + ", " + productName + ", " + "productCount" +  ")"
        );
    }

    public static int getLastProductID(SQLiteDatabase database, int categoryID, String productName, int productCount) {
        Cursor cursor = database.rawQuery(
                "select productID from " + database +
                        " where categoryID = " + categoryID + ", " +
                        "productName = " + productName + ", " +
                        "productCount = " + productCount + ")",
                null
        );

        int count = cursor.getCount();
        if(count == 0) return -1;
        cursor.moveToLast();
        return cursor.getInt(0);
    }

    public static Cursor getAllProduct(SQLiteDatabase database) {
        return database.rawQuery("select productID, categoryID, productCount, productName from " + TABLE_NAME, null);
    }
}