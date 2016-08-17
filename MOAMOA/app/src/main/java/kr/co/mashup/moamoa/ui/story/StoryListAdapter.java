package kr.co.mashup.moamoa.ui.story;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Story;

/**
 * Created by Dong on 2016-08-17.
 */
public class StoryListAdapter extends RecyclerView.Adapter<StoryViewHolder> {

    private Context mContext;
    private ArrayList<Story> mStories;

    //item click event
    private OnListItemListener<Story> mOnListItemListener;

    public void setOnListItemListener(OnListItemListener<Story> onListItemListener) {
        mOnListItemListener = onListItemListener;
    }

    public StoryListAdapter(Context context) {
        this.mContext = context;
        this.mStories = new ArrayList<>();
        setData();
    }

    public void setData() {
        mStories.clear();
        mStories.add(new Story("Ramadan charity seeks to free 'innocent' Indian Muslims", "안드로이드팀", "Mash-Up"));
        mStories.add(new Story("Ramadan charity seeks to free 'innocent' Indian Muslims", "안드로이드팀", "Mash-Up"));
        mStories.add(new Story("Ramadan charity seeks to free 'innocent' Indian Muslims", "안드로이드팀", "Mash-Up"));
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return StoryViewHolder.newInstance(parent, mOnListItemListener);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Story story = mStories.get(position);

        holder.bind(story);
    }

    @Override
    public int getItemCount() {
        return mStories != null ? mStories.size() : 0;
    }

    public void addItem(int position, Story story) {
        if (position < 0) {
            position = 0;
        }
        mStories.add(position, story);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mStories.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mStories.size());
    }
}
