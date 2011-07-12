package net.impjq.tabmenudemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
/** 
 * @author pengjianqing@gmail.com 
 * @version create at：2011-7-12 下午03:29:07  
 */
public class TabMenuDemo extends Activity {
    TabMenu.MenuBodyAdapter []bodyAdapter=new TabMenu.MenuBodyAdapter[3];
    TabMenu.MenuTitleAdapter titleAdapter;
    TabMenu tabMenu;
    int selTitle=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //设置分页栏的标题
        titleAdapter = new TabMenu.MenuTitleAdapter(this, new String[] { "常用",
                "设置", "工具" }, 16, 0xFF222222,Color.LTGRAY,Color.WHITE);
        //定义每项分页栏的内容
        bodyAdapter[0]=new TabMenu.MenuBodyAdapter(this,new String[] { "常用1", "常用2", }, 
                 new int[] { R.drawable.icon,
                R.drawable.icon},13, 0xFFFFFFFF);
         
        bodyAdapter[1]=new TabMenu.MenuBodyAdapter(this,new String[] { "设置1", "设置2",
                    "设置3"}, new int[] { R.drawable.icon,
                    R.drawable.icon, R.drawable.icon},13, 0xFFFFFFFF);
         
        bodyAdapter[2]=new TabMenu.MenuBodyAdapter(this,new String[] { "工具1", "工具2",
                    "工具3", "工具4" }, new int[] { R.drawable.icon,
                    R.drawable.icon, R.drawable.icon,
                    R.drawable.icon },13, 0xFFFFFFFF);
         
         
        tabMenu=new TabMenu(this,
                 new TitleClickEvent(),
                 new BodyClickEvent(),
                 titleAdapter,
                 0x55123456,//TabMenu的背景颜色
                 R.style.PopupAnimation);//出现与消失的动画
         
         tabMenu.update();
         tabMenu.SetTitleSelect(0);
         tabMenu.SetBodyAdapter(bodyAdapter[0]);
    }
    
    class TitleClickEvent implements OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            selTitle=arg2;
            tabMenu.SetTitleSelect(arg2);
            tabMenu.SetBodyAdapter(bodyAdapter[arg2]);
        }
    }
    
    class BodyClickEvent implements OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            tabMenu.SetBodySelect(arg2,Color.GRAY);
            String str="第"+String.valueOf(selTitle)+"栏/n/r"
            +"第"+String.valueOf(arg2)+"项";
            Toast.makeText(TabMenuDemo.this, str, 500).show();
            
        }
        
    }
    @Override
    /**
     * 创建MENU
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("menu");// 必须创建一项
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    /**
     * 拦截MENU
     */
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (tabMenu != null) {
            if (tabMenu.isShowing())
                tabMenu.dismiss();
            else {
                tabMenu.showAtLocation(findViewById(R.id.LinearLayout01),
                        Gravity.BOTTOM, 0, 0);
            }
        }
        return false;// 返回为true 则显示系统menu
    }
    
}
