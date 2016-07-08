package kr.co.mashup.moamoa.ui.home;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.mashup.moamoa.R;

public class ContentViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.content_title)
    TextView txt_content_title;

    @BindView(R.id.content_site)
    TextView txt_content_site;

    @BindView(R.id.content_thumbnail)
    ImageView img_content_thumbnail;

    @BindView(R.id.swipe_item)
    SwipeLayout swipeLayout;

    @BindView(R.id.content_menu_title)
    TextView content_menu_title;

    @BindView(R.id.content_menu_site)
    TextView content_menu_site;

    @BindView(R.id.content_menu_delete)
    ImageView content_menu_delete;


    public ContentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void update(final ContentData data){
        img_content_thumbnail.setImageResource(data.getImg());
        txt_content_title.setText(data.getTitle());
        txt_content_site.setText(data.getSite());
        content_menu_title.setText(data.getTitle());
        content_menu_site.setText(data.getSite());
    }
}
