package kr.co.mashup.moamoa.ui.group;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.ui.base.BaseViewHolder;

/**
 * Created by Dong on 2016-08-16.
 */
public class UserFooterViewHolder extends BaseViewHolder {

    OnListItemListener mListItemListener;

    public static UserFooterViewHolder newInstance(ViewGroup parent, @Nullable OnListItemListener listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_footer, parent, false);
        return new UserFooterViewHolder(itemView, listener);
    }

    public UserFooterViewHolder(View itemView, @Nullable OnListItemListener listener) {
        super(itemView);

        mListItemListener = listener;
    }

    @Override
    public void bind(Object o) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListItemListener != null) {
                    mListItemListener.onListItemClick(null);
                }
            }
        });
    }
}
