package kr.co.mashup.moamoa.ui.tag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Tag;


public class TagListAdapter extends RecyclerView.Adapter<TagViewHolder> {

    private Context mContext;
    private ArrayList<Tag> mTags;

    //item click event
    private OnListItemListener<Tag> mOnListItemListener;

    public void setOnListItemListener(OnListItemListener<Tag> listener) {
        mOnListItemListener = listener;
    }

    public TagListAdapter(Context context) {
        this.mContext = context;
        this.mTags = new ArrayList<>();
        setData();
    }

    public void setData() {
        mTags.clear();
        mTags.add(new Tag("Food"));
        mTags.add(new Tag("Server"));
        mTags.add(new Tag("News"));
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TagViewHolder.newInstance(parent, mOnListItemListener);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        Tag tag = mTags.get(position);

        holder.bind(tag);
    }

    @Override
    public int getItemCount() {
        return mTags != null ? mTags.size() : 0;
    }

    public void addItem(int position, Tag tag) {
        if (position < 0) {
            position = 0;
        }
        mTags.add(position, tag);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mTags.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mTags.size());
    }
}
