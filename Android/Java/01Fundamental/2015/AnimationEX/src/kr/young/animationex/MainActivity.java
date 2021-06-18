package kr.young.animationex;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends Activity {

	final int MENU_ALPHA_FI = 11;
	final int MENU_ALPHA_FO = 12;
	
	final int MENU_SCALE_REDUCT = 21;
	final int MENU_SCALE_EXPAND = 22;
	
	final int MENU_ROTATE = 31;
	
	final int MENU_TRANS_LEFT = 41;
	final int MENU_TRANS_RIGHT = 42;
	
	final int MENU_SET1 = 51;
	final int MENU_SET2 = 52;
	
	final int MENU_FRAME = 61;
	
	 ImageView iv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
    	SubMenu alpha = menu.addSubMenu("Alpha ▷");
    	alpha.add(0,MENU_ALPHA_FI,0,"나타남");
    	alpha.add(0,MENU_ALPHA_FO,0,"사라짐");
    	
    	SubMenu scale = menu.addSubMenu("Scale ▷");
    	scale.add(0,MENU_SCALE_REDUCT,0,"작게");
    	scale.add(0,MENU_SCALE_EXPAND,0,"크게");
    	
    	SubMenu rotate = menu.addSubMenu("Rotate ▷");
    	rotate.add(0,MENU_ROTATE,0,"회전");
    	
    	SubMenu trans = menu.addSubMenu("Trans ▷");
    	trans.add(0,MENU_TRANS_LEFT,0,"왼쪽 이동");
    	trans.add(0,MENU_TRANS_RIGHT,0,"오른쪽 이동");
    	
    	SubMenu set = menu.addSubMenu("Set ▷");
    	set.add(0,MENU_SET1,0,"설정1");
    	set.add(0,MENU_SET2,0,"설정2");
    	
    	SubMenu frame = menu.addSubMenu("Frame ▷");
    	frame.add(0,MENU_FRAME,0,"프레임");
    	
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        iv = (ImageView) findViewById(R.id.imageView1);
        Animation ani;
        switch(id){
        case MENU_ALPHA_FI:
        	ani = AnimationUtils.loadAnimation(this, R.anim.alpha_fi);
        	iv.startAnimation(ani);
        	break;
        case MENU_ALPHA_FO:
        	ani = AnimationUtils.loadAnimation(this, R.anim.alpha_fo);
        	iv.startAnimation(ani);
        	break;
        	
        case MENU_SCALE_REDUCT:
        	ani = AnimationUtils.loadAnimation(this, R.anim.scale_reduct);
        	iv.startAnimation(ani);
        	break;
        case MENU_SCALE_EXPAND:
        	ani = AnimationUtils.loadAnimation(this, R.anim.scale_expand);
        	iv.startAnimation(ani);
        	break;
        	
        case MENU_ROTATE:
        	ani = AnimationUtils.loadAnimation(this, R.anim.rotate_rotate);
        	iv.startAnimation(ani);
        	break;
        	
        case MENU_TRANS_LEFT:
        	ani = AnimationUtils.loadAnimation(this, R.anim.trans_left);
        	iv.startAnimation(ani);
        	break;
        case MENU_TRANS_RIGHT:
        	ani = AnimationUtils.loadAnimation(this, R.anim.trans_right);
        	iv.startAnimation(ani);
        	break;
        	
        case MENU_SET1:
        	ani = AnimationUtils.loadAnimation(this, R.anim.set_set1);
        	iv.startAnimation(ani);
        	break;
        case MENU_SET2:
        	ani = AnimationUtils.loadAnimation(this, R.anim.set_set2);
        	iv.startAnimation(ani);
        	break;
        	
        case MENU_FRAME:
        	iv.setImageDrawable(null);
        	iv.setBackgroundResource(R.drawable.ani_list);
        	AnimationDrawable anid = (AnimationDrawable) iv.getBackground();
        	anid.start();
        	
        	break;
        	
        }
        return super.onOptionsItemSelected(item);
    }
}
