package kr.co.mashup.moamoa.ui.group;


import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Group;
import kr.co.mashup.moamoa.ui.base.BaseViewHolder;

public class GroupViewHolder extends BaseViewHolder<Group> {

    @BindView(R.id.textView_group_name)
    TextView tvGroupName;  //이름

    @BindView(R.id.imageView_group_favorite)
    ImageView ivGroupFavorite;  //즐겨찾기 아이콘

    OnListItemListener<Group> mListItemListener;

    public static GroupViewHolder newInstance(ViewGroup parent, @Nullable OnListItemListener<Group> listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_group, parent, false);
        return new GroupViewHolder(itemView, listener);
    }

    public GroupViewHolder(View itemView, OnListItemListener<Group> listener) {
        super(itemView);

        mListItemListener = listener;
    }

    @Override
    public void bind(final Group group) {
        tvGroupName.setText(group.getName());

        if (group.isFavorite()) {  //즐겨찾기 아이콘 표시 여부
            ivGroupFavorite.setVisibility(View.VISIBLE);
        } else {
            ivGroupFavorite.setVisibility(View.GONE);
        }

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
