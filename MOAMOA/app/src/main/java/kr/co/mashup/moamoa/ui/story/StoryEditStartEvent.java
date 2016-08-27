package kr.co.mashup.moamoa.ui.story;

import kr.co.mashup.moamoa.data.Story;

/**
 * 나의 스토리 내용 수정 시작을 알리는 이벤트
 */
public class StoryEditStartEvent {

    private Story mStory;

    public StoryEditStartEvent(Story story) {
        mStory = story;
    }

    public Story getStory() {
        return mStory;
    }

    public void setStory(Story story) {
        mStory = story;
    }
}
