package kr.co.mashup.moamoa.common;

import kr.co.mashup.moamoa.data.Content;

/**
 * Created by Dong on 2016-08-16.
 */
public interface OnSwipeMenuListener extends OnListItemListener<Content> {

    void onMoveGroup();  //Todo: 정의 수정

    void onContentTag();  //태그 설정  Todo: 정의 수정

    void onContentDelete(int position);  //삭제

    void onContentShare();  //공유 Todo: 정의 수정
}
