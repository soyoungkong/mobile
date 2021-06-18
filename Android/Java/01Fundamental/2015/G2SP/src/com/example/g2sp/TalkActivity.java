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

	ImageView char_left;			// 캐릭터 이미지 왼쪽
	ImageView char_right;			// 캐릭터 이미지 오른쪽
	ImageView char_main;			// 캐릭터 이미지 (석혼)
	RelativeLayout layout;
	
	String left_char_name ="";
	String right_char_name ="";
	String back_img_name = "";
	
	int already_set_img = 0;
	TextView story_area;			// 텍스트 뷰( 스토리 텍스트 나오는 부분)
	
	Intent intent;		// 값을 받아오기위한 액티비티
	
	HashMap<Integer, String[]> story_txt_list;			// 스토리 해시맵
	HashMap<String, Integer> char_img_list;			// 캐릭터 이미지 해시맵
	HashMap<String, Integer> back_img_list;			// 배경 이미지 해시맵
	
	int story_level = 0;				// 스로리 진행도
	int story_line = 0;				// 각 스토리 진행도에 따른 스토리 라인의 개수
	int story_text_count = 0;		// 각 스토리 라인에 들어가있는 글자의 개수 
	String[] story_arr;				// 레벨에 맞는 스토리가 저장되는 변수
	String story_char = "";			// 문자를 받기위한 변수
	String open_char_name = "";			// 등장시킬 캐릭터 이름을 받기위한 변수
	String open_back_name = "";			// 배경의 이름을 받기위한 변수
	
	boolean char_set_count = false;				// 캐릭터를 등장시키기 위한 변수
	boolean back_set_count = false;			// 배경을 변경시키기 위한 변수
	
	CountDownTimer get_text;	// 글자를 한글자씩 뿌리기위한 타이머 변수
	
	Button btn_go_next_story;	// next버튼
	
	Thread thread1;				// 데이터 연산을 위한 스레드
	Runnable uiRunnable;		// 데이터를 화면에 뿌려주기위한 스레드
	
	int battle_count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talk);		// talk 레이아웃 생성
		
		intent = getIntent();		// 이전 액티비티에서 값을 받아옴(스토리 해시맵, 캐릭터 이미지 해시맵)
		
		story_txt_list = (HashMap<Integer, String[]>) intent.getExtras().getSerializable("story_txt_list");	// 스토리 해시맵 받는 부분
		char_img_list = (HashMap<String, Integer>) intent.getExtras().getSerializable("char_img_list");	// 캐릭터 이미지 해시맵 받는 부분
		back_img_list = (HashMap<String, Integer>) intent.getExtras().getSerializable("back_img_list");	// 배경 이미지 해시맵 받는 부분
		
		layout = (RelativeLayout) findViewById(R.id.talk_back_layout);	// 레이아웃 
		char_left = (ImageView) findViewById(R.id.char_left);				// 캐릭터 이미지 왼쪽
		char_right = (ImageView) findViewById(R.id.char_right);			// 캐릭터 이미지 오른쪽
		char_main = (ImageView) findViewById(R.id.char_main);			// 캐릭터 이미지 (석혼)
		
		btn_go_next_story = (Button) findViewById(R.id.btn_goNextStory);
		
		story_area = (TextView) findViewById(R.id.story_area);			// 텍스트 뷰( 스토리 텍스트 나오는 부분)
		story_area.setMovementMethod(new ScrollingMovementMethod());
		story_area.setVisibility(View.VISIBLE);								// 텍스트뷰 초기화
		story_area.setText(" ");													// 텍스트뷰 초기화
		
		//story_arr = story_txt_list.get(story_level);			// 스토리 레벨에 따른 스토리 셋팅
		story_arr = story_txt_list.get(5);	// 이미지 테스트를 위한 스토리레벨 셋팅 3
	}
	
	public void nextStory(View v){

		story_area.setText(" ");						// 텍스트뷰 초기화
		
		if(story_arr.length == story_line){		// 스토리라인의 완료와 다음 스토리의 진행을 위한 조건
			story_level++;							// 스토리레벨을 증가
			story_line = 0;							// 스토리 라인을 초기화
			
			left_char_name ="";
			right_char_name ="";					// 새로운 스토리 진행을 위해 기존 저장된 캐릭터 이름을 초기화
			back_img_name = "";
			
			char_left.setImageResource(R.drawable.null_img);		// 캐릭터 이름을 초기화
			char_right.setImageResource(R.drawable.null_img);
			
			story_arr = story_txt_list.get(story_level);	// 스토리 레벨을 증가시키고 계속 텍스트를 넘긴다
			
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
            				story_area.append(story_char);	// 한글자씩 story_area에 뿌려줌
            			}
            		}
            	}
            	
            	if(story_char.equals("}")){
            		layout.setBackgroundResource(back_img_list.get(open_back_name));
            		back_set_count = false;	// 배경 이름 저장이 종료
            	}
            	
            	if(story_char.equals("]")){
            		try {	// 현재 캐릭터가 3개뿐이기때문에 다른 캐릭터가 등장하면 에러를 발생 시킬 수 있다
						
            			if(open_char_name.equals("영우") || open_char_name.equals("안보노 선생") || open_char_name.equals("영우@")){
            				if(already_set_img == 1 && !left_char_name.equals(open_char_name) ){
	            				left_char_name = open_char_name;		// 왼쪽에 캐릭터 이름 저장
	            				char_left.setImageResource(char_img_list.get(open_char_name));
            				}
            			}
            			else if(open_char_name.equals("태훈형") || open_char_name.equals("떡집누나")){
            				if(already_set_img == 2 && !right_char_name.equals(open_char_name) ){
	            				right_char_name = open_char_name;	// 오른쪽에 캐릭터 이름 저장
	            				char_right.setImageResource(char_img_list.get(open_char_name));
            				}
            			}
            			
						/*if(already_set_img == 1){	// 왼쪽에 캐릭터가 이미 있다면 오른쪽에 캐릭터를 셋팅
							left_char_name = open_char_name;		// 왼쪽에 캐릭터 이름 저장
							char_left.setImageResource(char_img_list.get(open_char_name));		// 왼쪽에 이미지를 셋팅
						}
						else if(already_set_img == 2){
							right_char_name = open_char_name;	// 오른쪽에 캐릭터 이름 저장
							char_right.setImageResource(char_img_list.get(open_char_name));	// 오른쪽에 이미지를 셋팅
							already_set_img = 0;
						}
						else if(already_set_img == 3){
							left_char_name = open_char_name;	// 오른쪽에 캐릭터 이름 저장
							char_left.setImageResource(char_img_list.get(open_char_name));	// 왼쪽에 이미지를 셋팅
							already_set_img = 0;
						}
						else{	// 0번이면 패스
							
						}*/
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					char_set_count = false;	// 캐릭터 이름 저장이 종료
            	}
            	
            	if(story_arr[story_line].length() == story_text_count){
    				story_area.append("\n");
    				btn_go_next_story.setVisibility(View.VISIBLE);	// 텍스트 뿌리기가 종료되었기 때문에 다음 진행을 위해 버튼을 보여줌 
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
						
						if(story_arr[story_line].length() == story_text_count){	// 스토리 레벨의 이야기가 전부 진행됬다면 초기화작업
		        			
		    				story_char = ""; // 문자를 받는 변수 초기화
		    				char_set_count = false;	// 캐릭터 이름을 받기위한 변수 초기화
		    				back_set_count = false;	//배경 이름을 받기위한 변수 초기화
		    				open_char_name = "";		// 캐릭터 이름을 받는 변수 초기화
		    				open_back_name = "";		// 배경 이름을 받는 변수 초기화
		    				story_text_count = 0;		// 다음 스토리 텍스트 저장을 위해 카운트 초기화
		    				already_set_img = 0;
		    				
		    				story_line++; 		// 다음 스토리를 얻어오기위해 스토리라인 +1
		    				
		        			break;
		        		}
		        		else{
		    			
		    				story_char = String.valueOf(story_arr[story_line].charAt(story_text_count));
		    				
		    				if(back_set_count){		// true 상태에서 뿌려줄 배경의 이름을 저장
		    					
		    					if(!story_char.equals("}")){	
		    						open_back_name += story_char;		// 배경의 이름을 저장
		    					}
		    				}
		    				
		    				if(story_char.equals("{")){				// "{" 가 나오면 다음 스래드부터 배경의 이름을 저장
		    					back_set_count = true;
		    				}
		    				
		    				
		    				
		    				if(char_set_count){		// true 상태에서 등장시킬 캐릭터의 이름을 저장
		    					
		    					if(!story_char.equals("]")){	
		    						open_char_name += story_char;		// 등장시킬 캐릭터의 이름을 저장
		    					}
		    				}
		    				
		    				if(story_char.equals("[")){				// "[" 가 나오면 다음 타이머부터 캐릭터 이름을 저장
		    					char_set_count = true;
		    				}
		    				else if(story_char.equals("]")){			// "]" 가 나오면 캐릭터를 등장시킴
		    					
		    					
		    					if(open_char_name.equals("영우") || open_char_name.equals("안보노 선생") || open_char_name.equals("영우@")){
		    						if(!open_char_name.equals(left_char_name)){
		    							already_set_img = 1;	
		    						}
		            			}
		            			else if(open_char_name.equals("태훈형") || open_char_name.equals("떡집누나")){
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
//		    						already_set_img = 2;		// 왼쪽에 배치한 캐릭터 아이디와 같으면 패스
//		    					}
//		    					else if(left_char_name.equals(open_char_name)){
//		    						already_set_img = 0;		// 왼쪽에 배치한 캐릭터 아이디와 같으면 패스
//		    					}
//		    					else if(right_char_name.equals(open_char_name)){
//		    						already_set_img = 0;		// 오른쪽에  배치한 캐릭터 아이디와 같으면 패스
//		    					}
//		    					else {
//		    						already_set_img++;			// 둘 다 아니라면 왼쪽에 배치 다음번 캐릭터가 둘 다 아니라면 오른쪽 배치
//		    					}
		    					
		    				}
		    				
		    				story_text_count++;		// 다음 캐릭터 문자를 받이 위해 값을 넘김
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
		
		btn_go_next_story.setVisibility(View.INVISIBLE);	// 텍스트를 강제로 못 넘기가 하기위해 버튼은 숨김
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
