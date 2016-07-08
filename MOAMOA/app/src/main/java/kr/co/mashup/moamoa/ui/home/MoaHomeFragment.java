package kr.co.mashup.moamoa.ui.home;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.mashup.moamoa.R;


public class MoaHomeFragment extends Fragment {

    @BindView(R.id.home_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.home_refresh)
    SwipeRefreshLayout refreshLayout;

    @BindDimen(R.dimen.moa_list_margin)
    int itemSpacingSize;

    @BindDimen(R.dimen.moa_list_first_margin)
    int itemFirstSpacingSize;

    @BindDimen(R.dimen.bottom_navigation_height)
    int itemLastSpacingSize;

    @BindColor(R.color.moa_gradient_start)
    int refresh_start_color;

    @BindColor(R.color.moa_gradient_end)
    int refresh_end_color;

    ContentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moa_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecyclerViewInit();
    }

    private void initRecyclerViewInit() {
        ButterKnife.bind(getActivity());
        refreshLayout.setColorSchemeColors(refresh_start_color, refresh_end_color);
        adapter = new ContentAdapter(getActivity().getApplicationContext(), refreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacingItemDecoration(itemSpacingSize,itemFirstSpacingSize,itemLastSpacingSize));
    }

}
