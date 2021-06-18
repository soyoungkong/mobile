package kr.young.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragIn extends Fragment {

	public FragIn() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragin, container, false);
		
		return view;
	}
}
