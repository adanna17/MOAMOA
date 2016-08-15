package kr.co.mashup.moamoa.data;


import java.util.List;

import kr.co.mashup.moamoa.data.Contents;
import kr.co.mashup.moamoa.data.User;

public class Group {

    int id;  //고유 식별자
    String name;  //이름
    boolean favorite;  //즐겨찾기
    boolean bush;  //push 설정

    List<User> mUsers;  //그룹의 유저 리스트
    List<Contents> mContentses;  //그룹의 컨텐츠 리스트

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

    public boolean isBush() {
        return bush;
    }

    public void setBush(boolean bush) {
        this.bush = bush;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

    public List<Contents> getContentses() {
        return mContentses;
    }

    public void setContentses(List<Contents> contentses) {
        mContentses = contentses;
    }
}
