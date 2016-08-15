package kr.co.mashup.moamoa.ui.group;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Group;

//Todo: 즐겨찾기 아이콘 넣기
public class GroupViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textView_group_name)
    TextView tvGroupName;

    OnListItemListener<Group> mListItemListener;

    public static GroupViewHolder newInstance(ViewGroup parent, OnListItemListener<Group> listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_group, parent, false);
        return new GroupViewHolder(itemView, listener);
    }

    public GroupViewHolder(View itemView, OnListItemListener<Group> listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mListItemListener = listener;
    }

    public void bind(final Group group) {
        tvGroupName.setText(group.getName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListItemListener != null) {
                    mListItemListener.onListItemClick(group);
                }
            }
        });
    }
}
