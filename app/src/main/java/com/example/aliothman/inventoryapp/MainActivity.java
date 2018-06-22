package com.example.aliothman.inventoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aliothman.inventoryapp.alldata.BookContract;
import com.example.aliothman.inventoryapp.alldata.BookDbHelper;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private BookDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new BookDbHelper(this);

        insertData();
        displayData();
    }

    private void insertData() {
        //Git the data repository in write mode.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE, "Zaad Alma'ad fe Hadee Khair Ale'bad");
        values.put(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME, "Ibn Qayem Aljawzyah");
        values.put(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE, 60);
        values.put(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY, 13);
        values.put(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME, "Ar-Resalah");
        values.put(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL, "books@resalah.com");
        values.put(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER, "0500005555");

        long newRowId;
        newRowId = db.insert(BookContract.ProductInfoEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Log.e(LOG_TAG, "Error with saving product info");
        } else {
            Log.i(LOG_TAG, "Product saved with ID " + newRowId);
        }
    }

    private Cursor queryData(){

        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BookContract.ProductInfoEntry._ID,
                BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE,
                BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME,
                BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE,
                BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY,
                BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME,
                BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL,
                BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER
        };

        Cursor cursor = db.query(
                BookContract.ProductInfoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    private void displayData() {

        Cursor cursor = queryData();

        try {

            Log.i(LOG_TAG, "Number of rows on productInfo table = " + cursor.getCount());

            Log.i(LOG_TAG, BookContract.ProductInfoEntry._ID
                    + "\t" + BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE
                    + "\t" + BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME
                    + "\t" + BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE
                    + "\t" + BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY
                    + "\t" + BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME
                    + "\t" + BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL
                    + "\t" + BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER
            );

            int idColumnIndex = cursor.getColumnIndex(BookContract.ProductInfoEntry._ID);
            int bookTitleColumnIndex = cursor.getColumnIndex(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE);
            int authorNameColumnIndex = cursor.getColumnIndex(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE);
            int availableQuantityColumnIndex = cursor.getColumnIndex(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME);
            int supplierEmailColumnIndex = cursor.getColumnIndex(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(BookContract.ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER);

            if (cursor.moveToFirst()) {
                do {
                    int currentId = cursor.getInt(idColumnIndex);
                    String currentBookTitle = cursor.getString(bookTitleColumnIndex);
                    String currentAuthorName = cursor.getString(authorNameColumnIndex);
                    int currentPrice = cursor.getInt(priceColumnIndex);
                    int currentAvailableQuantity = cursor.getInt(availableQuantityColumnIndex);
                    String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                    String currentSupplierEmail = cursor.getString(supplierEmailColumnIndex);
                    String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);

                    Log.i(LOG_TAG, currentId
                            + "\t" + currentBookTitle
                            + "\t" + currentAuthorName
                            + "\t" + currentPrice
                            + "\t" + currentAvailableQuantity
                            + "\t" + currentSupplierName
                            + "\t" + currentSupplierEmail
                            + "\t" + currentSupplierPhoneNumber
                    );
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }
    }
}
