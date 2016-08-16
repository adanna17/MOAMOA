package kr.co.mashup.moamoa.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.ui.base.BaseViewHolder;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by Dong on 2016-08-15.
 */
public class ContentFooterViewHolder extends BaseViewHolder {

    @BindView(R.id.progressBar)
    MaterialProgressBar mProgressBar;

    public static ContentFooterViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_content_footer, parent, false);
        return new ContentFooterViewHolder(itemView);
    }

    public ContentFooterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Object o) {
        mProgressBar.setIndeterminate(true);
    }
}
