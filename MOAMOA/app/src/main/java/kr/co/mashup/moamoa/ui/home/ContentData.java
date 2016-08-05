package kr.co.mashup.moamoa.ui.home;

public class ContentData {

    private int img;
    private String title;
    private String site;

    public ContentData(int img, String title, String site) {
        this.img = img;
        this.title = title;
        this.site = site;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getSite() {
        return site;
    }
}
