package kr.co.mashup.moamoa.data.remote;

import kr.co.mashup.moamoa.data.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("user/login")
    Call<ServerResult> checkUser(@Field("user_kaokao_id") String kakaoID);

    @FormUrlEncoded
    @POST("user/register/repeat")
    Call<ServerResult> userRepeat(@Field("user_moa_id") String moamoaId);

    @FormUrlEncoded
    @POST("user/register/submit")
    Call<ServerResult> registerUser(
            @Field("user_kaokao_id") String kakaoID,
            @Field("user_profile_image") String kakaoProfileImage,
            @Field("user_moa_id") String moaId,
            @Field("user_profile_name") String moaNickname);

    @FormUrlEncoded
    @POST("user/info")
    Call<User> getUserInfo(@Field("user_moa_id") String moamoaId);

}
