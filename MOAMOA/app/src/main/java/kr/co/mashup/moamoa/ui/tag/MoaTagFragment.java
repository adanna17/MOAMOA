package kr.co.mashup.moamoa.ui.tag;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import kr.co.mashup.moamoa.data.Tag;

public class MoaTagFragment extends Fragment {

    @BindView(R.id.recyclerView_tag)
    RecyclerView rvTag;

    @BindDimen(R.dimen.moa_list_first_margin)
    int itemFirstSpacingSize;

    TagListAdapter mTagListAdapter;

    Unbinder mUnbinder;

    public MoaTagFragment() {
    }

    public static MoaTagFragment newInstance() {
        MoaTagFragment fragment = new MoaTagFragment();
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
        return inflater.inflate(R.layout.fragment_moa_tag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        initTagList();
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
            case R.id.action_add:
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.moa_tag_add_title)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input(R.string.moa_tag_add_hint, R.string.moa_tag_add_prefill, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                createTag(input.toString());
                            }
                        })
                        .positiveText(R.string.moa_tag_add_positive)
                        .negativeText(R.string.moa_tag_add_negative)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTagList() {
        mTagListAdapter = new TagListAdapter(getActivity());
        mTagListAdapter.setOnListItemListener(new OnListItemListener<Tag>() {
            @Override
            public void onListItemClick(Tag item) {
                Toast.makeText(getActivity(), "item click", Toast.LENGTH_SHORT).show();
            }
        });
        rvTag.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvTag.setHasFixedSize(true);
        rvTag.setAdapter(mTagListAdapter);
        rvTag.addItemDecoration(new SpacingHeaderItemDecoration(itemFirstSpacingSize));  //상단 간격
        rvTag.addItemDecoration(new ItemDividerlDecoration(getActivity()));  //아이템 하단 구분선
    }

    /**
     * 새 태그 추가
     *
     * @param tagName 태그 이름
     */
    private void createTag(String tagName) {
        Tag tag = new Tag(tagName);
        mTagListAdapter.addItem(mTagListAdapter.getItemCount(), tag);
        Toast.makeText(getActivity(), "태그가 추가되었습니다.", Toast.LENGTH_SHORT).show();

        //Todo: 태그 추가 api call
    }
}
