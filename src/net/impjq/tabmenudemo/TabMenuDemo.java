
package net.impjq.tabmenudemo;

import net.impjq.tabmenudemo.TabMenu.MenuBodyAdapter;
import net.impjq.tabmenudemo.TabMenu.TabMenuBodyEntity;
import net.impjq.tabmenudemo.TabMenu.TabMenuEntity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
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
    TabMenu.MenuTitleAdapter mTitleAdapter;
    TabMenu mTtabMenu;
    int mTitleIndex = 0;
    int mMaxBodyItems = 8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        createTabMenu();
        mTitleAdapter = new TabMenu.MenuTitleAdapter(getApplicationContext(), mTabMenuTitleList);

        // createTabMenuBodyAdapterList();

        mTtabMenu = new TabMenu(this,
                new TitleClickListener(),
                new BodyClickListener(),
                mTitleAdapter,
                // 0x55123456,// TabMenu的背景颜色
                Color.BLACK,
                R.style.PopupAnimation);// 出现与消失的动画

        int defaultIndex = 0;

        mTtabMenu.update();
        mTtabMenu.setTabMenuTitleSelect(defaultIndex);
        mTtabMenu.setTabMenuBodyAdapter(mTabMenuBodyAdapterList.get(defaultIndex));
    }

    /**
     * Because the Height maybe different,so need to fill the items to the same
     * number.
     * 
     * @param list
     * @return
     */
    private ArrayList<TabMenuBodyEntity> fixItemsNumber(ArrayList<TabMenuBodyEntity> list) {

        int size = list.size();

        if (size < mMaxBodyItems) {
            int needAdd = mMaxBodyItems - size;
            for (int i = 0; i < needAdd; i++) {
                // Set invisible.
                list.add(new TabMenuBodyEntity("Setting 6", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                        0xFFFFFFFF, R.drawable.menu_about, false, View.INVISIBLE));
            }
        }

        return list;

    }

    private void createTabMenu() {
        mTabMenuTitleList = new ArrayList<TabMenu.TabMenuEntity>();
        mTabMenuBodyAdapterList = new ArrayList<TabMenu.MenuBodyAdapter>();

        int index = 0;

        // Normal TabMenu
        TabMenuEntity entityNormal = new TabMenuEntity("Normal", 16, 0xFF222222,
                Color.LTGRAY, Color.WHITE, null, true);
        mTabMenuTitleList.add(index, entityNormal);

        ArrayList<TabMenuBodyEntity> bodyNormal = new ArrayList<TabMenu.TabMenuBodyEntity>();
        bodyNormal.add(new TabMenuBodyEntity("View", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_view_image, true, View.VISIBLE));
        bodyNormal.add(new TabMenuBodyEntity("Add", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_add_to_bookmark, false, View.VISIBLE));
        bodyNormal.add(new TabMenuBodyEntity("Export", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_bookmark_sync_export, true, View.VISIBLE));
        bodyNormal.add(new TabMenuBodyEntity("Import", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_bookmark_sync_import, true, View.VISIBLE));
        bodyNormal.add(new TabMenuBodyEntity("Cut", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_cut, true, View.VISIBLE));
        bodyNormal.add(new TabMenuBodyEntity("Copy", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_copy, false, View.VISIBLE));
        fixItemsNumber(bodyNormal);
        MenuBodyAdapter adapterNormal = new MenuBodyAdapter(getApplicationContext(), bodyNormal);
        mTabMenuBodyAdapterList.add(index, adapterNormal);

        // Tools TabMenu
        ++index;
        TabMenuEntity entityTool = new TabMenuEntity("Tools", 16, 0xFF222222,
                Color.LTGRAY, Color.WHITE, null, true);
        mTabMenuTitleList.add(index, entityTool);

        ArrayList<TabMenuBodyEntity> bodyTools = new ArrayList<TabMenu.TabMenuBodyEntity>();
        bodyTools.add(new TabMenuBodyEntity("Delete", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_delete, true, View.VISIBLE));
        bodyTools.add(new TabMenuBodyEntity("Close", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_close_window, true, View.VISIBLE));
        bodyTools.add(new TabMenuBodyEntity("Check", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_checknet, true, View.VISIBLE));
        bodyTools.add(new TabMenuBodyEntity("Debug", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_debug, false, View.VISIBLE));
        fixItemsNumber(bodyTools);
        MenuBodyAdapter adapterTools = new MenuBodyAdapter(getApplicationContext(), bodyTools);
        mTabMenuBodyAdapterList.add(index, adapterTools);

        // Settings TabMenu
        ++index;
        TabMenuEntity entitySettings = new TabMenuEntity("Settings", 16, 0xFF222222,
                Color.LTGRAY, Color.WHITE, null, true);
        mTabMenuTitleList.add(index, entitySettings);

        ArrayList<TabMenuBodyEntity> bodySettings = new ArrayList<TabMenu.TabMenuBodyEntity>();
        bodySettings.add(new TabMenuBodyEntity("Download", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_download, true, View.VISIBLE));
        bodySettings.add(new TabMenuBodyEntity("Manager", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_downmanager, true, View.VISIBLE));
        bodySettings.add(new TabMenuBodyEntity("Night", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_nightmode, false, View.VISIBLE));
        bodySettings.add(new TabMenuBodyEntity("Day", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_day, true, View.VISIBLE));
        bodySettings.add(new TabMenuBodyEntity("Quit", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_quit, true, View.VISIBLE));
        fixItemsNumber(bodySettings);
        MenuBodyAdapter adapterSettings = new MenuBodyAdapter(getApplicationContext(), bodySettings);
        mTabMenuBodyAdapterList.add(index, adapterSettings);

        // More TabMenu
        ++index;
        TabMenuEntity entityMore = new TabMenuEntity("More", 16, 0xFF222222,
                Color.LTGRAY, Color.WHITE, null, true);
        mTabMenuTitleList.add(index, entityMore);

        ArrayList<TabMenuBodyEntity> bodyMore = new ArrayList<TabMenu.TabMenuBodyEntity>();
        bodyMore.add(new TabMenuBodyEntity("About", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_about, true, View.VISIBLE));
        bodyMore.add(new TabMenuBodyEntity("More", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_more, true, View.VISIBLE));
        bodyMore.add(new TabMenuBodyEntity("Help", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_help, true, View.VISIBLE));
        bodyMore.add(new TabMenuBodyEntity("Screen", 13, 0xFFFFFFFF, 0xFFFFFFFF,
                0xFFFFFFFF, R.drawable.menu_fullscreen, true, View.VISIBLE));
        fixItemsNumber(bodyMore);
        MenuBodyAdapter adapterMore = new MenuBodyAdapter(getApplicationContext(), bodyMore);
        mTabMenuBodyAdapterList.add(index, adapterMore);

    }

    class TitleClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            mTitleIndex = arg2;
            mTtabMenu.setTabMenuTitleSelect(arg2);
            mTtabMenu.setTabMenuBodyAdapter(mTabMenuBodyAdapterList.get(arg2));
        }
    }

    class BodyClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            mTtabMenu.setTabMenuBodySelect(arg2, Color.GRAY);
            String str = "第" + String.valueOf(mTitleIndex) + "栏/n/r"
                    + "第" + String.valueOf(arg2) + "项";
            // Toast.makeText(TabMenuDemo.this, str, 500).show();

            TabMenuBodyEntity bodyEntity = mTabMenuBodyAdapterList.get(mTitleIndex).mBodyEntityList
                    .get(arg2);
            String msg = bodyEntity.mText;
            Toast.makeText(getApplicationContext(), msg, 500).show();
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
        Log.i("", "onMenuOpened");
        if (mTtabMenu != null) {
            if (mTtabMenu.isShowing()) {
                mTtabMenu.dismiss();
            } else {
                mTtabMenu.showAtLocation(findViewById(R.id.LinearLayout01),
                        Gravity.BOTTOM, 0, 0);
            }
        }
        return false;// 返回为true 则显示系统menu
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        Log.i("", "onKeyDown");

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            Log.i("", "onKeyDown,KEYCODE_MENU");
            if (mTtabMenu.isShowing()) {
                mTtabMenu.dismiss();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

}
