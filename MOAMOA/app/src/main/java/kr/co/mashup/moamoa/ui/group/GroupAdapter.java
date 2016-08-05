package kr.co.mashup.moamoa.ui.group;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.mashup.moamoa.R;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    private Context mContext;
    private List<GroupData> mDataset;

    public GroupAdapter(Context mContext) {
        this.mContext = mContext;
        this.mDataset = new ArrayList<>();
        setData();
    }

    public void setData(){
        mDataset.clear();
        mDataset.add(new GroupData("Mash-Up"));
        mDataset.add(new GroupData("안드로이드팀"));
        mDataset.add(new GroupData("프로젝트 3팀"));

    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.group_list_item, parent, false);
        return new GroupViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        holder.txt_group_title.setText(mDataset.get(position).getTitle());

        if(position == mDataset.size()-1){
            holder.list_middle_line.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return  mDataset.size();
    }
}
