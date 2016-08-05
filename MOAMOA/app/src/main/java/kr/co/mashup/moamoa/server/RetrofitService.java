package kr.co.mashup.moamoa.server;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("register/repeat")
    Call<User> userInfo(
            @Field("kakaoId") String kakaoID,
            @Field("profileImage") String profileImage,
            @Field("moamoaId") String moamoaId,
            @Field("nickname") String nickname);

    @FormUrlEncoded
    @POST("login")
    Call<ServerResult> checkUser(@Field("kakaoId") String kakaoID);

}
