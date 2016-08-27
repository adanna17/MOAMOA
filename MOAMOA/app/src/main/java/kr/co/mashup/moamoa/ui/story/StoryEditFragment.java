package kr.co.mashup.moamoa.ui.story;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.data.Story;

/**
 * Created by Dong on 2016-08-27.
 */
public class StoryEditFragment extends Fragment {

    public static final String TAG = StoryEditFragment.class.getSimpleName();
    private static final String ARG_STORY = "story";

    private Story mStory;

    @BindView(R.id.textView_content_name)
    TextView tvContentName;

    @BindView(R.id.textView_content_highlight)
    TextView tvContentHighlight;

    @BindView(R.id.editText_story_opinion)
    EditText etStoryOpinion;

    Unbinder mUnbinder;

    public StoryEditFragment() {
        // Required empty public constructor
    }

    public static StoryEditFragment newInstance(Story story) {
        StoryEditFragment fragment = new StoryEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORY, story);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mStory = (Story)getArguments().getSerializable(ARG_STORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story_edit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        initData();


    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    private void initData() {
        tvContentName.setText(mStory.getContentName());
        tvContentHighlight.setText(mStory.getContentHighlight());
        etStoryOpinion.setText(mStory.getMyOpinion());

        //Todo: 태그, 그룹 이름 ui recyclerview로 수정하고 데이터 넣기
    }

    /**
     * 저장하기 버튼을 클릭했을 때 StoryDetailActiviry로 수정 완료 이벤트 발행
     */
    @OnClick(R.id.button_story_save)
    public void stroyOpinionSave() {
        mStory.setMyOpinion(etStoryOpinion.getText().toString());
        EventBus.getDefault().post(new StoryEditResultEvent(mStory));
    }
}
