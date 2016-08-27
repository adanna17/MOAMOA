package kr.co.mashup.moamoa.data;

import java.io.Serializable;

/**
 * 나의 스토리 데이터를 담는 POJO 객체
 */
public class Story implements Serializable {

    String contentName;

    //Todo: 1:N관계이므로 Array로 수정
    String groupName;  //스토리가 속한 그룹 이름
    String tagName;  //스토리가 속한 태그 이름

    String contentHighlight;  //컨텐츠에서 하이라이트한 문구
    String myOpinion;  //하이라이트한 문구에 대한 의견

    public Story(String contentName, String groupName, String tagName) {
        this.contentName = contentName;
        this.groupName = groupName;
        this.tagName = tagName;
    }

    public Story(String contentName, String groupName, String tagName, String contentHighlight, String myOpinion) {
        this.contentName = contentName;
        this.groupName = groupName;
        this.tagName = tagName;
        this.contentHighlight = contentHighlight;
        this.myOpinion = myOpinion;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getContentHighlight() {
        return contentHighlight;
    }

    public void setContentHighlight(String contentHighlight) {
        this.contentHighlight = contentHighlight;
    }

    public String getMyOpinion() {
        return myOpinion;
    }

    public void setMyOpinion(String myOpinion) {
        this.myOpinion = myOpinion;
    }
}
