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
import kr.co.mashup.moamoa.common.EndlessRecyclerViewScrollListener;
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
    int mLoadingItemPosition;  //로딩 푸터 추가한 위치

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
        LinearLayoutManager llmHome = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvHome.setLayoutManager(llmHome);
        rvHome.setHasFixedSize(true);
        rvHome.setAdapter(mContentListAdapter);
        rvHome.addItemDecoration(new SpacingItemDecoration(itemSpacingSize, itemFirstSpacingSize));
        rvHome.addOnScrollListener(new EndlessRecyclerViewScrollListener(llmHome) {
            @Override
            public int getFooterViewType(int defaultNoFooterViewType) {
                return mContentListAdapter.VIEW_TYPE_LOADING;
            }

            @Override
            public void onLoadMore(int page, int totalItemCount) {
                mLoadingItemPosition = totalItemCount;  //로딩 푸터 추가한 위치 저장
                mContentListAdapter.addItem(mLoadingItemPosition, null);  //로딩 푸터 추가
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                //Todo: api 호출 메소드 변경
                customLoadMoreDataFromApi(10, totalItemCount);
            }
        });
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
                rvHome.scrollToPosition(0);
            }
        }, 1000);
    }

    // Append more data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void customLoadMoreDataFromApi(int page, final int totalItemCount) {
        // Send an API request to retrieve appropriate data using the offset value as a parameter.
        //  --> Deserialize API response and then construct new objects to append to the adapter
        //  --> Notify the adapter of the changes
//        List<Contact> moreContacts = Contact.createContactsList(10, page);
//        int curSize = adapter.getItemCount();
//        allContacts.addAll(moreContacts);
//        adapter.notifyItemRangeInserted(curSize, allContacts.size() - 1);

        //Todo: 쓰레드 로직 수정
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mContentListAdapter.addItem(totalItemCount + 1, new Content(R.drawable.default_profile, "Welcome to Mash Up", "http://mash-up.co.kr"));
                mContentListAdapter.removeItem(mLoadingItemPosition);  //로딩 푸터 제거
            }
        }, 3000);
    }


}
