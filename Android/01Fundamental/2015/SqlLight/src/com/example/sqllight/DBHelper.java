package com.example.sqllight;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

	Context context;
	String dbName;
	int dbVersion;
	SQLiteDatabase db;
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
		this.context = context;
		this.dbName = name;
		this.dbVersion = version;
		
	}

	public DBHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		String sql = "create table if not exists board( " +
				" _id Integer primary key autoincrement, " +
				" _title varchar2(50), " +
				" _doc varchar2(300) )";
		
		db.execSQL(sql);
		
		for(int i=1; i<=30; i++){
	         String[] params = {"제목"+i, "내용"+i};
	         db.execSQL("insert into board(_title, _doc) values(?, ?)", params);
	      }
	}

	public ArrayList<Data> select(String findStr){
		ArrayList<Data> al = new ArrayList<Data>();
		
		//database를 읽기전용으로 
		db = getReadableDatabase();
		String sql = "select * " +
				" from board " +
				" where _title like ? or " +
				" _doc like ?";
		String[] params = {"%" + findStr + "%","%" + findStr + "%"};
		
		Cursor c = db.rawQuery(sql, params);
		while(c.moveToNext()){
			Data d = new Data();
			d.set_id(c.getString(0));
			d.set_title(c.getString(1));
			d.set_doc(c.getString(2));
			al.add(d);
		}
		
		return al;
		
	}
	
	public boolean insert(Data d){
		boolean flag = true;
		
		db = getWritableDatabase();
		
		String sql = "insert into board(_title,_doc) " +
				" values (?,?) ";
		
		String[] params = { d.get_title(), d.get_doc()};
		
		db.execSQL(sql, params);
		
		return flag;
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
