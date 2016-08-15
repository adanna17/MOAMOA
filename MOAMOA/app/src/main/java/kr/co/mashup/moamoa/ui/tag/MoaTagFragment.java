package kr.co.mashup.moamoa.ui.tag;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moa_tag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        mTagListAdapter = new TagListAdapter(getActivity());
        mTagListAdapter.setOnListItemListener(new OnListItemListener<Tag>() {
            @Override
            public void onListItemClick(Tag item) {
                Toast.makeText(getActivity(), "item click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onListITemDelete(int position) {
            }
        });
        rvTag.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvTag.setHasFixedSize(true);
        rvTag.setAdapter(mTagListAdapter);
        rvTag.addItemDecoration(new SpacingHeaderItemDecoration(itemFirstSpacingSize));
        rvTag.addItemDecoration(new ItemDividerlDecoration(getActivity()));  //아이템 하단 구분선
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
