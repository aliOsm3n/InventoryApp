package com.example.aliothman.inventoryapp.alldata;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bookStore.db";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";
    private static final String SQL_CREATE_PRODUCT_INFO_TABLE =
            "CREATE TABLE " + BookContract.ProductInfoEntry.TABLE_NAME + " (" +
                    BookContract.ProductInfoEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                    BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                    BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_IMAGE + TEXT_TYPE + COMMA_SEP +
                    BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE + INTEGER_TYPE + " NOT NULL DEFAULT 0" + COMMA_SEP +
                    BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY + INTEGER_TYPE + " NOT NULL DEFAULT 0" + COMMA_SEP +
                    BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                    BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                    BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER + TEXT_TYPE + " );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCT_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}