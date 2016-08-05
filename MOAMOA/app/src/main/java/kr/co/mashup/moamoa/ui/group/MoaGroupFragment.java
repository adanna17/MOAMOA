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
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.SpacingItemDecoration_header;


public class MoaGroupFragment extends Fragment {

    @BindView(R.id.group_recycler_view)
    RecyclerView group_recyclerView;

    @BindDimen(R.dimen.moa_list_first_margin)
    int itemFirstSpacingSize;

    GroupAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_moa_group, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new GroupAdapter(getActivity().getApplicationContext());
        group_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        group_recyclerView.setAdapter(adapter);
        group_recyclerView.addItemDecoration(new SpacingItemDecoration_header(itemFirstSpacingSize));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.btn_menu_add :
                String message = "추가버튼이 클릭되었습니다.";
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
