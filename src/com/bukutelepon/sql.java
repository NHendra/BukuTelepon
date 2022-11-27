package com.bukutelepon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class sql extends SQLiteOpenHelper {
private static final String DATABASE_NAME = "db.db";
private static final int DATABASE_VERSION = 1;
public sql(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
// TODO Auto-generated constructor stub
}
@Override
public void onCreate(SQLiteDatabase db) {
	String sql = "create table buku_telepon(nama text null, " +" alamat text null, no_telp text null,kategori text null);";
	Log.d("Data", "onCreate: " + sql);
	db.execSQL(sql);
	sql = "INSERT INTO buku_telepon (nama, alamat, no_telp, kategori) VALUES ('I Wayan Gunayasa', 'Gianyar', '089172812', 'Teman');";
	db.execSQL(sql);
	sql = "INSERT INTO buku_telepon (nama, alamat, no_telp, kategori) VALUES ('I Wayan Nanda Mahendra', 'Badung', '081337672853', 'Kampus');";
	db.execSQL(sql);
	
	

// TODO Auto-generated method stub
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
}
}