package kr.co.mashup.moamoa.ui.story;

import kr.co.mashup.moamoa.data.Story;

/**
 * 나의 스토리 내용 수정 완료를 알리는 이벤트
 */
public class StoryEditResultEvent {

    private Story mStory;

    public StoryEditResultEvent(Story story) {
        mStory = story;
    }

    public Story getStory() {
        return mStory;
    }

    public void setStory(Story story) {
        mStory = story;
    }
}
