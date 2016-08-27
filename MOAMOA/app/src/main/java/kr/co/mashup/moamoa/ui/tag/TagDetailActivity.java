package kr.co.mashup.moamoa.ui.tag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.data.Group;
import kr.co.mashup.moamoa.data.Tag;
import kr.co.mashup.moamoa.ui.base.BaseActivity;
import kr.co.mashup.moamoa.ui.home.MoaHomeFragment;

public class TagDetailActivity extends BaseActivity {

    public static final String TAG = TagDetailActivity.class.getSimpleName();
    public static final String MOA_TAG = "tag";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.textView_toolbar_name)
    TextView tvToolbarTagName;

    Tag mTag;

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receiveIntent = getIntent();
        mTag = (Tag) receiveIntent.getSerializableExtra(MOA_TAG);

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
    public int getLayoutId() {
        return R.layout.activity_tag_detail;
    }

    @UiThread
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);  //actionBar 기본 타이틀 제거
            ab.setDisplayHomeAsUpEnabled(true);  //홈 버튼 표시
//            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);  //홈 버튼 아이콘 변경
            tvToolbarTagName.setText(String.format("#%s", mTag.getName()));
        }
    }
}
