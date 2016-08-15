package kr.co.mashup.moamoa.ui.group;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.ItemDividerlDecoration;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.common.SpacingHeaderItemDecoration;
import kr.co.mashup.moamoa.data.Group;


public class MoaGroupFragment extends Fragment {

    @BindView(R.id.recyclerView_group)
    RecyclerView rvGroup;

    @BindDimen(R.dimen.moa_list_first_margin)
    int itemFirstSpacingSize;

    GroupListAdapter mGroupListAdapter;

    Unbinder mUnbinder;

    public MoaGroupFragment() {
    }

    public static MoaGroupFragment newInstance() {
        MoaGroupFragment fragment = new MoaGroupFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moa_group, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        initGroupList();
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_menu_add:
                String message = "추가버튼이 클릭되었습니다.";
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initGroupList() {
        mGroupListAdapter = new GroupListAdapter(getActivity());
        mGroupListAdapter.setOnListItemListener(new OnListItemListener<Group>() {
            @Override
            public void onListItemClick(Group item) {
                Toast.makeText(getActivity(), "item click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onListITemDelete(int position) {
            }
        });
        rvGroup.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvGroup.setHasFixedSize(true);
        rvGroup.setAdapter(mGroupListAdapter);
        rvGroup.addItemDecoration(new SpacingHeaderItemDecoration(itemFirstSpacingSize));
        rvGroup.addItemDecoration(new ItemDividerlDecoration(getActivity()));
    }
}
