package kr.co.mashup.moamoa.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.co.mashup.moamoa.BuildConfig;
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
                .baseUrl("http://172.30.32.54:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(RetrofitService.class);

    }

    private OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    // http loging
    private HttpLoggingInterceptor makeLoggingInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    public Call<User> getUserInfo(String kakaoID, String profileImage, String moamoaId, String nickname){
        return service.userInfo(kakaoID, profileImage, moamoaId, nickname);
    }

    public Call<ServerResult> getCheckUser(String kakaoID){
        return service.checkUser(kakaoID);
    }

}
