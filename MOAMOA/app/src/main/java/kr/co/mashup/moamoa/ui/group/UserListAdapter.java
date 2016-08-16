package kr.co.mashup.moamoa.ui.group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Content;
import kr.co.mashup.moamoa.data.Group;
import kr.co.mashup.moamoa.data.User;

public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_ITEM = 1;
    public static final int VIEW_TYPE_FOOTER = 2;

    private Context mContext;
    private ArrayList<User> mUsers;

    //item click event
    private OnListItemListener mOnListItemListener;

    public void setOnListItemListener(OnListItemListener onListItemListener) {
        mOnListItemListener = onListItemListener;
    }

    public UserListAdapter(Context context) {
        this.mContext = context;
        this.mUsers = new ArrayList<>();
        setData();
    }

    public void setData() {
        mUsers.clear();
        mUsers.add(new User("박지훈"));
        mUsers.add(new User("김정민"));
        mUsers.add(new User("김태경"));
        mUsers.add(new User("유건욱"));
    }

    @Override
    public int getItemViewType(int position) {
        return position == mUsers.size() ? VIEW_TYPE_FOOTER : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return UserViewHolder.newInstance(parent);
            case VIEW_TYPE_FOOTER:
                return UserFooterViewHolder.newInstance(parent, mOnListItemListener);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof UserViewHolder) {
            User user = mUsers.get(position);
            ((UserViewHolder) holder).bind(user);
        } else {
            ((UserFooterViewHolder) holder).bind(null);
        }
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() + 1 : 1;
    }

    public void addFooter() {
        notifyItemInserted(mUsers.size());
    }

    public void addItem(int position, User user) {
        if (position < 0) {
            position = 0;
        }
        mUsers.add(position, user);
        notifyItemInserted(position + 1);
    }

    /**
     * 리스트 아이템 삭제
     *
     * @param position 어댑터의 포지션
     */
    public void removeItem(int position) {
        //Todo: remove posiotion 수정, 제대로 동작 안할 듯
        mUsers.remove(position);
        notifyItemRemoved(position - 1);
        notifyItemRangeChanged(position, mUsers.size());
    }
}
