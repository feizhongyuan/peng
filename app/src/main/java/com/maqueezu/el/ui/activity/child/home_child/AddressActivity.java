package com.maqueezu.el.ui.activity.child.home_child;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.engine.DBManager;
import com.maqueezu.el.ui.test.TestActivity;
import com.maqueezu.el.ui.view.citylist.CityDBManager;
import com.maqueezu.el.ui.view.citylist.CityModel;
import com.maqueezu.el.ui.view.citylist.MyLetterListView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 城市选择
 */
public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private AutoRelativeLayout rl_statusbar;
    private TextView title_text;
    private TextView tv_dangqianCity;
    private TextView dangqian_name;
    private View view_item_top_line1;
    private AutoRelativeLayout rl_dangqianCity;
    private TextView tv_dingweiCity;
    private TextView dingwei_name;
    private View view_item_top_line2;
    private AutoRelativeLayout rl_dingweiCity;
    private ListView mListView_City;
    private MyLetterListView cityLetterListView;
    private AutoRelativeLayout city_layout;

    private SQLiteDatabase database;

    private TextView overlay;
    private List<CityModel> cityModels;
    private HashMap<String, Integer> alphaIndexer;
    private Handler handler;
    private OverlayThread overlayThread;
    private String[] sections;// 存放存在的汉语拼音首字母

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide(Gravity.BOTTOM).setDuration(300));
            getWindow().setExitTransition(new Slide(Gravity.TOP).setDuration(300));
        }
        setContentView(R.layout.activity_address);
        initView();
        initDate();

        CityDBManager dbManager = new CityDBManager(this);
        dbManager.openDateBase();
        dbManager.closeDatabase();
        database = SQLiteDatabase.openOrCreateDatabase(CityDBManager.DB_PATH + "/" + CityDBManager.DB_NAME, null);
        cityModels = getCityNames();
        database.close();

        cityLetterListView.setOnTouchingLetterChangedListener(new LetterListViewListener());
        alphaIndexer = new HashMap<String, Integer>();
        handler = new Handler();
        overlayThread = new OverlayThread();

        initOverlay();
        setAdapter(cityModels);
        mListView_City.setOnItemClickListener(new CityListOnItemClick());
    }

    private void initDate() {
        title_text.setText("选择地区");

        tv_dangqianCity.setText("当前城市");
        dangqian_name.setText("西安市");
        tv_dingweiCity.setText("定位城市");
        dingwei_name.setText("西安市");

    }

    private void initView() {
        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        back_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
        tv_dangqianCity = (TextView) findViewById(R.id.tv_dangqianCity);
        tv_dangqianCity.setOnClickListener(this);
        dangqian_name = (TextView) findViewById(R.id.dangqian_name);
        dangqian_name.setOnClickListener(this);
        view_item_top_line1 = (View) findViewById(R.id.view_item_top_line1);
        view_item_top_line1.setOnClickListener(this);
        rl_dangqianCity = (AutoRelativeLayout) findViewById(R.id.rl_dangqianCity);
        rl_dangqianCity.setOnClickListener(this);
        tv_dingweiCity = (TextView) findViewById(R.id.tv_dingweiCity);
        tv_dingweiCity.setOnClickListener(this);
        dingwei_name = (TextView) findViewById(R.id.dingwei_name);
        dingwei_name.setOnClickListener(this);
        view_item_top_line2 = (View) findViewById(R.id.view_item_top_line2);
        view_item_top_line2.setOnClickListener(this);
        rl_dingweiCity = (AutoRelativeLayout) findViewById(R.id.rl_dingweiCity);
        rl_dingweiCity.setOnClickListener(this);
        mListView_City = (ListView) findViewById(R.id.mListView_City);
        cityLetterListView = (MyLetterListView) findViewById(R.id.cityLetterListView);
        city_layout = (AutoRelativeLayout) findViewById(R.id.city_layout);
        city_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;

            default:
                break;
        }
    }

    /**
     * 从数据库获取城市数据
     *
     * @return
     */
    private ArrayList<CityModel> getCityNames() {
        ArrayList<CityModel> names = new ArrayList<CityModel>();
        Cursor cursor = database.rawQuery("SELECT * FROM T_City ORDER BY NameSort", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            CityModel cityModel = new CityModel();
            cityModel.setCityName(cursor.getString(cursor.getColumnIndex("CityName")));
            cityModel.setNameSort(cursor.getString(cursor.getColumnIndex("NameSort")));
            cityModel.setAreaCode(cursor.getString(cursor.getColumnIndex("AreaCode")));
            names.add(cityModel);
        }
        return names;
    }

    /**
     * 城市列表点击事件
     *
     * @author sy
     */
    class CityListOnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
            if (pos != 0) {
                CityModel cityModel = (CityModel) mListView_City.getAdapter().getItem(pos);
                Toast.makeText(AddressActivity.this, cityModel.getCityName(), Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     * 为ListView设置适配器
     *
     * @param list
     */
    private void setAdapter(List<CityModel> list) {
        if (list != null) {
            ListAdapter adapter = new ListAdapter(this, list);
            mListView_City.setAdapter(adapter);
        }
    }

    /**
     * ListViewAdapter
     *
     * @author sy
     */
    private class ListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<CityModel> list;

        public ListAdapter(Context context, List<CityModel> list) {

            this.inflater = LayoutInflater.from(context);
            this.list = list;
            alphaIndexer = new HashMap<String, Integer>();
            sections = new String[list.size()];

            for (int i = 0; i < list.size(); i++) {
                // 当前汉语拼音首字母
                // getAlpha(list.get(i));
                String currentStr = list.get(i).getNameSort();
                // 上一个汉语拼音首字母，如果不存在为“ ”
                String previewStr = (i - 1) >= 0 ? list.get(i - 1).getNameSort() : " ";
                if (!previewStr.equals(currentStr)) {
                    String name = list.get(i).getNameSort();
                    alphaIndexer.put(name, i);
                    sections[i] = name;
                }
            }

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.city_list_item, null);
                holder = new ListAdapter.ViewHolder();
                holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.view_item_top_line = convertView.findViewById(R.id.view_item_top_line);
                convertView.setTag(holder);
            } else {
                holder = (ListAdapter.ViewHolder) convertView.getTag();
            }

            holder.name.setText(list.get(position).getCityName());
            String currentStr = list.get(position).getNameSort();
            String previewStr = (position - 1) >= 0 ? list.get(position - 1).getNameSort() : " ";
            if (!previewStr.equals(currentStr)) {
                holder.alpha.setVisibility(View.VISIBLE);
                holder.alpha.setText(currentStr);
                holder.view_item_top_line.setVisibility(View.GONE);
            } else {
                holder.alpha.setVisibility(View.GONE);
                holder.view_item_top_line.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

        private class ViewHolder {
            TextView alpha;
            TextView name;
            TextView code;
            View view_item_top_line;
        }

    }

    // 初始化汉语拼音首字母弹出提示框
    private void initOverlay() {
        LayoutInflater inflater = LayoutInflater.from(this);
        overlay = (TextView) inflater.inflate(R.layout.overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);
    }

    private class LetterListViewListener implements MyLetterListView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
                mListView_City.setSelection(position + 1);
                overlay.setText(sections[position]);
                overlay.setVisibility(View.VISIBLE);
                handler.removeCallbacks(overlayThread);
                // 延迟一秒后执行，让overlay为不可见

                handler.postDelayed(overlayThread, 700);
            }
        }

    }

    // 设置overlay不可见
    private class OverlayThread implements Runnable {

        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }

    }
}
