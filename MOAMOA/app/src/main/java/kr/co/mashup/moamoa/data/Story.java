package kr.co.mashup.moamoa.data;

/**
 * Created by Dong on 2016-08-17.
 */
public class Story {

    String contentName;
    String groupName;
    String tagName;

    public Story(String contentName, String groupName, String tagName) {
        this.contentName = contentName;
        this.groupName = groupName;
        this.tagName = tagName;
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
}
