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
		case R.id.btn_1: // �⺻��
			ad = new AlertDialog.Builder(this);
			ad.setTitle("�⺻�� ���̾�α�");
			ad.setMessage("�氡�氡");
			ad.setPositiveButton("Ȯ��", null);
			ad.show();
			break;
		case R.id.btn_2: // ��ư �̺�Ʈ
			ad = new AlertDialog.Builder(this);
			ad.setTitle("������ ������?");
			ad.setMessage("���� ���� �޴��� �������� �ұ��?");
			ad.setPositiveButton("�ƹ��ų�", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "ġ�÷�?!",
							Toast.LENGTH_SHORT).show();
				}
			});

			ad.setNeutralButton("�ʳ��Ծ�", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "���� �������̴�!!!",
							Toast.LENGTH_SHORT).show();
				}
			});

			ad.setNegativeButton("�ȸԾ�", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "�׷� ����",
							Toast.LENGTH_SHORT).show();
				}
			});
			ad.show();
			break;
		case R.id.btn_3: // üũ�ڽ�
			ad = new AlertDialog.Builder(this);

			final CharSequence[] chk_box = { "��⵵", "������", "����", "��û��", "���ֵ�" };
			final boolean[] flag = new boolean[chk_box.length];

			ad.setTitle("üũ�ڽ� ���̾�α�");
			ad.setIcon(R.drawable.about_512);
			ad.setMultiChoiceItems(chk_box, null,
					new DialogInterface.OnMultiChoiceClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which,
								boolean isChecked) {
							flag[which] = isChecked;
						}
					});
			ad.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
				
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
		case R.id.btn_4:	// ����� ������ ���̾�α�
			ad = new AlertDialog.Builder(this);
			LayoutInflater inf = getLayoutInflater();
			ViewGroup vg = (ViewGroup) findViewById(R.id.dialog_root);
			
			view = inf.inflate(R.layout.dialog_layout, vg);
			
			ad.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					EditText mid = (EditText) view.findViewById(R.id.txt_id);
					EditText pwd = (EditText) view.findViewById(R.id.txt_pw);
					String str = "�Էµ� ����- ���̵�: " + mid.getText().toString() +
							" ��й�ȣ: " + pwd.getText().toString();
					
					Toast.makeText(getApplicationContext(), str,
							Toast.LENGTH_SHORT).show();
				}
			});
			ad.setView(view);
			ad.show();
			
			break;
		case R.id.btn_5:
			final ProgressDialog pd1 = new ProgressDialog(this);
			pd1.setTitle("��Ʈ��ũ ������...");
			pd1.setMessage("���̵�� ��ȣ�� ���Źް� �ֽ��ϴ�.");
			pd1.setCancelable(true);
			pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd1.setMax(100);
			pd1.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					Toast.makeText(getApplicationContext(),
							"�۾��� ��ҵǾ����ϴ�.", Toast.LENGTH_SHORT)
							.show();	
				}
			});
			
			final Handler handler = new Handler(){	// ���꽺����� ���ο��� ���α׷����ٸ� �����ų �� ���� ������ ���⼭ �����Ŵ
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
		case R.id.btn_6: // ������
			ad = new AlertDialog.Builder(this);
			ad.setTitle("������ ���̾�α�");
			ad.setIcon(R.drawable.about_512);
			ad.setMessage("�ȵ���̵�?");
			ad.setPositiveButton("����ֽ��ϴ�!", null);
			ad.setNegativeButton("�Ⱦ��!", null);
			ad.setNeutralButton("�����׷���", null);
			ad.show();
			break;
		case R.id.btn_7:
			ad = new AlertDialog.Builder(this);
			final CharSequence[] items = { "¥��", "«��", "������", "ġ����", "õ����",
					"������", "ũ��" };
			ad.setTitle("������ ȸ�ĸ޴�");
			// ad.setMessage("���� �޴��� �ϳ��� �����ϼ���"); �޽��� ��� �������� ����
			ad.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(),
							"���õ� ȸ�� �޴��� " + items[which], Toast.LENGTH_SHORT)
							.show();
				}
			});
			ad.show();
			break;
		case R.id.btn_8:
			ad = new AlertDialog.Builder(this);
			final CharSequence[] radio = {"�","����","���","����ŷ","����","����","����"};
			
			ad.setTitle("��̻�Ȱ��?");
			ad.setIcon(R.drawable.about_512);
			ad.setSingleChoiceItems(radio, 0, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					pos = which;
				}
				
			});
			ad.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(),
							"���õ� ��̴� " + radio[pos], Toast.LENGTH_SHORT)
							.show();
				}
			});
			
			ad.show();
			break;
		case R.id.btn_9:
			final ProgressDialog pd2 = new ProgressDialog(this);
			pd2.setTitle("��Ʈ��ũ ������...");
			pd2.setMessage("���̵�� ��ȣ�� ���Źް� �ֽ��ϴ�.");
			pd2.setCancelable(true);
			pd2.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					Toast.makeText(getApplicationContext(),
							"�۾��� ��ҵǾ����ϴ�.", Toast.LENGTH_SHORT)
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
			
			ad.setPositiveButton("Ȯ��", null);
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
