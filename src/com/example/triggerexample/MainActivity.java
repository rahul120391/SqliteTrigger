package com.example.triggerexample;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	Button btn_submit,btn_fetch,btn_update;
	SQLiteDatabase _db;
	EditText et_id,et_name,et_number;
	MyDatabase _dataDatabase;
	String name,number;
	JavaBean bean;
	Cursor cr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_dataDatabase=new MyDatabase(MainActivity.this);
		btn_submit=(Button)findViewById(R.id.btn_submit);
		btn_fetch=(Button)findViewById(R.id.btn_fetch);
		btn_update=(Button)findViewById(R.id.btn_update);
		et_id=(EditText)findViewById(R.id.et_id);
		et_name=(EditText)findViewById(R.id.et_name);
		et_number=(EditText)findViewById(R.id.et_number);
		btn_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{

                if(et_name.getText().toString().equalsIgnoreCase(""))
               {
            	   Toast.makeText(MainActivity.this,"please fill name", Toast.LENGTH_SHORT).show();
               }
               else if(et_number.getText().toString().equalsIgnoreCase(""))
               {
            	   Toast.makeText(MainActivity.this,"please fill number", Toast.LENGTH_SHORT).show();
               }
               else
               {
            	   bean=new JavaBean();
            	   bean.setNAME(et_name.getText().toString());
            	   bean.setNumber(et_number.getText().toString());
            	   insert_data();
            	   et_id.setText("");
            	   et_name.setText("");
            	   et_number.setText("");
               }
			}
		});
		btn_fetch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(et_id.getText().toString().equalsIgnoreCase(""))
				{
					Toast.makeText(MainActivity.this,"please enter id",Toast.LENGTH_SHORT).show();
				}
				else
				{
					fetch();
					et_name.setText("");
					et_number.setText("");
					et_name.setText(name);
					et_number.setText(number);
				}
			}
		});
		btn_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				updatedata();
				et_id.setText("");
				et_name.setText("");
				et_number.setText("");
			}
		});
	}
	public void insert_data()
	{
		_db=_dataDatabase.getWritableDatabase();
		ContentValues cnt=new ContentValues();
		cnt.put("name",bean.getName());
		cnt.put("number",bean.getNumber());
		_db.insert(MyDatabase.TABLE1,null,cnt);
		_db.close();
		Toast.makeText(MainActivity.this,"data has been saved",Toast.LENGTH_SHORT).show();
	}
   public void fetch()
   {
	   _db=_dataDatabase.getReadableDatabase();
	   String sql="select * from adding where id='"+et_id.getText().toString()+"'";
	   cr=_db.rawQuery(sql,null);
	   if(cr!=null)
	   {
		   if(cr.moveToFirst())
		   {
			   do
			   {
				   name=cr.getString(1);
				   number=cr.getString(2);
			   }
			   while(cr.moveToNext());
		   }
		   else
		   {
			   Toast.makeText(MainActivity.this,"no value in the database",Toast.LENGTH_SHORT).show();
		   }
	   }
	   else
	   {
		   Toast.makeText(MainActivity.this,"no value in the database",Toast.LENGTH_SHORT).show();
	   }
	   cr.close();
	  _db.close();
   }
  public void updatedata()
  {
	  _db=_dataDatabase.getWritableDatabase();
	  ContentValues cnt=new ContentValues();
	  cnt.put("name",et_name.getText().toString());
	  cnt.put("number",et_number.getText().toString());
	  _db.update("adding",cnt,"id='"+et_id.getText().toString()+"'", null);
	  _db.close();
	  Toast.makeText(MainActivity.this,"adding table updated",Toast.LENGTH_SHORT).show();
  }
}
