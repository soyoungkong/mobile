package kr.young.fragmentex;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThirdFragment extends Fragment {

	public ThirdFragment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.third_layout, container, false);
		
		return view;
	}
}
