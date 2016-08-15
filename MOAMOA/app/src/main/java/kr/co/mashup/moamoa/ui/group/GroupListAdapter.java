package kr.co.mashup.moamoa.ui.group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Group;

public class GroupListAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    private Context mContext;
    private ArrayList<Group> mGroups;

    //item click event
    private OnListItemListener<Group> mOnListItemListener;

    public void setOnListItemListener(OnListItemListener<Group> listener) {
        mOnListItemListener = listener;
    }

    public GroupListAdapter(Context mContext) {
        this.mContext = mContext;
        this.mGroups = new ArrayList<>();
        setData();
    }

    public void setData() {
        mGroups.clear();
        mGroups.add(new Group("Mash-Up"));
        mGroups.add(new Group("안드로이드팀"));
        mGroups.add(new Group("프로젝트 3팀"));
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return GroupViewHolder.newInstance(parent, mOnListItemListener);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        Group group = mGroups.get(position);

        holder.bind(group);
    }

    @Override
    public int getItemCount() {
        return mGroups != null ? mGroups.size() : 0;
    }

    public void addItem(int position, Group group) {
        if (position < 0) {
            position = 0;
        }
        mGroups.add(position, group);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mGroups.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mGroups.size());
    }
}
