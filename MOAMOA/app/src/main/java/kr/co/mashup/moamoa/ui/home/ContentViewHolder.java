package kr.co.mashup.moamoa.ui.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;

import butterknife.BindView;
import butterknife.OnClick;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.OnListItemListener;
import kr.co.mashup.moamoa.common.OnSwipeMenuListener;
import kr.co.mashup.moamoa.data.Content;
import kr.co.mashup.moamoa.ui.base.BaseViewHolder;

public class ContentViewHolder extends BaseViewHolder<Content> {

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

    @BindView(R.id.content_menu_group)
    ImageView ivContentMenuGroup;

    @BindView(R.id.content_menu_tag)
    ImageView ivContentMenuTag;

    @BindView(R.id.imageView_content_menu_delete)
    ImageView ivContentMenuDelete;

    @BindView(R.id.content_menu_share)
    ImageView ivContentMenuShare;

    SwipeItemRecyclerMangerImpl mItemManger;  //swipeLayout controller
    OnSwipeMenuListener mSwipeMenuListener;

    public static ContentViewHolder newInstance(ViewGroup parent, @NonNull SwipeItemRecyclerMangerImpl itemManger,
                                                @Nullable OnSwipeMenuListener listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_content, parent, false);
        return new ContentViewHolder(itemView, itemManger, listener);
    }

    public ContentViewHolder(View itemView, @NonNull SwipeItemRecyclerMangerImpl itemManger,
                             @Nullable OnSwipeMenuListener listener) {
        super(itemView);

        mSwipeMenuListener = listener;
        mItemManger = itemManger;

        //swipe layout config
        itemSwipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        itemSwipeLayout.addDrag(SwipeLayout.DragEdge.Right, llContentSwipeMenu);
    }

    @Override
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
                if (mSwipeMenuListener != null) {
                    mSwipeMenuListener.onListItemClick(content);
                }
            }
        });

        ivContentMenuGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(itemSwipeLayout);
                mItemManger.closeAllItems();
                if (mSwipeMenuListener != null) {
                    //Todo: 수정
                    mSwipeMenuListener.onMoveGroup();
                }
            }
        });

        ivContentMenuTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(itemSwipeLayout);
                mItemManger.closeAllItems();
                if (mSwipeMenuListener != null) {
                    //Todo: 수정
                    mSwipeMenuListener.onContentTag();
                }
            }
        });

        ivContentMenuDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(itemSwipeLayout);
                mItemManger.closeAllItems();
                if (mSwipeMenuListener != null) {
                    mSwipeMenuListener.onContentDelete(getAdapterPosition());
                }
            }
        });

        ivContentMenuShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(itemSwipeLayout);
                mItemManger.closeAllItems();
                if (mSwipeMenuListener != null) {
                    //Todo: 수정
                    mSwipeMenuListener.onContentShare();
                }
            }
        });
    }
}
