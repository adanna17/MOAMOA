package kr.co.mashup.moamoa.ui.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.data.Group;
import kr.co.mashup.moamoa.ui.base.BaseActivity;
import kr.co.mashup.moamoa.ui.home.MoaHomeFragment;

//Todo: 컨텐츠 리스트 스와이프 메뉴 항목 뭐가 있어야되는지 보고 수정해야함
public class GroupDetailActivity extends BaseActivity {

    public static final String TAG = GroupDetailActivity.class.getSimpleName();  //로그
    public static final String MOA_GROUP = "group";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.textView_toolbar_name)
    TextView tvToolbarGroupName;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    Group mGroup;

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receiveIntent = getIntent();
        mGroup = (Group) receiveIntent.getSerializableExtra(MOA_GROUP);

        initToolbar();

        mFragmentManager = getSupportFragmentManager();

        //초기 화면 지정
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fl_container_fragment);
        if (fragment == null) {
            MoaHomeFragment moaHomeFragment = MoaHomeFragment.newInstance();

            mFragmentManager.beginTransaction()
                    .add(R.id.fl_container_fragment, moaHomeFragment, MoaHomeFragment.TAG)
                    .commit();
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_group_detail;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.group_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_nav_open) {  //drawer navigation open
            if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                mDrawerLayout.closeDrawer(GravityCompat.END);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.END);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @UiThread
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);  //actionBar 기본 타이틀 제거
            ab.setDisplayHomeAsUpEnabled(true);  //홈 버튼 표시
//            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);  //홈 버튼 아이콘 변경
            tvToolbarGroupName.setText(mGroup.getName());
        }
    }
}
