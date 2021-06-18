package kr.young.dialogex;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	int pos = 0;
	View view = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void viewDialog(View v) {
		int id = v.getId();

		AlertDialog.Builder ad = null;
		switch (id) {
		case R.id.btn_1: // 기본형
			ad = new AlertDialog.Builder(this);
			ad.setTitle("기본형 다이얼로그");
			ad.setMessage("방가방가");
			ad.setPositiveButton("확인", null);
			ad.show();
			break;
		case R.id.btn_2: // 버튼 이벤트
			ad = new AlertDialog.Builder(this);
			ad.setTitle("오늘의 점심은?");
			ad.setMessage("오늘 점심 메뉴는 무엇으로 할까요?");
			ad.setPositiveButton("아무거나", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "치플레?!",
							Toast.LENGTH_SHORT).show();
				}
			});

			ad.setNeutralButton("너나먹어", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "내가 식인종이다!!!",
							Toast.LENGTH_SHORT).show();
				}
			});

			ad.setNegativeButton("안먹어", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "그럼 굶어",
							Toast.LENGTH_SHORT).show();
				}
			});
			ad.show();
			break;
		case R.id.btn_3: // 체크박스
			ad = new AlertDialog.Builder(this);

			final CharSequence[] chk_box = { "경기도", "강원도", "전라도", "충청도", "제주도" };
			final boolean[] flag = new boolean[chk_box.length];

			ad.setTitle("체크박스 다이얼로그");
			ad.setIcon(R.drawable.about_512);
			ad.setMultiChoiceItems(chk_box, null,
					new DialogInterface.OnMultiChoiceClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which,
								boolean isChecked) {
							flag[which] = isChecked;
						}
					});
			ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i< chk_box.length; i++){
						if(flag[i]){
							sb.append(chk_box[i] + " ");
						}
					}
					Toast.makeText(getApplicationContext(), sb.toString(),
							Toast.LENGTH_SHORT).show();
				}
			});
			ad.show();

			break;
		case R.id.btn_4:	// 사용자 유형의 다이얼로그
			ad = new AlertDialog.Builder(this);
			LayoutInflater inf = getLayoutInflater();
			ViewGroup vg = (ViewGroup) findViewById(R.id.dialog_root);
			
			view = inf.inflate(R.layout.dialog_layout, vg);
			
			ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					EditText mid = (EditText) view.findViewById(R.id.txt_id);
					EditText pwd = (EditText) view.findViewById(R.id.txt_pw);
					String str = "입력된 정보- 아이디: " + mid.getText().toString() +
							" 비밀번호: " + pwd.getText().toString();
					
					Toast.makeText(getApplicationContext(), str,
							Toast.LENGTH_SHORT).show();
				}
			});
			ad.setView(view);
			ad.show();
			
			break;
		case R.id.btn_5:
			final ProgressDialog pd1 = new ProgressDialog(this);
			pd1.setTitle("네트워크 접속중...");
			pd1.setMessage("아이디와 암호를 수신받고 있습니다.");
			pd1.setCancelable(true);
			pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd1.setMax(100);
			pd1.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					Toast.makeText(getApplicationContext(),
							"작업이 취소되었습니다.", Toast.LENGTH_SHORT)
							.show();	
				}
			});
			
			final Handler handler = new Handler(){	// 서브스레드로 메인에서 프로그래스바를 변경시킬 수 없기 때문에 여기서 변경시킴
				@Override
				public void handleMessage(Message msg) {
					pd1.setProgress(msg.arg1);
					super.handleMessage(msg);
				}
			};
			
			class PdThread extends Thread{
				public void run(){
					while(pd1.getProgress()<100){
						int val = pd1.getProgress() +2;
						Message m = new Message();
						m.arg1 = val;
						handler.sendMessage(m);
						
						try {
							Thread.sleep(100);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			}
			
			pd1.show();
			new PdThread().start();
			
			
			break;
		case R.id.btn_6: // 아이콘
			ad = new AlertDialog.Builder(this);
			ad.setTitle("아이콘 다이얼로그");
			ad.setIcon(R.drawable.about_512);
			ad.setMessage("안드로이드?");
			ad.setPositiveButton("재미있습니다!", null);
			ad.setNegativeButton("싫어요!", null);
			ad.setNeutralButton("그저그래요", null);
			ad.show();
			break;
		case R.id.btn_7:
			ad = new AlertDialog.Builder(this);
			final CharSequence[] items = { "짜장", "짬뽕", "군만두", "치폴레", "천국장",
					"탕수육", "크롬" };
			ad.setTitle("오늘의 회식메뉴");
			// ad.setMessage("각자 메뉴를 하나씩 선택하세요"); 메시지 대신 아이템이 나옴
			ad.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(),
							"선택된 회식 메뉴는 " + items[which], Toast.LENGTH_SHORT)
							.show();
				}
			});
			ad.show();
			break;
		case R.id.btn_8:
			ad = new AlertDialog.Builder(this);
			final CharSequence[] radio = {"운동","낚시","등산","하이킹","독서","낮잠","게임"};
			
			ad.setTitle("취미생활은?");
			ad.setIcon(R.drawable.about_512);
			ad.setSingleChoiceItems(radio, 0, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					pos = which;
				}
				
			});
			ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(),
							"선택된 취미는 " + radio[pos], Toast.LENGTH_SHORT)
							.show();
				}
			});
			
			ad.show();
			break;
		case R.id.btn_9:
			final ProgressDialog pd2 = new ProgressDialog(this);
			pd2.setTitle("네트워크 접속중...");
			pd2.setMessage("아이디와 암호를 수신받고 있습니다.");
			pd2.setCancelable(true);
			pd2.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					Toast.makeText(getApplicationContext(),
							"작업이 취소되었습니다.", Toast.LENGTH_SHORT)
							.show();	
				}
			});
			pd2.show();
			break;
		case R.id.btn_10:
			ad = new AlertDialog.Builder(this);
			LayoutInflater inf2 = getLayoutInflater();
			ViewGroup vg2 = (ViewGroup) findViewById(R.id.Picker_root);
			
			view = inf2.inflate(R.layout.dialog_in_picker, vg2);
			
			ad.setPositiveButton("확인", null);
			ad.setView(view);
			ad.show();
			break;

		}
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
