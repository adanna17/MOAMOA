package kr.co.mashup.moamoa.data;


import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {

    int id;  //고유 식별자
    String name;  //이름
    boolean favorite;  //즐겨찾기
    boolean push;  //push 설정

    List<User> mUsers;  //그룹의 유저 리스트
    List<Content> mContents;  //그룹의 컨텐츠 리스트

    public Group(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

    public List<Content> getContents() {
        return mContents;
    }

    public void setContentses(List<Content> contents) {
        mContents = contents;
    }
}
