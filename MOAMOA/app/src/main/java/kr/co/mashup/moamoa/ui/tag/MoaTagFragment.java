package kr.co.mashup.moamoa.ui.tag;


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
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.SpacingItemDecoration_header;
import kr.co.mashup.moamoa.ui.group.GroupAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoaTagFragment extends Fragment {

    @BindView(R.id.tag_recycler_view)
    RecyclerView tag_recyclerView;

    @BindDimen(R.dimen.moa_list_first_margin)
    int itemFirstSpacingSize;

    TagAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moa_tag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new TagAdapter(getActivity().getApplicationContext());
        tag_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        tag_recyclerView.setAdapter(adapter);
        tag_recyclerView.addItemDecoration(new SpacingItemDecoration_header(itemFirstSpacingSize));
    }
}
