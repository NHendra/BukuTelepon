package com.bukutelepon;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddkActivity extends Activity {
 
    Button btnAdd;  
    EditText inputLabel;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addk);  
        btnAdd =  (Button)findViewById(R.id.btn_add);  
        inputLabel = (EditText)findViewById(R.id.input_label);
        btnAdd.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String label = inputLabel.getText().toString();  
				  
                if (label.trim().length() > 0) {  
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());  
                    db.insertLabel(label);  
  
                    // making input filed text to blank  
                    inputLabel.setText("");  
  
                    // Hiding the keyboard  
                    InputMethodManager imm = (InputMethodManager)  
                            getSystemService(Context.INPUT_METHOD_SERVICE);  
                    imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);  
                    // loading spinner with newly added data  
              
                } else {  
                    Toast.makeText(getApplicationContext(), "Masukin Dulu Kategori",  
                            Toast.LENGTH_SHORT).show();  
                }  
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addk, menu);
		return true;
	}

}
