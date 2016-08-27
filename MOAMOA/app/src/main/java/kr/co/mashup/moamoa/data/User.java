package kr.co.mashup.moamoa.data;

import com.google.gson.annotations.SerializedName;

public class User {

    String kakaoId;

    @SerializedName("user_profile_image")
    String profileImage;

    String moamoaId;

    @SerializedName("user_profile_name")
    String nickname;

    public User(String kakaoId, String profileImage, String moamoaId, String nickname) {
        this.kakaoId = kakaoId;
        this.profileImage = profileImage;
        this.moamoaId = moamoaId;
        this.nickname = nickname;
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getMoamoaId() {
        return moamoaId;
    }

    public String getNickname() {
        return nickname;
    }
}
