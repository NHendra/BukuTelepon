package com.bukutelepon;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add extends Activity {
	protected Cursor cursor;
	sql dbHelper;
	Spinner spinner;  
	Button simpan;
	EditText add_nama,add_alamat,add_telp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		spinner = (Spinner)findViewById(R.id.spinner);  
		dbHelper = new sql(this);
		add_nama = (EditText)findViewById(R.id.editText1);
		add_alamat = (EditText)findViewById(R.id.editText2);
		add_telp = (EditText)findViewById(R.id.editText3);
		loadSpinnerData();  
		simpan = (Button)findViewById(R.id.button1);
		simpan.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String text = spinner.getSelectedItem().toString();
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.execSQL("INSERT INTO buku_telepon(nama,alamat,no_telp,kategori)" +
						"VALUES('"+add_nama.getText().toString()+"','"+add_alamat.getText().toString()+"','"+add_telp.getText().toString()+"','"+text+"')");
				Toast.makeText(getApplicationContext(), "Berhasil Disimpan!", Toast.LENGTH_LONG).show();
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
  
    public void onItemSelected(AdapterView<?> parent, View view, int position,  
                               long id) {  
        // On selecting a spinner item  
        String label = parent.getItemAtPosition(position).toString();  
  
        // Showing selected spinner item  
        Toast.makeText(parent.getContext(), "You selected: " + label,  
                Toast.LENGTH_LONG).show();  
  
    }  
  
    public void onNothingSelected(AdapterView<?> arg0) {  
        // TODO Auto-generated method stub  
  
    }  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

}
