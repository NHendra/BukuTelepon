package com.bukutelepon;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends Activity {
	protected Cursor cursor;
	sql dbHelper;
	Button call;
	TextView view_nama,view_alamat,view_telp,view_kategori;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		call = (Button)findViewById(R.id.button1);
		
		dbHelper = new sql(this);
		view_nama = (TextView)findViewById(R.id.textView2);
		view_alamat = (TextView)findViewById(R.id.textView4);
		view_telp = (TextView)findViewById(R.id.textView6);
		view_kategori = (TextView)findViewById(R.id.textView9);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM buku_telepon WHERE nama='"+getIntent().getStringExtra("nama")+"'", null);
		cursor.moveToFirst();
		
		if(cursor.getCount()>0)
		{
			cursor.moveToPosition(0);
			view_nama.setText(cursor.getString(0).toString());
			view_alamat.setText(cursor.getString(1).toString());
			view_telp.setText(cursor.getString(2).toString());
			view_kategori.setText(cursor.getString(3).toString());
		}
		
		call.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String no = view_telp.getText().toString();
				Intent panggil = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+no));
				startActivity(panggil);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

}
