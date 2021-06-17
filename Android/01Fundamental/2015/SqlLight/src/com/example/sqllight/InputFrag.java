package com.example.sqllight;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputFrag extends Fragment implements OnClickListener{

	View view;
	
	public InputFrag() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.input_layout, container, false);

		Button b = (Button) view.findViewById(R.id.btn_save);
		
		b.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		
		DBHelper db =  new DBHelper(getActivity(), "mydb", null, 1);
		Data data =  new Data();
		
		EditText et_title = (EditText) view.findViewById(R.id.title);
		EditText et_doc = (EditText) view.findViewById(R.id.doc);
		
		data.set_title(et_title.getText().toString());
		data.set_doc(et_doc.getText().toString());
		
		boolean flag = db.insert(data);
		
		if(flag){
			Toast.makeText(getActivity(), "저장 성공", Toast.LENGTH_SHORT).show();
			et_title.setText("");
			et_doc.setText("");
			et_title.requestFocus();
		}
		else{
			Toast.makeText(getActivity(), "저장 실패", Toast.LENGTH_SHORT).show();
			et_title.requestFocus();
		}
		
		
		
	}
}
