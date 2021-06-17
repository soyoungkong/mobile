package com.example.sqllight;

import java.io.Serializable;

public class Data  implements Serializable{

	private String _id;
	private String _title;
	private String _doc;

	public Data() {
		this._id = "";
		this._title = "";
		this._doc = "";
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_doc() {
		return _doc;
	}

	public void set_doc(String _doc) {
		this._doc = _doc;
	}
	

}
