package com.example.bmt_proekt;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class Registracija2 extends Activity implements OnClickListener{
	
	//TextView proba;
	EditText adresa,grad,password,idTelefon,username;
	String s1,s2,s3,s4;
	Button nezadolzitelno,qr;
	
	@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.registracija2);
	
	//ScrollView sView = (ScrollView)findViewById(R.id.s)
	
	
	Intent i=getIntent();
    s1=i.getStringExtra("ime");
    s2=i.getStringExtra("prezime");
    s3=i.getStringExtra("maticen");
    s4=i.getStringExtra("date");
    
	adresa=(EditText)findViewById(R.id.editText1);
	grad=(EditText)findViewById(R.id.editText2);
	password=(EditText)findViewById(R.id.editText4);
	username=(EditText)findViewById(R.id.editUsername);
	idTelefon=(EditText)findViewById(R.id.editText5);
	//nezadolzitelno=(Button)findViewById(R.id.button2);
//	qr=(Button)findViewById(R.id.qr);
	//proba = (TextView)findViewById(R.id.proba);
	
	DatabaseHelper hel = new DatabaseHelper(Registracija2.this);
	hel.open();
	 final int num = hel.checjUsername(username.getText().toString());
	
	//proba.setText(s1);
	
	Button zacuvaj=(Button)findViewById(R.id.button1);
	zacuvaj.setOnClickListener(new OnClickListener() {
		
		
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			try{
			DatabaseHelper db=new DatabaseHelper(Registracija2.this);
			db.open();
		
			
			if(db.checjUsername(username.getText().toString())>0){
				AlertDialog.Builder a = new AlertDialog.Builder(Registracija2.this);
				a.setTitle("Зафатено корисничко име");
				a.setMessage("Избери ново");
				a.setPositiveButton("OK",null);
				AlertDialog dialog= a.show();
				
			}else if(password.getText().toString().equals("") || username.getText().toString().equals("")){
				
				AlertDialog.Builder a = new AlertDialog.Builder(Registracija2.this);
				a.setTitle("Празно корисничко име или лозинка");
				a.setMessage("Внеси ги податоците");
				a.setPositiveButton("OK",null);
				AlertDialog dialog= a.show();
			}
			
			
			else {
				 
				String name=s1;
				String surname=s2;
				String embg=s3;
				String dateBirth=s4;
				String street=adresa.getText().toString();
				String city=grad.getText().toString();
				String username1 = username.getText().toString();
				String password1=password.getText().toString();
				String PhoneID=idTelefon.getText().toString();
							
				DatabaseHelper entry=new DatabaseHelper(Registracija2.this);
					entry.open();
				entry.createEntry(name, surname, embg,dateBirth, street, city, username1, password1, PhoneID);
				entry.close();
				db.close();
				
				AlertDialog.Builder b = new AlertDialog.Builder(Registracija2.this);
				b.setTitle("Снимано");
				b.setMessage("Вашите податоци се успешно зачувани");
				b.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						Intent data = new Intent(getApplicationContext(),MainActivity.class);
						data.putExtra("ime", password.getText().toString());
						data.putExtra("Ime1", username.getText().toString());
						startActivity(data);
					}
				} );
				
				AlertDialog dialog= b.create();
				dialog.show();
				
			}
			}catch (Exception e) {
				// TODO: handle exception
				String error= e.toString();
				Dialog d = new Dialog(Registracija2.this);
				d.setTitle("NEUSPESNO");
				TextView tv=new TextView(Registracija2.this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			
		
			
		}});
	
	
	// KODOT ZA BAZATA
	
	//KOPCE ZA POGLED NA BAZATA
//	nezadolzitelno.setOnClickListener(new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			Intent pogled=new Intent(getApplicationContext(),PodatociBaza.class);
//			startActivity(pogled);
//		}
//	});
//	
//	
	
	
	
	
	


	//go naogja imei na telefonot i go pecati vo text poleto
	//
	TelephonyManager tm = (TelephonyManager) getSystemService(android.content.Context.TELEPHONY_SERVICE);
	idTelefon.setText(tm.getDeviceId());

	
	
	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	
	}
	



	
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	
}
}
