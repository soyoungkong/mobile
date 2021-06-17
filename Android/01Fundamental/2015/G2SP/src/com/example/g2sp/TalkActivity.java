package com.example.g2sp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TalkActivity extends Activity{

	ImageView char_left;			// ĳ���� �̹��� ����
	ImageView char_right;			// ĳ���� �̹��� ������
	ImageView char_main;			// ĳ���� �̹��� (��ȥ)
	RelativeLayout layout;
	
	String left_char_name ="";
	String right_char_name ="";
	String back_img_name = "";
	
	int already_set_img = 0;
	TextView story_area;			// �ؽ�Ʈ ��( ���丮 �ؽ�Ʈ ������ �κ�)
	
	Intent intent;		// ���� �޾ƿ������� ��Ƽ��Ƽ
	
	HashMap<Integer, String[]> story_txt_list;			// ���丮 �ؽø�
	HashMap<String, Integer> char_img_list;			// ĳ���� �̹��� �ؽø�
	HashMap<String, Integer> back_img_list;			// ��� �̹��� �ؽø�
	
	int story_level = 0;				// ���θ� ���൵
	int story_line = 0;				// �� ���丮 ���൵�� ���� ���丮 ������ ����
	int story_text_count = 0;		// �� ���丮 ���ο� ���ִ� ������ ���� 
	String[] story_arr;				// ������ �´� ���丮�� ����Ǵ� ����
	String story_char = "";			// ���ڸ� �ޱ����� ����
	String open_char_name = "";			// �����ų ĳ���� �̸��� �ޱ����� ����
	String open_back_name = "";			// ����� �̸��� �ޱ����� ����
	
	boolean char_set_count = false;				// ĳ���͸� �����Ű�� ���� ����
	boolean back_set_count = false;			// ����� �����Ű�� ���� ����
	
	CountDownTimer get_text;	// ���ڸ� �ѱ��ھ� �Ѹ������� Ÿ�̸� ����
	
	Button btn_go_next_story;	// next��ư
	
	Thread thread1;				// ������ ������ ���� ������
	Runnable uiRunnable;		// �����͸� ȭ�鿡 �ѷ��ֱ����� ������
	
	int battle_count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talk);		// talk ���̾ƿ� ����
		
		intent = getIntent();		// ���� ��Ƽ��Ƽ���� ���� �޾ƿ�(���丮 �ؽø�, ĳ���� �̹��� �ؽø�)
		
		story_txt_list = (HashMap<Integer, String[]>) intent.getExtras().getSerializable("story_txt_list");	// ���丮 �ؽø� �޴� �κ�
		char_img_list = (HashMap<String, Integer>) intent.getExtras().getSerializable("char_img_list");	// ĳ���� �̹��� �ؽø� �޴� �κ�
		back_img_list = (HashMap<String, Integer>) intent.getExtras().getSerializable("back_img_list");	// ��� �̹��� �ؽø� �޴� �κ�
		
		layout = (RelativeLayout) findViewById(R.id.talk_back_layout);	// ���̾ƿ� 
		char_left = (ImageView) findViewById(R.id.char_left);				// ĳ���� �̹��� ����
		char_right = (ImageView) findViewById(R.id.char_right);			// ĳ���� �̹��� ������
		char_main = (ImageView) findViewById(R.id.char_main);			// ĳ���� �̹��� (��ȥ)
		
		btn_go_next_story = (Button) findViewById(R.id.btn_goNextStory);
		
		story_area = (TextView) findViewById(R.id.story_area);			// �ؽ�Ʈ ��( ���丮 �ؽ�Ʈ ������ �κ�)
		story_area.setMovementMethod(new ScrollingMovementMethod());
		story_area.setVisibility(View.VISIBLE);								// �ؽ�Ʈ�� �ʱ�ȭ
		story_area.setText(" ");													// �ؽ�Ʈ�� �ʱ�ȭ
		
		//story_arr = story_txt_list.get(story_level);			// ���丮 ������ ���� ���丮 ����
		story_arr = story_txt_list.get(5);	// �̹��� �׽�Ʈ�� ���� ���丮���� ���� 3
	}
	
	public void nextStory(View v){

		story_area.setText(" ");						// �ؽ�Ʈ�� �ʱ�ȭ
		
		if(story_arr.length == story_line){		// ���丮������ �Ϸ�� ���� ���丮�� ������ ���� ����
			story_level++;							// ���丮������ ����
			story_line = 0;							// ���丮 ������ �ʱ�ȭ
			
			left_char_name ="";
			right_char_name ="";					// ���ο� ���丮 ������ ���� ���� ����� ĳ���� �̸��� �ʱ�ȭ
			back_img_name = "";
			
			char_left.setImageResource(R.drawable.null_img);		// ĳ���� �̸��� �ʱ�ȭ
			char_right.setImageResource(R.drawable.null_img);
			
			story_arr = story_txt_list.get(story_level);	// ���丮 ������ ������Ű�� ��� �ؽ�Ʈ�� �ѱ��
			
		}
		
		uiRunnable = new Runnable(){
            @Override
             public void run() {
            	
            	if(story_char.equals("$")){
            		battle_count++;
            	}
            	else{
            		if(!back_set_count){
            			if(!story_char.equals("@")){
            				story_area.append(story_char);	// �ѱ��ھ� story_area�� �ѷ���
            			}
            		}
            	}
            	
            	if(story_char.equals("}")){
            		layout.setBackgroundResource(back_img_list.get(open_back_name));
            		back_set_count = false;	// ��� �̸� ������ ����
            	}
            	
            	if(story_char.equals("]")){
            		try {	// ���� ĳ���Ͱ� 3�����̱⶧���� �ٸ� ĳ���Ͱ� �����ϸ� ������ �߻� ��ų �� �ִ�
						
            			if(open_char_name.equals("����") || open_char_name.equals("�Ⱥ��� ����") || open_char_name.equals("����@")){
            				if(already_set_img == 1 && !left_char_name.equals(open_char_name) ){
	            				left_char_name = open_char_name;		// ���ʿ� ĳ���� �̸� ����
	            				char_left.setImageResource(char_img_list.get(open_char_name));
            				}
            			}
            			else if(open_char_name.equals("������") || open_char_name.equals("��������")){
            				if(already_set_img == 2 && !right_char_name.equals(open_char_name) ){
	            				right_char_name = open_char_name;	// �����ʿ� ĳ���� �̸� ����
	            				char_right.setImageResource(char_img_list.get(open_char_name));
            				}
            			}
            			
						/*if(already_set_img == 1){	// ���ʿ� ĳ���Ͱ� �̹� �ִٸ� �����ʿ� ĳ���͸� ����
							left_char_name = open_char_name;		// ���ʿ� ĳ���� �̸� ����
							char_left.setImageResource(char_img_list.get(open_char_name));		// ���ʿ� �̹����� ����
						}
						else if(already_set_img == 2){
							right_char_name = open_char_name;	// �����ʿ� ĳ���� �̸� ����
							char_right.setImageResource(char_img_list.get(open_char_name));	// �����ʿ� �̹����� ����
							already_set_img = 0;
						}
						else if(already_set_img == 3){
							left_char_name = open_char_name;	// �����ʿ� ĳ���� �̸� ����
							char_left.setImageResource(char_img_list.get(open_char_name));	// ���ʿ� �̹����� ����
							already_set_img = 0;
						}
						else{	// 0���̸� �н�
							
						}*/
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					char_set_count = false;	// ĳ���� �̸� ������ ����
            	}
            	
            	if(story_arr[story_line].length() == story_text_count){
    				story_area.append("\n");
    				btn_go_next_story.setVisibility(View.VISIBLE);	// �ؽ�Ʈ �Ѹ��Ⱑ ����Ǿ��� ������ ���� ������ ���� ��ư�� ������ 
        		}
            	
            	 synchronized ( this )
                 {
                     this.notify();
                 }
             }
        };	//	set uiRunnable
        
        thread1 = new Thread(new Runnable() {
		    @Override
		    public  void run() {    
		    	
		    	synchronized (uiRunnable){
			    	while(true){
						
						if(story_arr[story_line].length() == story_text_count){	// ���丮 ������ �̾߱Ⱑ ���� ������ٸ� �ʱ�ȭ�۾�
		        			
		    				story_char = ""; // ���ڸ� �޴� ���� �ʱ�ȭ
		    				char_set_count = false;	// ĳ���� �̸��� �ޱ����� ���� �ʱ�ȭ
		    				back_set_count = false;	//��� �̸��� �ޱ����� ���� �ʱ�ȭ
		    				open_char_name = "";		// ĳ���� �̸��� �޴� ���� �ʱ�ȭ
		    				open_back_name = "";		// ��� �̸��� �޴� ���� �ʱ�ȭ
		    				story_text_count = 0;		// ���� ���丮 �ؽ�Ʈ ������ ���� ī��Ʈ �ʱ�ȭ
		    				already_set_img = 0;
		    				
		    				story_line++; 		// ���� ���丮�� ���������� ���丮���� +1
		    				
		        			break;
		        		}
		        		else{
		    			
		    				story_char = String.valueOf(story_arr[story_line].charAt(story_text_count));
		    				
		    				if(back_set_count){		// true ���¿��� �ѷ��� ����� �̸��� ����
		    					
		    					if(!story_char.equals("}")){	
		    						open_back_name += story_char;		// ����� �̸��� ����
		    					}
		    				}
		    				
		    				if(story_char.equals("{")){				// "{" �� ������ ���� ��������� ����� �̸��� ����
		    					back_set_count = true;
		    				}
		    				
		    				
		    				
		    				if(char_set_count){		// true ���¿��� �����ų ĳ������ �̸��� ����
		    					
		    					if(!story_char.equals("]")){	
		    						open_char_name += story_char;		// �����ų ĳ������ �̸��� ����
		    					}
		    				}
		    				
		    				if(story_char.equals("[")){				// "[" �� ������ ���� Ÿ�̸Ӻ��� ĳ���� �̸��� ����
		    					char_set_count = true;
		    				}
		    				else if(story_char.equals("]")){			// "]" �� ������ ĳ���͸� �����Ŵ
		    					
		    					
		    					if(open_char_name.equals("����") || open_char_name.equals("�Ⱥ��� ����") || open_char_name.equals("����@")){
		    						if(!open_char_name.equals(left_char_name)){
		    							already_set_img = 1;	
		    						}
		            			}
		            			else if(open_char_name.equals("������") || open_char_name.equals("��������")){
		            				if(!open_char_name.equals(right_char_name)){
		            					already_set_img = 2;
		    						}
		            			}
		            			else{
		            			}
//		    					if(left_char_name.equals("")){
//		    						already_set_img = 1;	
//		    					}
//		    					else if(right_char_name.equals("")){
//		    						already_set_img = 2;		// ���ʿ� ��ġ�� ĳ���� ���̵�� ������ �н�
//		    					}
//		    					else if(left_char_name.equals(open_char_name)){
//		    						already_set_img = 0;		// ���ʿ� ��ġ�� ĳ���� ���̵�� ������ �н�
//		    					}
//		    					else if(right_char_name.equals(open_char_name)){
//		    						already_set_img = 0;		// �����ʿ�  ��ġ�� ĳ���� ���̵�� ������ �н�
//		    					}
//		    					else {
//		    						already_set_img++;			// �� �� �ƴ϶�� ���ʿ� ��ġ ������ ĳ���Ͱ� �� �� �ƴ϶�� ������ ��ġ
//		    					}
		    					
		    				}
		    				
		    				story_text_count++;		// ���� ĳ���� ���ڸ� ���� ���� ���� �ѱ�
		        		}
		        		
						TalkActivity.this.runOnUiThread(uiRunnable); 
		    			
				        try {
				        	uiRunnable.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	
			    	}// while close
			    	
		    	}// synchronized close
		    }
		    
		});	// set thread1
		thread1.start();
		
		btn_go_next_story.setVisibility(View.INVISIBLE);	// �ؽ�Ʈ�� ������ �� �ѱⰡ �ϱ����� ��ư�� ����
		if(battle_count == 2){
			// go battle activity
		}
	}
    
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.talk, menu);
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
