package com.bukutelepon;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Edit extends Activity {
	protected Cursor cursor;
	sql dbHelper;
	Spinner spinner; 
	Button simpan;
	EditText edit_nama,edit_alamat,edit_telp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		dbHelper = new sql(this);
		edit_nama = (EditText)findViewById(R.id.editText1);
		spinner = (Spinner)findViewById(R.id.spinner); 
		edit_alamat = (EditText)findViewById(R.id.editText2);
		edit_telp = (EditText)findViewById(R.id.editText3);
		loadSpinnerData(); 
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM buku_telepon WHERE nama = '"+getIntent().getStringExtra("nama")+"'", null);
		cursor.moveToFirst();
		if(cursor.getCount()>0)
		{
			cursor.moveToPosition(0);
			edit_nama.setText(cursor.getString(0).toString());
			edit_alamat.setText(cursor.getString(1).toString());
			edit_telp.setText(cursor.getString(2).toString());
		}
		
		simpan = (Button)findViewById(R.id.button1);
		simpan.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String text = spinner.getSelectedItem().toString();
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				
				db.execSQL("UPDATE buku_telepon SET " +
						"nama='"+edit_nama.getText().toString()+"'," +
						"alamat='"+edit_alamat.getText().toString()+"'," +
						"no_telp='"+edit_telp.getText().toString()+"'," +
						"kategori='"+text+"'" +
						"WHERE nama='"+getIntent().getStringExtra("nama")+"'");
				Toast.makeText(getApplicationContext(), "Berhasil di Edit!", Toast.LENGTH_LONG).show();
				MainActivity.ma.RefreshList();
				finish();
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
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
