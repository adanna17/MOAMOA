package kr.co.mashup.moamoa.ui.tag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Tag;
import kr.co.mashup.moamoa.ui.base.BaseViewHolder;

public class TagViewHolder extends BaseViewHolder<Tag> {

    @BindView(R.id.textView_tag_name)
    TextView tvTagName;

    OnListItemListener<Tag> mListItemListener;

    public static TagViewHolder newInstance(ViewGroup parent, OnListItemListener<Tag> listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_tag, parent, false);
        return new TagViewHolder(itemView, listener);
    }

    public TagViewHolder(View itemView, OnListItemListener<Tag> listener) {
        super(itemView);

        mListItemListener = listener;
    }

    @Override
    public void bind(final Tag tag) {
        tvTagName.setText(String.format("# %s", tag.getName()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListItemListener != null) {
                    mListItemListener.onListItemClick(tag);
                }
            }
        });
    }
}
