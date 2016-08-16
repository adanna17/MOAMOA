package kr.co.mashup.moamoa.ui.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

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
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.moa_group_add_title)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input(R.string.moa_group_add_hint, R.string.moa_group_add_prefill, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                createGroup(input.toString());
                            }
                        })
                        .positiveText(R.string.moa_group_add_positive)
                        .negativeText(R.string.moa_group_add_negative)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initGroupList() {
        mGroupListAdapter = new GroupListAdapter(getActivity());
        mGroupListAdapter.setOnListItemListener(new OnListItemListener<Group>() {
            @Override
            public void onListItemClick(Group item) {
                //그룹 상세화면으로 이동
                Intent intent = new Intent(getActivity(), GroupDetailActivity.class);
                intent.putExtra(GroupDetailActivity.GROUP, item);
                startActivity(intent);
            }
        });
        rvGroup.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvGroup.setHasFixedSize(true);
        rvGroup.setAdapter(mGroupListAdapter);
        rvGroup.addItemDecoration(new SpacingHeaderItemDecoration(itemFirstSpacingSize));  //상단 간격
        rvGroup.addItemDecoration(new ItemDividerlDecoration(getActivity()));  //아이템 하단 구분선
    }

    /**
     * 새 그룹 추가
     *
     * @param groupName 그룹 이름
     */
    private void createGroup(String groupName) {
        Group group = new Group(groupName);
        mGroupListAdapter.addItem(mGroupListAdapter.getItemCount(), group);
        Toast.makeText(getActivity(), "그룹이 추가되었습니다.", Toast.LENGTH_SHORT).show();

        //Todo: 그룹 추가 api call
    }
}
