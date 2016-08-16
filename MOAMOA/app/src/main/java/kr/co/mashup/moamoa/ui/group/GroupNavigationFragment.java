package kr.co.mashup.moamoa.ui.group;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.ItemDividerlDecoration;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.User;

public class GroupNavigationFragment extends Fragment {

    @BindView(R.id.recyclerView_user)
    RecyclerView rvUser;

    UserListAdapter mUserListAdapter;

    Unbinder mUnbinder;

    public GroupNavigationFragment() {
    }

    public static GroupNavigationFragment newInstance() {
        GroupNavigationFragment fragment = new GroupNavigationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_navigation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        initUserList();
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @UiThread
    private void initUserList() {
        mUserListAdapter = new UserListAdapter(getActivity());
        mUserListAdapter.addFooter();
        mUserListAdapter.setOnListItemListener(new OnListItemListener() {
            @Override
            public void onListItemClick(Object item) {
                addGroupMember();
            }
        });
        rvUser.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvUser.setHasFixedSize(true);
        rvUser.setAdapter(mUserListAdapter);
        rvUser.addItemDecoration(new ItemDividerlDecoration(getActivity()));  //아이템 하단 구분선
    }

    @OnClick(R.id.imageView_group_remove)
    public void groupRemove() {
        //Todo: 그룹 제거
        //Todo: 그룹 제거 api call
        Toast.makeText(getActivity(), "그룹 나가기", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imageView_group_push)
    public void groupPush() {
        //Todo: 그룹 푸쉬 설정
        //Todo: 일정 시간 계산해서 변경이 있으면 그룹 푸쉬 설정 api call
        Toast.makeText(getActivity(), "푸쉬 설정", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imageView_group_favorite)
    public void groupFavorite() {
        //Todo: 그룹 즐겨찾기 설정
        //Todo: 일정 시간 계산해서 변경이 있으면 그룹 즐겨찾기 설정 api call
        Toast.makeText(getActivity(), "즐겨찾기 설정", Toast.LENGTH_SHORT).show();
    }

    public void addGroupMember() {
        Toast.makeText(getActivity(), "멤버 추가", Toast.LENGTH_SHORT).show();
        //Todo: show 친구 목록
        mUserListAdapter.addItem(mUserListAdapter.getItemCount() - 1, new User("ㅁㅁㅁ"));
        //Todo: 멤버 추가 api call
    }
}