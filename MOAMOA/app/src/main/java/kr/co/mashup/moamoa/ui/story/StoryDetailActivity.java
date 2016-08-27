package kr.co.mashup.moamoa.ui.story;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.data.Story;
import kr.co.mashup.moamoa.ui.base.BaseActivity;

public class StoryDetailActivity extends BaseActivity {

    public static final String TAG = StoryDetailActivity.class.getSimpleName();  //로그
    public static final String MOA_STORY = "story";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    Story mStory;

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receiveIntent = getIntent();
        mStory = (Story) receiveIntent.getSerializableExtra(MOA_STORY);

        //Todo: 지우기
        dummyData();  //Todo: 지우기

        initToolbar();

        mFragmentManager = getSupportFragmentManager();

        //초기 화면 지정
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fl_container_fragment);
        if (fragment == null) {
            StoryDetailFragment storyDetailFragment = StoryDetailFragment.newInstance(mStory);

            mFragmentManager.beginTransaction()
                    .add(R.id.fl_container_fragment, storyDetailFragment, StoryDetailFragment.TAG)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_story_detail;
    }

    @UiThread
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);  //actionBar 기본 타이틀 제거
            ab.setDisplayHomeAsUpEnabled(true);  //홈 버튼 표시
//            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);  //홈 버튼 아이콘 변경
        }
    }

    //    @OnClick(R.id.button_story_modify)
    public void stroyOpinionModify() {
        Toast.makeText(StoryDetailActivity.this, "modify", Toast.LENGTH_SHORT).show();
        //Todo: 토스트 삭제. 수정 기능 구현
    }

    //    @OnClick(R.id.button_story_delete)
    public void stroyOpinionDelete() {
        Toast.makeText(StoryDetailActivity.this, "delete", Toast.LENGTH_SHORT).show();
        //Todo: 토스트 삭제. 삭제 기능 구현
    }

    /**
     * StoryDetailFragment에서 수정하기 버튼 클릭했을 때
     * 마이스토리의 수정(나의 의견)을 시작한다.
     *
     * @param storyEditStartEvent
     */
    @Subscribe
    public void onEvent(StoryEditStartEvent storyEditStartEvent) {
        //ui 변경 및 데이터 전달
        mFragmentManager.beginTransaction()
                .replace(R.id.fl_container_fragment, StoryEditFragment.newInstance(storyEditStartEvent.getStory()), StoryEditFragment.TAG)
                .commit();
    }

    /**
     * StoryDetailFragment에서 삭제하기 버튼을 클릭했을 때
     * 마
     * @param storyDeleteEvent
     */
    @Subscribe
    public void onEvent(StoryDeleteEvent storyDeleteEvent){
        //Todo: DB에서 삭제 and UI update

    }

    /**
     * StoryEditFragment에서 저장하기 버튼 클릭했을 때
     * 마이스토리의 수정 내용(나의 의견)을 DB에 반영한다.
     *
     * @param storyEditResultEvent
     */
    @Subscribe
    public void onEvent(StoryEditResultEvent storyEditResultEvent) {
        //ui 변경 및 데이터 전달
        mFragmentManager.beginTransaction()
                .replace(R.id.fl_container_fragment, StoryDetailFragment.newInstance(storyEditResultEvent.getStory()), StoryDetailFragment.TAG)
                .commit();
        //Todo: Local, Remote DB에 반영
    }

    //Todo: 지우기
    private void dummyData(){
        mStory.setMyOpinion("영어는 너무 어려워");
        mStory.setContentHighlight("content highlight");
    }

}
