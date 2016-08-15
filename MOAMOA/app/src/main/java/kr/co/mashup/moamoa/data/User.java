package kr.co.mashup.moamoa.data;

public class User {
    String kakaoId;
    String profileImage;
    String moamoaId;
    String nickname;

    public User(String kakaoId, String profileImage, String moamoaId, String nickname) {
        this.kakaoId = kakaoId;
        this.profileImage = profileImage;
        this.moamoaId = moamoaId;
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
