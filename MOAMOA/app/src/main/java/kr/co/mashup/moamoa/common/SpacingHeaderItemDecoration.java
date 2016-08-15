package kr.co.mashup.moamoa.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacingHeaderItemDecoration extends RecyclerView.ItemDecoration{

    private final int spacingFirstSize;

    public SpacingHeaderItemDecoration(int spacingFirstSize) {
        this.spacingFirstSize = spacingFirstSize;
    }

    @Override
    public void getItemOffsets(Rect outRect,  //viewholder에 바인딩된 사각형을 받아옴
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int currentPosition  = parent.getChildAdapterPosition(view);

        if (currentPosition == 0 ){
            outRect.top = spacingFirstSize; //actionbar에 가려지는 첫번째 아이템에만 위에 여백을 줌!
        }

    }
}

