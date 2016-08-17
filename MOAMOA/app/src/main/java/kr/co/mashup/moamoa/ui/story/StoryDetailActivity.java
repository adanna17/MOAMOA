package kr.co.mashup.moamoa.ui.story;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.data.Group;
import kr.co.mashup.moamoa.data.Story;
import kr.co.mashup.moamoa.ui.base.BaseActivity;

public class StoryDetailActivity extends BaseActivity {

    public static final String TAG = StoryDetailActivity.class.getSimpleName();  //로그
    public static final String MOA_STORY = "story";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.textView_content_name)
    TextView tvContentName;

    @BindView(R.id.textView_content_highlight)
    TextView tvContentHighlight;

    @BindView(R.id.textView_story_opinion)
    TextView tvStoryOpinion;

    @BindView(R.id.ll_story_opinion_update)
    LinearLayout llStoryOpinionUpdate;

    Story mStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receiveIntent = getIntent();
        mStory = (Story) receiveIntent.getSerializableExtra(MOA_STORY);

        initToolbar();
    }

    @Override
    public int getLayoutID() {
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

    @OnClick(R.id.textView_story_opinion_update_show)
    public void onClickOpinionUpdateShow() {
        if (llStoryOpinionUpdate.getVisibility() == View.VISIBLE) {
            llStoryOpinionUpdate.setVisibility(View.GONE);
        } else {
            llStoryOpinionUpdate.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.button_story_modify)
    public void stroyOpinionModify() {
        Toast.makeText(StoryDetailActivity.this, "modify", Toast.LENGTH_SHORT).show();
        //Todo: 토스트 삭제. 수정 기능 구현
    }

    @OnClick(R.id.button_story_delete)
    public void stroyOpinionDelete() {
        Toast.makeText(StoryDetailActivity.this, "delete", Toast.LENGTH_SHORT).show();
        //Todo: 토스트 삭제. 삭제 기능 구현
    }
}
