package kr.co.mashup.moamoa.ui.group;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.mashup.moamoa.R;

public class GroupViewHolder  extends RecyclerView.ViewHolder{

    @BindView(R.id.group_title)
    TextView txt_group_title;

    @BindView(R.id.list_middle_line)
    LinearLayout list_middle_line;

    public GroupViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
