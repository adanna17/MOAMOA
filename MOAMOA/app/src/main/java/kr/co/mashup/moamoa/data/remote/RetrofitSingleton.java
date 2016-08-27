package kr.co.mashup.moamoa.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.co.mashup.moamoa.BuildConfig;
import kr.co.mashup.moamoa.data.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static RetrofitSingleton instance;
    private RetrofitService service;

    public static RetrofitSingleton getInstance(){
        if(instance == null){
            synchronized (RetrofitSingleton.class){
                if(instance == null){
                    instance = new RetrofitSingleton();
                }
            }
        }
        return instance;
    }

    private RetrofitSingleton(){

        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.14:3000/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(RetrofitService.class);
    }

    /**
     * HTTP Client 생성
     * @param httpLoggingInterceptor 로깅을 위해
     * @return HTTP Logging Interceptor가 들어간 OkHttpClient 클래스
     */
    private OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    /**
     * http loging 클래스 생성
     * @return Http Logging Interceptor 클래스 생성
     */
    private HttpLoggingInterceptor makeLoggingInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    public Call<ServerResult> getUserRepeat(String moamoaId){
        return service.userRepeat(moamoaId);
    }

    public Call<ServerResult> getCheckUser(String kakaoID){
        return service.checkUser(kakaoID);
    }

    public  Call<ServerResult> getRegisterUser(String kakaoID, String kakaoProfileImage, String moaId, String moaNickname){
        return service.registerUser(kakaoID,kakaoProfileImage, moaId, moaNickname);
    }

    public  Call<User> getUserInfo(String moamoaId){
        return service.getUserInfo(moamoaId);
    }

}
