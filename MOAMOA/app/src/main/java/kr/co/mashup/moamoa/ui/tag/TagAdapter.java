package kr.co.mashup.moamoa.ui.tag;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.mashup.moamoa.R;


public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {

    private Context mContext;
    private List<TagData> mDataset;

    public TagAdapter(Context mContext) {
        this.mContext = mContext;
        this.mDataset = new ArrayList<>();
        setData();
    }

    public void setData(){
        mDataset.clear();
        mDataset.add(new TagData("Food"));
        mDataset.add(new TagData("Server"));
        mDataset.add(new TagData("News"));

    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.tag_list_item, parent, false);
        return new TagViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        holder.txt_tag_title.setText("#" + mDataset.get(position).getTitle());

        if(position == mDataset.size()-1){
            holder.list_middle_line.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
