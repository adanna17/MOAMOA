package kr.co.mashup.moamoa.ui.story;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Story;
import kr.co.mashup.moamoa.ui.base.BaseViewHolder;

/**
 * Created by Dong on 2016-08-17.
 */
public class StoryViewHolder extends BaseViewHolder<Story> {

    @BindView(R.id.textView_content_name)
    TextView tvContentName;

    @BindView(R.id.textView_group_name)
    TextView tvGroupName;

    @BindView(R.id.textView_tag_name)
    TextView tvTagName;

    OnListItemListener<Story> mListItemListener;

    public static StoryViewHolder newInstance(ViewGroup parent, @Nullable OnListItemListener<Story> listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_story, parent, false);
        return new StoryViewHolder(itemView, listener);
    }

    public StoryViewHolder(View itemView, @Nullable OnListItemListener<Story> listener) {
        super(itemView);

        mListItemListener = listener;
    }

    @Override
    public void bind(final Story story) {

        tvContentName.setText(story.getContentName());
        tvGroupName.setText(story.getGroupName());
        tvTagName.setText(story.getTagName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListItemListener != null) {
                    mListItemListener.onListItemClick(story);
                }
            }
        });
    }
}
