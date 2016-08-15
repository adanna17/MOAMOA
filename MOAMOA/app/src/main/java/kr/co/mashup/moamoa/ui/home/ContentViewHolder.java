package kr.co.mashup.moamoa.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.data.Content;

public class ContentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textView_content_name)
    TextView tvContentName;

    @BindView(R.id.textView_content_url)
    TextView tvContentUrl;

    @BindView(R.id.imageView_content_thumbnail)
    ImageView ivContentThumbnail;

    @BindView(R.id.swipeLayout_item)
    SwipeLayout itemSwipeLayout;

    @BindView(R.id.ll_content_swipe_menu)
    LinearLayout llContentSwipeMenu;

    @BindView(R.id.textView_content_menu_title)
    TextView tvContentMenuTitle;

    @BindView(R.id.textView_content_menu_url)
    TextView tvContentMenuUrl;

    @BindView(R.id.imageView_content_menu_delete)
    ImageView ivContentMenuDelete;

    SwipeItemRecyclerMangerImpl mItemManger;  //swipeLayout controller
    OnListItemListener<Content> mListItemListener;  //item click listener

    public static ContentViewHolder newInstance(ViewGroup parent, SwipeItemRecyclerMangerImpl itemManger,
                                                OnListItemListener<Content> listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_content, parent, false);
        return new ContentViewHolder(itemView, itemManger, listener);
    }

    public ContentViewHolder(View itemView, SwipeItemRecyclerMangerImpl itemManger,
                             OnListItemListener<Content> listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mListItemListener = listener;
        mItemManger = itemManger;

        //swipe layout config
        itemSwipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        itemSwipeLayout.addDrag(SwipeLayout.DragEdge.Right, llContentSwipeMenu);
    }

    public void bind(final Content content) {

        //data bind
        ivContentThumbnail.setImageResource(content.getImg());
        tvContentName.setText(content.getTitle());
        tvContentUrl.setText(content.getSite());
        tvContentMenuTitle.setText(content.getTitle());
        tvContentMenuUrl.setText(content.getSite());

        //listener bind
        mItemManger.bindView(itemView, getAdapterPosition());
        itemSwipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListItemListener != null) {
                    mListItemListener.onListItemClick(content);
                }
            }
        });

        ivContentMenuDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(itemSwipeLayout);
                mItemManger.closeAllItems();
                if (mListItemListener != null) {
                    mListItemListener.onListITemDelete(getAdapterPosition());
                }

            }
        });
    }
}
