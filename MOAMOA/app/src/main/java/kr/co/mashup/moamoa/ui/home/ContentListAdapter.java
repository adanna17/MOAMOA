package kr.co.mashup.moamoa.ui.home;


import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;

import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Content;

public class ContentListAdapter extends RecyclerSwipeAdapter<ContentViewHolder> {

    private Context mContext;
    private ArrayList<Content> mContents;

    OnListItemListener<Content> mOnListItemListener;

    public void setOnListItemListener(OnListItemListener<Content> listener) {
        mOnListItemListener = listener;
    }

    public ContentListAdapter(Context mContext) {
        this.mContext = mContext;
        this.mContents = new ArrayList<>();
        setData();
    }

    public void setData() {
        mContents.clear();
        mContents.add(new Content(R.drawable.content_example1, "Ramadan charity seeks to free 'innocent' Indian Muslims", "http://www.bbc.com/"));
        mContents.add(new Content(R.drawable.content_example2, "US sanctions North Korea's Kim Jong-un for the first time", "http://www.bbc.com/"));
        mContents.add(new Content(R.drawable.content_example3, "The new divide: Hard or soft Brexit?", "http://www.bbc.com/"));
        mContents.add(new Content(R.drawable.content_example4, "Keen to move to Canada?", "http://www.bbc.com/"));
        mContents.add(new Content(R.drawable.content_example5, "Samsung expects most profitable quarter in over two years", "http://www.bbc.com/"));
        mContents.add(new Content(R.drawable.content_example6, "Tata Steel 'to pause' Port Talbot sale", "http://www.bbc.com/"));
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ContentViewHolder.newInstance(parent, mItemManger, mOnListItemListener);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {

        Content content = mContents.get(position);

        holder.bind(content);
    }

    @Override
    public int getItemCount() {
        return mContents != null ? mContents.size() : 0;
    }

    public void addItem(int position, Content content) {
        if (position < 0) {
            position = 0;
        }
        mContents.add(position, content);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mContents.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mContents.size());
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout_item;
    }
}
