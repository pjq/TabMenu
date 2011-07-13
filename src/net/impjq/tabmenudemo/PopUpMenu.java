
package net.impjq.tabmenudemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
public class PopUpMenu extends PopupWindow {
    private GridView mPopUpMenuGridView;
    private LinearLayout mLayout;
    private PopUpMenuAdapter mPopUpMenuTitleAdapter;

    static class PopUpMenuEntity {
        String mText;
        int mTextSize;
        int mTextColor;
        int mSelectedColor;
        int mUnSelectedColor;
        int mIconResId;
        boolean mEnable;
        int mVisibility;

        public PopUpMenuEntity(String titleText, int titleTextSize, int titleTextColor,
                int titleSelectedColor,
                int titleUnSelectedColor, int iconResId, boolean enable, int visibility) {
            // TODO Auto-generated constructor stub
            mText = titleText;
            mTextSize = titleTextSize;
            mTextColor = titleTextColor;
            mSelectedColor = titleSelectedColor;
            mUnSelectedColor = titleUnSelectedColor;
            mIconResId = iconResId;
            mEnable = enable;
            mVisibility = visibility;
        }
    }

    public PopUpMenu(Context context, OnItemClickListener popUpMenuOnItemClickListener,
            PopUpMenuAdapter popUpMenuAdapter, int tabMenuBackgroundColor, int tabMenuAnimation) {
        super(context);

        mLayout = new LinearLayout(context);
        mLayout.setPadding(1, 1, 1, 1);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        // TabMenu Titlebar GridView
        mPopUpMenuGridView = new GridView(context);
        mPopUpMenuGridView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        mPopUpMenuGridView.setNumColumns(popUpMenuAdapter.getCount());
        mPopUpMenuGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        mPopUpMenuGridView.setVerticalSpacing(1);
        mPopUpMenuGridView.setHorizontalSpacing(1);
        mPopUpMenuGridView.setGravity(Gravity.CENTER);
        mPopUpMenuGridView.setOnItemClickListener(popUpMenuOnItemClickListener);
        mPopUpMenuGridView.setAdapter(popUpMenuAdapter);
        // When selected,set to transparent.
        mPopUpMenuGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        mLayout.addView(mPopUpMenuGridView);
        this.mPopUpMenuTitleAdapter = popUpMenuAdapter;

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

    // public void setTabMenuTitleSelect(int index) {
    // mPopUpMenuGridView.setSelection(index);
    // this.mTabMenuTitleAdapter.setFocus(index);
    // }

    public void setPopUpMenuItemSelect(int index, int bodySelectedColor) {
        int count = mPopUpMenuGridView.getChildCount();
        for (int i = 0; i < count; i++) {
            if (i != index)
                ((LinearLayout) mPopUpMenuGridView.getChildAt(i))
                        .setBackgroundColor(Color.TRANSPARENT);
        }
        ((LinearLayout) mPopUpMenuGridView.getChildAt(index)).setBackgroundColor(bodySelectedColor);
    }

    /**
     * 自定义Adapter,TabMenu的分页标签部分
     */
    static public class PopUpMenuAdapter extends BaseAdapter {
        private Context mContext;
        private int fontColor, unselcolor, selcolor;
        // private TextView[] mTitleTextView;
        private ArrayList<PopUpMenuEntity> mTitleEntityList;

        public PopUpMenuAdapter(Context context, ArrayList<PopUpMenuEntity> popUpMenuEntityList) {
            // TODO Auto-generated constructor stub
            mTitleEntityList = popUpMenuEntityList;
            mContext = context;
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
        // public PopUpMenuAdapter(Context context, String[] titles, int
        // fontSize,
        // int fontcolor, int unselcolor, int selcolor) {
        // this.mContext = context;
        // this.fontColor = fontcolor;
        // this.unselcolor = unselcolor;
        // this.selcolor = selcolor;
        // this.mTitleTextView = new TextView[titles.length];
        // for (int i = 0; i < titles.length; i++) {
        // mTitleTextView[i] = new TextView(mContext);
        // mTitleTextView[i].setText(titles[i]);
        // mTitleTextView[i].setTextSize(fontSize);
        // mTitleTextView[i].setTextColor(fontColor);
        // mTitleTextView[i].setGravity(Gravity.CENTER);
        // mTitleTextView[i].setPadding(10, 10, 10, 10);
        // }
        // }

        public int getCount() {
            return null == mTitleEntityList ? 0 : mTitleEntityList.size();
        }

        public Object getItem(int position) {
            return null == mTitleEntityList ? 0 : mTitleEntityList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        // /**
        // * 设置选中的效果
        // */
        // private void setFocus(int index) {
        // int length = getCount();
        // for (int i = 0; i < length; i++) {
        // if (i != index) {
        // PopUpMenuEntity entity = mTitleEntityList.get(i);
        // mTitleTextView[i].setBackgroundDrawable(new ColorDrawable(
        // entity.mUnSelectedColor));// 设置没选中的颜色
        // mTitleTextView[i].setTextColor(entity.mTextColor);// 设置没选中项的字体颜色
        // }
        // }
        //
        // PopUpMenuEntity ent = mTitleEntityList.get(index);
        // // mTitleTextView[index].setBackgroundColor(0x00);// 设置选中项的颜色
        // mTitleTextView[index].setBackgroundColor(Color.TRANSPARENT);//
        // 设置选中项的颜色
        // mTitleTextView[index].setTextColor(ent.mSelectedColor);// 设置选中项的字体颜色
        // // mTitleTextView[index].setTextColor(Color.RED);// 设置选中项的字体颜色
        // }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            v = makeMenuBody(position);
            return v;
        }

        private LinearLayout makeMenuBody(int position) {
            PopUpMenuEntity entity = (PopUpMenuEntity) getItem(position);
            LinearLayout result = new LinearLayout(this.mContext);
            result.setOrientation(LinearLayout.VERTICAL);
            result.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            result.setPadding(2, 2, 2, 2);

            TextView text = new TextView(this.mContext);
            text.setSingleLine(true);
            text.setText(entity.mText);
            text.setTextSize(entity.mTextSize);
            if (entity.mEnable) {
                text.setTextColor(entity.mTextColor);
            } else {
                text.setTextColor(Color.GRAY);
            }

            text.setGravity(Gravity.CENTER);
            text.setPadding(1, 1, 1, 1);
            ImageView img = new ImageView(this.mContext);
            img.setBackgroundResource(entity.mIconResId);
            result.addView(img, new LinearLayout.LayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)));
            result.addView(text);

            result.setVisibility(entity.mVisibility);

            return result;
        }

        @Override
        public boolean isEnabled(int position) {
            // TODO Auto-generated method stub
            PopUpMenuEntity entity = mTitleEntityList.get(position);
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
