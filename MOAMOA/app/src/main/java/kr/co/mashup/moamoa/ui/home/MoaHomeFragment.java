package kr.co.mashup.moamoa.ui.home;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Content;

//Todo: Endless Scrolling
public class MoaHomeFragment extends Fragment {

    @BindView(R.id.recyclerView_home)
    RecyclerView rvHome;

    @BindView(R.id.swipeRefreshLayout_home)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindDimen(R.dimen.moa_list_margin)
    int itemSpacingSize;

    @BindDimen(R.dimen.moa_list_first_margin)
    int itemFirstSpacingSize;

    @BindColor(R.color.moa_gradient_start)
    int refresh_start_color;

    @BindColor(R.color.moa_gradient_end)
    int refresh_end_color;

    ContentListAdapter mContentListAdapter;

    Unbinder mUnbinder;

    public MoaHomeFragment() {
    }

    public static MoaHomeFragment newInstance() {
        MoaHomeFragment fragment = new MoaHomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moa_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        initContentsList();
        initSwipeRefreshLayout();
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(refresh_start_color, refresh_end_color);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void initContentsList() {
        mContentListAdapter = new ContentListAdapter(getActivity());
        mContentListAdapter.setOnListItemListener(new OnListItemListener<Content>() {
            @Override
            public void onListItemClick(Content item) {
                Toast.makeText(getActivity(), "item 선택", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onListITemDelete(int position) {
                mContentListAdapter.removeItem(position);
                Toast.makeText(getActivity(), "Deleted ", Toast.LENGTH_SHORT).show();

            }
        });
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvHome.setHasFixedSize(true);
        rvHome.setAdapter(mContentListAdapter);
        rvHome.addItemDecoration(new SpacingItemDecoration(itemSpacingSize, itemFirstSpacingSize));
    }

    /**
     * Todo:  컨텐츠 리스트 새로고침
     */
    private void refresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mContentListAdapter.addItem(0, new Content(R.drawable.default_profile, "Welcome to Mash Up", "http://mash-up.co.kr"));
                Toast.makeText(getActivity(), "refresh!!", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
