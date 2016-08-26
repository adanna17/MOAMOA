package kr.co.mashup.moamoa.ui.main;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindInt;
import butterknife.ButterKnife;
import kr.co.mashup.moamoa.common.PagerAdapter;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.ui.group.MoaGroupFragment;
import kr.co.mashup.moamoa.ui.home.MoaHomeFragment;
import kr.co.mashup.moamoa.ui.profile.MoaProfileFragment;
import kr.co.mashup.moamoa.ui.story.MoaStoryFragment;
import kr.co.mashup.moamoa.ui.tag.MoaTagFragment;

public class MoaMainActivity extends AppCompatActivity {

    private PagerAdapter adapter;
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    //String userMoaId;

    @BindColor(R.color.moa_blue)
    int accentcolor;

    @BindColor(R.color.moa_background_gray)
    int inactivecolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moa_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();

//        Intent intent = getIntent();
//        userMoaId = intent.getExtras().getString("moaId");
//        Toast.makeText(MoaMainActivity.this, userMoaId, Toast.LENGTH_SHORT).show();

    }

    private void initUI() {

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("홈", R.drawable.icon_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("그룹", R.drawable.icon_group);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("태그", R.drawable.icon_tag);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("스토리", R.drawable.icon_story);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("프로필", R.drawable.icon_profile);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigationItems.add(item5);

        bottomNavigation.addItems(bottomNavigationItems);

        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setTitleTextSize(40, 40);
        bottomNavigation.setAccentColor(accentcolor);
        bottomNavigation.setInactiveColor(inactivecolor);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, false);
                return true;
            }
        });

        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MoaHomeFragment());
        adapter.addFragment(new MoaGroupFragment());
        adapter.addFragment(new MoaTagFragment());
        adapter.addFragment(new MoaStoryFragment());
        adapter.addFragment(new MoaProfileFragment());
        viewPager.setAdapter(adapter);

    }
}
