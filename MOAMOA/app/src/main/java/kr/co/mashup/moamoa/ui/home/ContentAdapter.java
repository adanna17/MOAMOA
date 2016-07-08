package kr.co.mashup.moamoa.ui.home;


import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.co.mashup.moamoa.R;

public class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {

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
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        holder.update(mDataset.get(position));
        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
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
}
