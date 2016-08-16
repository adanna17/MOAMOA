package kr.co.mashup.moamoa.data;

import java.io.Serializable;

public class Tag implements Serializable{

    int id;  //고유 식별자
    private String name;  //이름

    public Tag(String name) {
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
}
