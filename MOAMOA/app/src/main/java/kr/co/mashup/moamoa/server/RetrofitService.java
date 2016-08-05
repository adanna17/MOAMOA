package kr.co.mashup.moamoa.server;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("register/repeat")
    Call<ServerResult> userRepeat(@Field("moamoaId") String moamoaId);

    @FormUrlEncoded
    @POST("login")
    Call<ServerResult> checkUser(@Field("kakaoId") String kakaoID);

    @FormUrlEncoded
    @POST("register/submit")
    Call<ServerResult> registerUser(
            @Field("kakaoId") String kakaoID,
            @Field("kakaoProfileImage") String kakaoProfileImage,
            @Field("moaId") String moaId,
            @Field("moaNickname") String moaNickname);

}
