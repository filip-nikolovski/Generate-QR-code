package com.example.bmt_proekt;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PodatociBaza1 extends Activity implements OnClickListener {

	DatabaseHelper dbh = new DatabaseHelper(this);
	Button button;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.izgled1);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo1);
		DatabaseHelper info = new DatabaseHelper(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
	
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}



}
