package kr.co.mashup.moamoa.common;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kr.co.mashup.moamoa.R;

/**
 * 그룹, 태그 리스트 아이템 하단 구분선 데코레이터
 */
public class ItemDividerlDecoration extends RecyclerView.ItemDecoration {

    @Px
    private final int dividerHeightPx;
    @Px
    private final int dividerRightPaddingPx;

    private final Paint dividerPaint;

    public ItemDividerlDecoration(@NonNull Context context) {
        this.dividerPaint = new Paint();
        this.dividerPaint.setColor(ContextCompat.getColor(context, R.color.moa_text_hint));
        this.dividerHeightPx = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        this.dividerRightPaddingPx = context.getResources().getDimensionPixelSize(R.dimen.divider_right_padding);
    }

//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//        outRect.bottom = dividerHeightPx;
//    }

//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);
//
//        final int childCount = parent.getChildCount();
//        final int left = parent.getPaddingLeft() + dividerRightPaddingPx;
//        final int right = parent.getWidth() - parent.getPaddingRight();
//
//        for (int i = 0; i < childCount - 1; i++) {
//            View view = parent.getChildAt(i);
//            float top = view.getBottom();
//            float bottom = view.getBottom() + dividerHeightPx;
//            c.drawRect(left, top, right, bottom, dividerPaint);
//        }
//    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        final int childCount = parent.getChildCount();
        final int left = parent.getPaddingLeft() + dividerRightPaddingPx;
        final int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeightPx;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}
