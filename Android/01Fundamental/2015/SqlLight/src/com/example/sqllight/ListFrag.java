package com.example.sqllight;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListFrag extends Fragment implements OnClickListener, OnItemClickListener {

	ListView lv;
	View view;
	int position;
	String save_str;
	
	public ListFrag() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list_layout, container, false);
		
		Button btn = (Button) view.findViewById(R.id.btn_search);
		lv = (ListView) view.findViewById(R.id.listView1);
		
		btn.setOnClickListener(this);
		lv.setOnItemClickListener(this);
		
		
		list();
		
		
		return view;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {		

		
		menu.add(0, 1, 0, "보기");
		menu.add(0, 2, 0, "삭제");
			
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		Toast.makeText(getActivity(), save_str + "==" + position, Toast.LENGTH_SHORT).show();
		registerForContextMenu(lv);
		
		return super.onContextItemSelected(item);
	}
	
	public void list(){
		Toast.makeText(getActivity(), "okay....", Toast.LENGTH_SHORT).show();
		DBHelper db = new DBHelper(getActivity(), "mydb", null, 1);
		EditText et = (EditText) view.findViewById(R.id.find_view);
		
		ArrayAdapter<String> adapter = null;
		
		ArrayList<Data> al = db.select(et.getText().toString());
		ArrayList<String> title = new ArrayList<String>();
		
		for(int i = 0; i< al.size(); i++){
			Data d = al.get(i);
			title.add(d.get_title());
		}
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, title);
		lv.setAdapter(adapter);
	}
	
	@Override
	public void onClick(View view) {
		list();		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		
		this.position = position;
		this.save_str = ((TextView) view).getText().toString();
		

	}

	
}
