package kr.young.audioplayer;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		seek = (SeekBar) findViewById(R.id.seekBar1);
		info = (TextView) findViewById(R.id.textView1);
	}

	TextView info;
	SeekBar seek;
	int savepos = 0;
	int btnID;
	String playfile;
	MediaPlayer mp;
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int p = msg.arg1;
			seek.setProgress(savepos);
			info.setText("POSITION : " + savepos);
		}

	};
	

	class MyThread extends Thread{
		
		@Override
		public void run(){
			if(btnID == R.id.btn_play){
				seek.setProgress(savepos);
				seek.setMax(mp.getDuration());
			}
			
			mp.seekTo(seek.getProgress());
			//mp.seekTo(savepos);
			mp.start();
			//mp.selectTrack(savepos);
			//savepos = mp.getCurrentPosition();	// 미디어 플레이어의 현재값
			while(thread_flag){
				savepos = mp.getCurrentPosition();	
				
				if( savepos == mp.getDuration()){
					thread_flag = false;
				}
				else{
					Message m = new Message();
					m.arg1 = savepos;
					handler.sendMessage(m);
					
					try {
						thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			thread = null;
		}
	}
	
	MyThread thread;

	boolean thread_flag = false;
	
	
	public void play(View v) {
		if(thread == null){
			btnID = v.getId();
			thread_flag = true;
			mp = new MediaPlayer();
			try {
				mp.setDataSource(playfile);
				mp.prepare();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			thread = new MyThread();
			thread.start();
		}
	}

	public void stop(View v) {
		thread_flag = false;
		if(mp!=null){
			btnID = v.getId();
			
			mp.stop();
			mp.release();
			mp = null;
		}
	}

	public void pause(View v) {
		thread_flag = false;
		if(mp != null){
			mp.pause();
			savepos = 0;
		}
	}

	public void restart(View v) {
		if(thread == null ){
			btnID = v.getId();
			thread_flag = true;
			if(mp != null){
				play(v);
			}else{
				thread = new MyThread();
				thread.start();
			}
		}
	}

	@Override
	protected void onResume() {
		String playfile = getIntent().getStringExtra("playfile");
	    Toast.makeText(this, playfile, Toast.LENGTH_SHORT).show();
	    this.playfile = playfile;
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
