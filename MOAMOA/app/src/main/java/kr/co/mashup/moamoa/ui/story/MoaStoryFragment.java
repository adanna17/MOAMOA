package kr.co.mashup.moamoa.ui.story;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.common.SpacingHeaderItemDecoration;
import kr.co.mashup.moamoa.data.Story;
import kr.co.mashup.moamoa.ui.group.GroupDetailActivity;
import kr.co.mashup.moamoa.ui.home.SpacingItemDecoration;

public class MoaStoryFragment extends Fragment {

    @BindView(R.id.recyclerView_story)
    RecyclerView rvStory;

    @BindDimen(R.dimen.moa_list_margin)
    int itemSpacingSize;

    @BindDimen(R.dimen.moa_list_first_margin)
    int itemFirstSpacingSize;

    StoryListAdapter mStoryListAdapter;

    Unbinder mUnbinder;

    public MoaStoryFragment() {
        // Required empty public constructor
    }

    public static MoaStoryFragment newInstance() {
        MoaStoryFragment fragment = new MoaStoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moa_story, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        initStoryList();
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    private void initStoryList() {
        mStoryListAdapter = new StoryListAdapter(getActivity());
        mStoryListAdapter.setOnListItemListener(new OnListItemListener<Story>() {
            @Override
            public void onListItemClick(Story item) {
                //스토리 상세로 이동
                Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                intent.putExtra(StoryDetailActivity.MOA_STORY, item);
                startActivity(intent);
            }
        });
        rvStory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvStory.setHasFixedSize(true);
        rvStory.setAdapter(mStoryListAdapter);
        rvStory.addItemDecoration(new SpacingItemDecoration(itemSpacingSize, itemFirstSpacingSize));

    }
}
