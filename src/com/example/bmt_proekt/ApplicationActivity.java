package com.example.bmt_proekt;

import android.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ApplicationActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	Button najava,registracija;
	public EditText kime,lozinka,username;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first);
        kime=(EditText)findViewById(R.id.editText1);
        lozinka=(EditText)findViewById(R.id.txtLozinka);
        najava=(Button)findViewById(R.id.button1);
        registracija=(Button)findViewById(R.id.button2);
        username=(EditText)findViewById(R.id.txtusername);
      
      
        
        registracija.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent nextScreen = new Intent(getApplicationContext(),Registracija.class);
				startActivity(nextScreen);
				
							}
		});
        final DatabaseHelper db = new DatabaseHelper(this);
        db.open();
    
        
        najava.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
								
				String password = lozinka.getText().toString();
				String username1 = username.getText().toString();
				
				if(db.Login(password,username1) >0 ){
					Intent nextScreen = new Intent(getApplicationContext(),MainActivity.class);
					nextScreen.putExtra("pass",lozinka.getText().toString());
					nextScreen.putExtra("pass1",username.getText().toString());
					startActivity(nextScreen);
					
				}else{
					
					AlertDialog.Builder b = new AlertDialog.Builder(ApplicationActivity.this);
					b.setTitle("Погрешна лознка или име");
					b.setMessage("Обидете се повторно");
					b.setPositiveButton("OK",null);
					AlertDialog dialog= b.show();
				}
				
			}
		});
        
        
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
    }

    public String getLozinka(){
    	EditText lozinka1 = (EditText)findViewById(R.id.txtLozinka);   	
    	return  lozinka1.getText().toString();
    }
    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
