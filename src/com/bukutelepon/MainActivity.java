package com.bukutelepon;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {
	String[] daftar;
	ListView buku_telepon;
	Spinner spinner; 
	Button tambah,tambahk,cari,segar;
	protected Cursor cursor;
	sql dbHelper;
	public static MainActivity ma;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spinner = (Spinner)findViewById(R.id.spinner); 
		loadSpinnerData(); 
		ma = this;
			dbHelper=new sql(this);
			RefreshList();
			
	}

	void RefreshList() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM buku_telepon", null);
		daftar = new String[cursor.getCount()];
		cursor.moveToFirst();
		for(int cc=0; cc<cursor.getCount();cc++ )
		{
			cursor.moveToPosition(cc);
			daftar[cc] = cursor.getString(0).toString();
		}
		
		//TOMBOL TAMBAH
		tambah = (Button)findViewById(R.id.button1);
		tambah.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent a = new Intent(getApplicationContext(),Add.class);
				startActivity(a);
			}});
		tambahk = (Button)findViewById(R.id.button2);
		tambahk.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent a = new Intent(getApplicationContext(),AddkActivity.class);
				startActivity(a);
			}});
		cari = (Button)findViewById(R.id.button3);
		cari.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RefreshList2();
			}});
		segar = (Button)findViewById(R.id.button4);
		segar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RefreshList();
				loadSpinnerData(); 
			}});
		
		
		
		//ITEM LIST
		buku_telepon = (ListView)findViewById(R.id.listView1);
		buku_telepon.setAdapter(new ArrayAdapter<Object>(this,android.R.layout.simple_list_item_1,daftar));
		buku_telepon.setSelected(true);
		buku_telepon.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				final String selection = daftar[arg2];
				final CharSequence[] dialogitem = {"Detail Kontak","Edit Kontak","Hapus Kontak"};
				
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Pilihan");
				builder.setItems(dialogitem, new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int item) {
						// TODO Auto-generated method stub
					switch(item){
					case 0:
						Intent i = new Intent(getApplicationContext(),Detail.class);
						i.putExtra("nama", selection);
						startActivity(i);
						break;
					case 1:
						Intent a = new Intent(getApplicationContext(),Edit.class);
						a.putExtra("nama", selection);
						startActivity(a);
						break;
					case 2:
						SQLiteDatabase db = dbHelper.getWritableDatabase();
						db.execSQL("DELETE FROM buku_telepon WHERE nama = '"+selection+"'");
						RefreshList();
						break;
					}
					}});
					builder.create().show();
				}});
			
	}
	
	void RefreshList2() {
		String text = spinner.getSelectedItem().toString();
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM buku_telepon Where Kategori='"+text+"'", null);
		daftar = new String[cursor.getCount()];
		cursor.moveToFirst();
		for(int cc=0; cc<cursor.getCount();cc++ )
		{
			cursor.moveToPosition(cc);
			daftar[cc] = cursor.getString(0).toString();
		}
		
		//TOMBOL TAMBAH
		tambah = (Button)findViewById(R.id.button1);
		tambah.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent a = new Intent(getApplicationContext(),Add.class);
				startActivity(a);
			}});
		tambahk = (Button)findViewById(R.id.button2);
		tambahk.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent a = new Intent(getApplicationContext(),AddkActivity.class);
				startActivity(a);
			}});
		cari = (Button)findViewById(R.id.button3);
		cari.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RefreshList2();
			}});
		segar = (Button)findViewById(R.id.button4);
		segar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RefreshList();
				loadSpinnerData(); 
			}});
		
		
		
		//ITEM LIST
		buku_telepon = (ListView)findViewById(R.id.listView1);
		buku_telepon.setAdapter(new ArrayAdapter<Object>(this,android.R.layout.simple_list_item_1,daftar));
		buku_telepon.setSelected(true);
		buku_telepon.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				final String selection = daftar[arg2];
				final CharSequence[] dialogitem = {"Detail Kontak","Edit Kontak","Hapus Kontak"};
				
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Pilihan");
				builder.setItems(dialogitem, new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int item) {
						// TODO Auto-generated method stub
					switch(item){
					case 0:
						Intent i = new Intent(getApplicationContext(),Detail.class);
						i.putExtra("nama", selection);
						startActivity(i);
						break;
					case 1:
						Intent a = new Intent(getApplicationContext(),Edit.class);
						a.putExtra("nama", selection);
						startActivity(a);
						break;
					case 2:
						SQLiteDatabase db = dbHelper.getWritableDatabase();
						db.execSQL("DELETE FROM buku_telepon WHERE nama = '"+selection+"'");
						RefreshList();
						break;
					}
					}});
					builder.create().show();
				}});
			
	}
	
	private void loadSpinnerData() {  
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());  
        List<String> labels = db.getAllLabels();  
  
        // Creating adapter for spinner  
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);  
  
        // Drop down layout style - list view with radio button  
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
  
        // attaching data adapter to spinner  
        spinner.setAdapter(dataAdapter);  
    }  


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
