package kr.co.mashup.moamoa.server;


public class ServerUser {
    private String user_profile_image;
    private String user_profile_name;

    public ServerUser(String user_profile_image, String user_profile_name) {
        this.user_profile_image = user_profile_image;
        this.user_profile_name = user_profile_name;
    }

    public String getUser_profile_image() {
        return user_profile_image;
    }

    public String getUser_profile_name() {
        return user_profile_name;
    }
}
