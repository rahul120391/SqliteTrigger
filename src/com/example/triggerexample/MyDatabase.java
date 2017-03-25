package com.example.triggerexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper
{
    public static final String MYDATABASE_NAME="rahul.db";
    public static final String TABLE1="adding";
    public static final String TABLE2="adding1";
    public static final int version1=1;
    SQLiteDatabase _db;
    JavaBean bean;
    Context cnt1;
    public MyDatabase(Context cnt)
    {
    	super(cnt, MYDATABASE_NAME, null, version1);
    	cnt1=cnt;
    	
    }
	public MyDatabase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, MYDATABASE_NAME, null, version1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
          String sql1="create table adding (id Integer primary key,name text,number Integer);";
          String sql2="create table adding1(name text,number Integer);";
          String sql3="create trigger employee_add " +
          		"AFTER INSERT ON " +
          		"adding " +
          		"Begin " +
          		"Insert into adding1 values('rahul',8283931976);" +
          		"  END;";
          String sql4="create trigger employee_delete " +
          		"AFTER UPDATE ON " +
          		"adding " +
          		"BEGIN " +
          		"delete from adding1 where name='"+"rahul"+"';" +
          		"  END; ";
          db.execSQL(sql1);
          db.execSQL(sql2);
          db.execSQL(sql3);
          db.execSQL(sql4);
	}
   
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		System.out.println("inside on open");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
          db.execSQL("DROP TABLE IF EXISTS"+TABLE1);
          db.execSQL("DROP TABLE IF EXISTS"+TABLE2);
	}
}
