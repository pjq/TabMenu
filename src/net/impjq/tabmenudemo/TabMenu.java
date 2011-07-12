
package net.impjq.tabmenudemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

/**
 * @author pengjianqing@gmail.com
 * @version create at：2011-7-12 下午03:27:40
 */
public class TabMenu extends PopupWindow {
    private GridView mTabMenuBody;
    private GridView mTabMenuTitle;
    private LinearLayout mLayout;
    private MenuTitleAdapter mTabMenuTitleAdapter;

    static class TabMenuEntity {
        String mTitleText;
        int mTitleTextSize;
        int mTitleTextColor;
        int mTitleSelectedColor;
        int mTitleUnSelectedColor;
        Drawable mTitleIcon;
        boolean mEnable;

        public TabMenuEntity(String titleText, int titleTextSize, int titleTextColor,
                int titleSelectedColor,
                int titleUnSelectedColor, Drawable titleIcon, boolean enable) {
            // TODO Auto-generated constructor stub
            mTitleText = titleText;
            mTitleTextSize = titleTextSize;
            mTitleTextColor = titleTextColor;
            mTitleSelectedColor = titleSelectedColor;
            mTitleUnSelectedColor = titleUnSelectedColor;
            mTitleIcon = titleIcon;
            mEnable = enable;
        }
    }

    static class TabMenuBodyEntity {
        String mTitleText;
        int mTitleTextSize;
        int mTitleTextColor;
        int mTitleSelectedColor;
        int mTitleUnSelectedColor;
        Drawable mTitleIcon;
        boolean mEnable;

        public TabMenuBodyEntity(String titleText, int titleTextSize, int titleTextColor,
                int titleSelectedColor,
                int titleUnSelectedColor, Drawable titleIcon, boolean enable) {
            // TODO Auto-generated constructor stub
            mTitleText = titleText;
            mTitleTextSize = titleTextSize;
            mTitleTextColor = titleTextColor;
            mTitleSelectedColor = titleSelectedColor;
            mTitleUnSelectedColor = titleUnSelectedColor;
            mTitleIcon = titleIcon;
            mEnable = enable;
        }
    }

    public TabMenu(Context context, OnItemClickListener titleOnItemClickListener,
            OnItemClickListener bodyOnItemClickListener,
            MenuTitleAdapter tabMenuTitleAdapter, int tabMenuBackgroundColor, int tabMenuAnimation) {
        super(context);

        mLayout = new LinearLayout(context);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        // TabMenu Titlebar GridView
        mTabMenuTitle = new GridView(context);
        mTabMenuTitle.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        mTabMenuTitle.setNumColumns(tabMenuTitleAdapter.getCount());
        mTabMenuTitle.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        mTabMenuTitle.setVerticalSpacing(1);
        mTabMenuTitle.setHorizontalSpacing(1);
        mTabMenuTitle.setGravity(Gravity.CENTER);
        mTabMenuTitle.setOnItemClickListener(titleOnItemClickListener);
        mTabMenuTitle.setAdapter(tabMenuTitleAdapter);
        // When selected,set to transparent.
        mTabMenuTitle.setSelector(new ColorDrawable(Color.TRANSPARENT));
        this.mTabMenuTitleAdapter = tabMenuTitleAdapter;
        // TabMenu SubMenu GridView
        mTabMenuBody = new GridView(context);
        mTabMenuBody.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        // When selected,set to transparent.
        mTabMenuBody.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mTabMenuBody.setNumColumns(4);
        mTabMenuBody.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        mTabMenuBody.setVerticalSpacing(10);
        mTabMenuBody.setHorizontalSpacing(10);
        mTabMenuBody.setPadding(10, 10, 10, 10);
        mTabMenuBody.setGravity(Gravity.CENTER);
        mTabMenuBody.setOnItemClickListener(bodyOnItemClickListener);
        mLayout.addView(mTabMenuTitle);
        mLayout.addView(mTabMenuBody);

        // Default Settings
        this.setContentView(mLayout);
        this.setWidth(LayoutParams.FILL_PARENT);
        this.setHeight(LayoutParams.WRAP_CONTENT);

        // Set the TabMenu background
        this.setBackgroundDrawable(new ColorDrawable(tabMenuBackgroundColor));
        this.setAnimationStyle(tabMenuAnimation);
        // SetFocusable to true,If not set set true,the item in the TabMenu will
        // not handle the touch event.
        this.setFocusable(true);
    }

    public void setTabMenuTitleSelect(int index) {
        mTabMenuTitle.setSelection(index);
        this.mTabMenuTitleAdapter.SetFocus(index);
    }

    public void setTabMenuBodySelect(int index, int bodySelectedColor) {
        int count = mTabMenuBody.getChildCount();
        for (int i = 0; i < count; i++) {
            if (i != index)
                ((LinearLayout) mTabMenuBody.getChildAt(i)).setBackgroundColor(Color.TRANSPARENT);
        }
        ((LinearLayout) mTabMenuBody.getChildAt(index)).setBackgroundColor(bodySelectedColor);
    }

    public void setTabMenuBodyAdapter(MenuBodyAdapter bodyAdapter) {
        mTabMenuBody.setAdapter(bodyAdapter);
    }

    /**
     * The customer body adapter for the TabMenuBody,it extends the
     * {@link BaseAdapter}
     */
    static public class MenuBodyAdapter extends BaseAdapter {
        private Context mContext;
        private int fontColor, fontSize;
        private String[] texts;
        private int[] resID;

        /**
         * 设置TabMenu的分页主体
         * 
         * @param context 调用方的上下文
         * @param texts 按钮集合的字符串数组
         * @param resID 按钮集合的图标资源数组
         * @param fontSize 按钮字体大小
         * @param color 按钮字体颜色
         */
        public MenuBodyAdapter(Context context, String[] texts, int[] resID, int fontSize,
                int fontColor) {
            this.mContext = context;
            this.fontColor = fontColor;
            this.texts = texts;
            this.fontSize = fontSize;
            this.resID = resID;
        }

        public int getCount() {
            return texts.length;
        }

        public Object getItem(int position) {

            return makeMenyBody(position);
        }

        public long getItemId(int position) {
            return position;
        }

        private LinearLayout makeMenyBody(int position) {
            LinearLayout result = new LinearLayout(this.mContext);
            result.setOrientation(LinearLayout.VERTICAL);
            result.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            result.setPadding(10, 10, 10, 10);

            TextView text = new TextView(this.mContext);
            text.setText(texts[position]);
            text.setTextSize(fontSize);
            text.setTextColor(fontColor);
            text.setGravity(Gravity.CENTER);
            text.setPadding(5, 5, 5, 5);
            ImageView img = new ImageView(this.mContext);
            img.setBackgroundResource(resID[position]);
            result.addView(img, new LinearLayout.LayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)));
            result.addView(text);
            return result;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return makeMenyBody(position);
        }
    }

    /**
     * 自定义Adapter,TabMenu的分页标签部分
     */
    static public class MenuTitleAdapter extends BaseAdapter {
        private Context mContext;
        private int fontColor, unselcolor, selcolor;
        private TextView[] mTitleTextView;
        private ArrayList<TabMenuEntity> mTitleEntityList;

        public MenuTitleAdapter(Context context, ArrayList<TabMenuEntity> titleEntityList) {
            // TODO Auto-generated constructor stub
            mTitleEntityList = titleEntityList;
            mContext = context;

            int length = mTitleEntityList.size();
            this.mTitleTextView = new TextView[length];
            for (int i = 0; i < length; i++) {
                TabMenuEntity entity = mTitleEntityList.get(i);
                mTitleTextView[i] = new TextView(mContext);
                mTitleTextView[i].setText(entity.mTitleText);
                mTitleTextView[i].setTextSize(entity.mTitleTextSize);
                mTitleTextView[i].setTextColor(entity.mTitleTextColor);
                mTitleTextView[i].setGravity(Gravity.CENTER);
                mTitleTextView[i].setPadding(10, 10, 10, 10);
            }
        }

        /**
         * 设置TabMenu的title
         * 
         * @param context 调用方的上下文
         * @param titles 分页标签的字符串数组
         * @param fontSize 字体大小
         * @param fontcolor 字体颜色
         * @param unselcolor 未选中项的背景色
         * @param selcolor 选中项的背景色
         */
        public MenuTitleAdapter(Context context, String[] titles, int fontSize,
                int fontcolor, int unselcolor, int selcolor) {
            this.mContext = context;
            this.fontColor = fontcolor;
            this.unselcolor = unselcolor;
            this.selcolor = selcolor;
            this.mTitleTextView = new TextView[titles.length];
            for (int i = 0; i < titles.length; i++) {
                mTitleTextView[i] = new TextView(mContext);
                mTitleTextView[i].setText(titles[i]);
                mTitleTextView[i].setTextSize(fontSize);
                mTitleTextView[i].setTextColor(fontColor);
                mTitleTextView[i].setGravity(Gravity.CENTER);
                mTitleTextView[i].setPadding(10, 10, 10, 10);
            }
        }

        public int getCount() {
            return null == mTitleEntityList ? 0 : mTitleEntityList.size();
        }

        public Object getItem(int position) {
            return null == mTitleEntityList ? 0 : mTitleEntityList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        /**
         * 设置选中的效果
         */
        private void SetFocus(int index) {
            int length = getCount();
            for (int i = 0; i < length; i++) {
                if (i != index) {
                    TabMenuEntity entity = mTitleEntityList.get(i);
                    mTitleTextView[i].setBackgroundDrawable(new ColorDrawable(
                            entity.mTitleUnSelectedColor));// 设置没选中的颜色
                    mTitleTextView[i].setTextColor(entity.mTitleTextColor);// 设置没选中项的字体颜色
                }
            }

            TabMenuEntity ent = mTitleEntityList.get(index);
            mTitleTextView[index].setBackgroundColor(0x00);// 设置选中项的颜色
            mTitleTextView[index].setTextColor(ent.mTitleSelectedColor);// 设置选中项的字体颜色
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            if (convertView == null) {
                v = mTitleTextView[position];
            } else {
                v = convertView;
            }
            return v;
        }

        @Override
        public boolean isEnabled(int position) {
            // TODO Auto-generated method stub
            TabMenuEntity entity = mTitleEntityList.get(position);
            boolean enable = true;
            if (entity.mEnable) {
                enable = true;
            } else {
                enable = false;
            }

            return enable;
        }
    }
}
