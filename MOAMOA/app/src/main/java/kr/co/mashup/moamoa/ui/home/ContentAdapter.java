package kr.co.mashup.moamoa.ui.home;


import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;
import java.util.List;

import kr.co.mashup.moamoa.R;

public class ContentAdapter extends RecyclerSwipeAdapter<ContentViewHolder> {

    private Context mContext;
    private List<ContentData> mDataset;
    private SwipeRefreshLayout refreshlayout;

    public ContentAdapter(Context mContext, SwipeRefreshLayout refreshlayout) {
        this.mContext = mContext;
        this.refreshlayout = refreshlayout;
        this.mDataset = new ArrayList<>();
        setData();
    }

    public void setData(){
        mDataset.clear();
        mDataset.add(new ContentData(R.drawable.content_example1,"Ramadan charity seeks to free 'innocent' Indian Muslims","http://www.bbc.com/"));
        mDataset.add(new ContentData(R.drawable.content_example2,"US sanctions North Korea's Kim Jong-un for the first time","http://www.bbc.com/"));
        mDataset.add(new ContentData(R.drawable.content_example3,"The new divide: Hard or soft Brexit?","http://www.bbc.com/"));
        mDataset.add(new ContentData(R.drawable.content_example4,"Keen to move to Canada?","http://www.bbc.com/"));
        mDataset.add(new ContentData(R.drawable.content_example5,"Samsung expects most profitable quarter in over two years","http://www.bbc.com/"));
        mDataset.add(new ContentData(R.drawable.content_example6,"Tata Steel 'to pause' Port Talbot sale","http://www.bbc.com/"));

    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.content_list_item, parent, false);
        return new ContentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ContentViewHolder holder, final int position) {

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right,
                holder.swipeLayout.findViewById(R.id.content_swipe_menu));

        holder.content_menu_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(holder.swipeLayout);
                mDataset.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataset.size());
                mItemManger.closeAllItems();
                Toast.makeText(mContext, "Deleted " , Toast.LENGTH_SHORT).show();
            }
        });

        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "item 선택", Toast.LENGTH_SHORT).show();
            }
        });

        holder.update(mDataset.get(position));
        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    private void refresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataset.add(0,new ContentData(R.drawable.default_profile,"Welcome to Mash Up","http://mash-up.co.kr"));
                ContentAdapter.this.notifyDataSetChanged();
                Toast.makeText(mContext, "refresh!!", Toast.LENGTH_SHORT).show();
                refreshlayout.setRefreshing(false);
            }
        }, 1000);
    }


    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_item;
    }
}
