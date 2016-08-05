package kr.co.mashup.moamoa.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import kr.co.mashup.moamoa.server.RetrofitSingleton;
import kr.co.mashup.moamoa.server.ServerResult;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.ui.main.MoaMainActivity;
import kr.co.mashup.moamoa.ui.signup.MoaSignupActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KakaoSessionCheck extends AppCompatActivity{

    public static final String TAG = KakaoSessionCheck.class.getSimpleName();

    /**
     * 유효한 세션이 있다는 검증 후
     * me를 호출하여 가입 여부에 따라 가입 페이지를 그리던지 Main 페이지로 이동 시킨다.
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }
    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    Log.v(TAG, "네트워크 불안정");
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.v(TAG, "세션 닫힘");
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(UserProfile userProfile) {

                RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
                Call<ServerResult> call = retrofitSingleton.getCheckUser(String.valueOf(userProfile.getId()));

                call.enqueue(new Callback<ServerResult>() {
                    @Override
                    public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                        Log.v(TAG,String.valueOf(response.body().getmResult()));
                        if (response.body().getmResult()){
                            redirectMainActivity();
                        }else{
                            redirectSignupActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResult> call, Throwable t) {
                        Log.v(TAG ," " + t.getMessage());
                    }
                });
            }

            @Override
            public void onNotSignedUp() {
                Log.v(TAG, "회원가입이 필요합니다.");
            }
        });
    }

    private void redirectSignupActivity() {
        startActivity(new Intent(KakaoSessionCheck.this, MoaSignupActivity.class));
        finish();
    }

    private void redirectMainActivity() {
        startActivity(new Intent(KakaoSessionCheck.this, MoaMainActivity.class));
        finish();
    }

    private void redirectLoginActivity() {
        new MaterialDialog.Builder(this)
                .content("로그인에 실패하였습니다.")
                .positiveText("확인")
                .positiveColorRes(R.color.moa_blue)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        startActivity(new Intent(KakaoSessionCheck.this, KakaoLoginActivity.class));
                        finish();
                    }
                })
                .show();
    }
}
