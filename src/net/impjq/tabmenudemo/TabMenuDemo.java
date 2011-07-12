
package net.impjq.tabmenudemo;

import net.impjq.tabmenudemo.TabMenu.MenuBodyAdapter;
import net.impjq.tabmenudemo.TabMenu.TabMenuEntity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author pengjianqing@gmail.com
 * @version create at：2011-7-12 下午03:29:07
 */
public class TabMenuDemo extends Activity {

    private ArrayList<TabMenuEntity> mTabMenuTitleList;
    private ArrayList<MenuBodyAdapter> mTabMenuBodyAdapterList;

    TabMenu.MenuBodyAdapter[] bodyAdapter = new TabMenu.MenuBodyAdapter[3];
    TabMenu.MenuTitleAdapter mTitleAdapter;
    TabMenu mTtabMenu;
    int selTitle = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 设置分页栏的标题
        init();
        // mTitleAdapter = new TabMenu.MenuTitleAdapter(this, new String[] {
        // "常用",
        // "设置", "工具"
        // }, 16, 0xFF222222, Color.LTGRAY, Color.WHITE);
        // // 定义每项分页栏的内容
        // bodyAdapter[0] = new TabMenu.MenuBodyAdapter(this, new String[] {
        // "常用1", "常用2",
        // },
        // new int[] {
        // R.drawable.icon,
        // R.drawable.icon
        // }, 13, 0xFFFFFFFF);
        //
        // bodyAdapter[1] = new TabMenu.MenuBodyAdapter(this, new String[] {
        // "设置1", "设置2",
        // "设置3"
        // }, new int[] {
        // R.drawable.icon,
        // R.drawable.icon, R.drawable.icon
        // }, 13, 0xFFFFFFFF);
        //
        // bodyAdapter[2] = new TabMenu.MenuBodyAdapter(this, new String[] {
        // "工具1", "工具2",
        // "工具3", "工具4"
        // }, new int[] {
        // R.drawable.icon,
        // R.drawable.icon, R.drawable.icon,
        // R.drawable.icon
        // }, 13, 0xFFFFFFFF);
        //
        // mTtabMenu = new TabMenu(this,
        // new TitleClickEvent(),
        // new BodyClickEvent(),
        // mTitleAdapter,
        // 0x55123456,// TabMenu的背景颜色
        // R.style.PopupAnimation);// 出现与消失的动画
        //
        // mTtabMenu.update();
        // mTtabMenu.setTabMenuTitleSelect(0);
        // mTtabMenu.setTabMenuBodyAdapter(bodyAdapter[0]);
    }

    private void init() {
        // TODO Auto-generated method stub
        createTabMenuTitle();
        mTitleAdapter = new TabMenu.MenuTitleAdapter(getApplicationContext(), mTabMenuTitleList);

        createTabMenuBodyAdapterList();

        mTtabMenu = new TabMenu(this,
                new TitleClickEvent(),
                new BodyClickEvent(),
                mTitleAdapter,
                0x55123456,// TabMenu的背景颜色
                R.style.PopupAnimation);// 出现与消失的动画

        int defaultIndex = 0;
        mTtabMenu.update();
        mTtabMenu.setTabMenuTitleSelect(defaultIndex);
        mTtabMenu.setTabMenuBodyAdapter(mTabMenuBodyAdapterList.get(defaultIndex));
    }

    private void createTabMenuTitle() {
        mTabMenuTitleList = new ArrayList<TabMenu.TabMenuEntity>();
        TabMenuEntity entityNormal = new TabMenuEntity("Normal", 16, 0xFF222222,
                Color.LTGRAY, Color.WHITE, null, true);
        int index = 0;
        mTabMenuTitleList.add(index++, entityNormal);
        TabMenuEntity entityTool = new TabMenuEntity("Tools", 16, 0xFF222222,
                Color.LTGRAY, Color.WHITE, null, true);
        mTabMenuTitleList.add(index++, entityTool);
        TabMenuEntity entitySettings = new TabMenuEntity("Settings", 16, 0xFF222222,
                Color.LTGRAY, Color.WHITE, null, true);
        mTabMenuTitleList.add(index++, entitySettings);
        TabMenuEntity entityMore = new TabMenuEntity("More", 16, 0xFF222222,
                Color.LTGRAY, Color.WHITE, null, true);
        mTabMenuTitleList.add(index++, entityMore);
    }

    private void createTabMenuBodyAdapterList() {
        bodyAdapter[0] = new TabMenu.MenuBodyAdapter(this, new String[] {
                "常用1", "常用2",
        },
                 new int[] {
                         R.drawable.icon,
                         R.drawable.icon
                 }, 13, 0xFFFFFFFF);

        mTabMenuBodyAdapterList = new ArrayList<TabMenu.MenuBodyAdapter>();
        int index = 0;
        mTabMenuBodyAdapterList.add(bodyAdapter[index++]);

        bodyAdapter[1] = new TabMenu.MenuBodyAdapter(this, new String[] {
                "设置1", "设置2",
                    "设置3"
        }, new int[] {
                R.drawable.icon,
                    R.drawable.icon, R.drawable.icon
        }, 13, 0xFFFFFFFF);
        mTabMenuBodyAdapterList.add(bodyAdapter[index++]);

        bodyAdapter[2] = new TabMenu.MenuBodyAdapter(this, new String[] {
                "工具1", "工具2",
                    "工具3", "工具4"
        }, new int[] {
                R.drawable.icon,
                    R.drawable.icon, R.drawable.icon,
                    R.drawable.icon
        }, 13, 0xFFFFFFFF);
        mTabMenuBodyAdapterList.add(bodyAdapter[index++]);

        TabMenu.MenuBodyAdapter adapter4 = new TabMenu.MenuBodyAdapter(this, new String[] {
                "工具1", "工具2",
                    "工具3", "工具4"
        }, new int[] {
                R.drawable.icon,
                    R.drawable.icon, R.drawable.icon,
                    R.drawable.icon
        }, 13, 0xFFFFFFFF);
        mTabMenuBodyAdapterList.add(adapter4);

    }

    class TitleClickEvent implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            selTitle = arg2;
            mTtabMenu.setTabMenuTitleSelect(arg2);
            mTtabMenu.setTabMenuBodyAdapter(mTabMenuBodyAdapterList.get(arg2));
        }
    }

    class BodyClickEvent implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            mTtabMenu.setTabMenuBodySelect(arg2, Color.GRAY);
            String str = "第" + String.valueOf(selTitle) + "栏/n/r"
                    + "第" + String.valueOf(arg2) + "项";
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
        if (mTtabMenu != null) {
            if (mTtabMenu.isShowing())
                mTtabMenu.dismiss();
            else {
                mTtabMenu.showAtLocation(findViewById(R.id.LinearLayout01),
                        Gravity.BOTTOM, 0, 0);
            }
        }
        return false;// 返回为true 则显示系统menu
    }

}
