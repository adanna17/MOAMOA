package kr.co.mashup.moamoa.ui.main;

import android.support.annotation.UiThread;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.ui.group.MoaGroupFragment;
import kr.co.mashup.moamoa.ui.home.MoaHomeFragment;
import kr.co.mashup.moamoa.ui.profile.MoaProfileFragment;
import kr.co.mashup.moamoa.ui.story.MoaStoryFragment;
import kr.co.mashup.moamoa.ui.tag.MoaTagFragment;

public class MoaMainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager mBottomNavigationViewPager;

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigation;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private MoaMainPagerAdapter mMoaMainPagerAdapter;
    private ArrayList<AHBottomNavigationItem> mBottomNavigationItems = new ArrayList<>();

    @BindColor(R.color.moa_blue)
    int mAccentColor;

    @BindColor(R.color.moa_background_gray)
    int mInactiveColor;

    Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moa_main);
        mUnbinder = ButterKnife.bind(this);

        initToolbar();
        initUI();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    @UiThread
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);  //actionBar 기본 타이틀 제거
        }
    }

    @UiThread
    private void initUI() {
        mBottomNavigationItems.add(new AHBottomNavigationItem("홈", R.drawable.icon_home));
        mBottomNavigationItems.add(new AHBottomNavigationItem("그룹", R.drawable.icon_group));
        mBottomNavigationItems.add(new AHBottomNavigationItem("태그", R.drawable.icon_tag));
        mBottomNavigationItems.add(new AHBottomNavigationItem("스토리", R.drawable.icon_story));
        mBottomNavigationItems.add(new AHBottomNavigationItem("프로필", R.drawable.icon_profile));

        mBottomNavigation.addItems(mBottomNavigationItems);

        mBottomNavigation.setBehaviorTranslationEnabled(false);
        mBottomNavigation.setForceTitlesDisplay(true);
        mBottomNavigation.setTitleTextSize(40, 40);
        mBottomNavigation.setAccentColor(mAccentColor);
        mBottomNavigation.setInactiveColor(mInactiveColor);
        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                mBottomNavigationViewPager.setCurrentItem(position, false);
                return true;
            }
        });

        mMoaMainPagerAdapter = new MoaMainPagerAdapter(getSupportFragmentManager());
        mMoaMainPagerAdapter.addFragment(MoaHomeFragment.newInstance());
        mMoaMainPagerAdapter.addFragment(MoaGroupFragment.newInstance());
        mMoaMainPagerAdapter.addFragment(MoaTagFragment.newInstance());
        mMoaMainPagerAdapter.addFragment(MoaStoryFragment.newInstance());
        mMoaMainPagerAdapter.addFragment(MoaProfileFragment.newInstance());
        mBottomNavigationViewPager.setAdapter(mMoaMainPagerAdapter);
    }
}
