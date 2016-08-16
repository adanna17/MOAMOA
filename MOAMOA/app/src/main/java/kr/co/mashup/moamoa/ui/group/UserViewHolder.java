package kr.co.mashup.moamoa.ui.group;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.data.User;
import kr.co.mashup.moamoa.ui.base.BaseViewHolder;

public class UserViewHolder extends BaseViewHolder<User> {

    @BindView(R.id.sdv_user_profile)
    SimpleDraweeView sdvUserProfile;

    @BindView(R.id.textView_user_name)
    TextView tvUserName;

    public static UserViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user, parent, false);
        return new UserViewHolder(itemView);
    }

    public UserViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(User user) {

//        sdvUserProfile.setImageURI(user.getProfileImage());
        tvUserName.setText(user.getNickname());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
            }
        });
    }
}
