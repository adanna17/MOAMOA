package kr.co.mashup.moamoa.ui.tag;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.mashup.moamoa.R;

public class TagViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tag_title)
    TextView txt_tag_title;

    @BindView(R.id.list_middle_line)
    LinearLayout list_middle_line;

    public TagViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
