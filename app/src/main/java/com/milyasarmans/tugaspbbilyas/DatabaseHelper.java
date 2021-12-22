package com.milyasarmans.tugaspbbilyas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data_mhs.db";
    public static final String TABLE_NAME = "table_mhs";
    public static final String col_1 = "nim";
    public static final String col_2 = "nama";
    public static final String col_3 = "umur";
    public static final String col_4 = "ipk";
    public static final String col_5 = "alamat";
    public static final int DATABASE_VERTION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERTION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE table_mhs(nim text null, nama text null, umur text null, ipk text null, alamat text null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //Method Insert Data
    public boolean insertData(String nim, String nama, String umur, String ipk, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_1,nim);
        contentValues.put(col_2,nama);
        contentValues.put(col_3,umur);
        contentValues.put(col_4,ipk);
        contentValues.put(col_5,alamat);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result ==-1){
            return false;
        }
        else{
            return true;
        }
    }

    //Method Ubah Data
    public boolean ubahData(String nim, String nama, String umur, String ipk, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,nim);
        contentValues.put(col_2,nama);
        contentValues.put(col_3,umur);
        contentValues.put(col_4,ipk);
        contentValues.put(col_5,alamat);

        long result = db.update(TABLE_NAME,contentValues, "nim =?", new String[] {nim});
        return true;
    }

    public int deleteData(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "nim = ?", new String[] {nim});
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_mhs", null);
        return res;
    }
}

