package kr.co.mashup.moamoa.ui.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kakao.kakaotalk.KakaoTalkService;
import com.kakao.kakaotalk.callback.TalkResponseCallback;
import com.kakao.kakaotalk.response.KakaoTalkProfile;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.response.model.UserProfile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.mashup.moamoa.common.KakaoProfileInformation;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.server.RetrofitSingleton;
import kr.co.mashup.moamoa.server.ServerResult;
import kr.co.mashup.moamoa.ui.init.MoaNoGroupActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoaSignupActivity extends AppCompatActivity {

    public static final String TAG = MoaSignupActivity.class.getSimpleName();

    private UserProfile mUserProfile;
    private boolean mCheckRepeat;

    @BindView(R.id.com_kakao_profileinformation)
    KakaoProfileInformation mProfileInformation;

    @BindView(R.id.edit_signup_moaid)
    EditText etMoaId;

    @BindView(R.id.edit_signup_username)
    EditText etNickname;

    @OnClick(R.id.btn_signup_repeat)
    void Check_ID_repeat() {
        if (checkInputOnlyNumberAndAlphabet(etMoaId.getText().toString())){

            RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
            Call<ServerResult> call = retrofitSingleton.getUserRepeat(etMoaId.getText().toString());

            call.enqueue(new Callback<ServerResult>() {
                @Override
                public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                    if (response.body().getmResult()){
                        idCheckFail();
                    }else{
                        idCheckSuccess();
                    }
                }

                @Override
                public void onFailure(Call<ServerResult> call, Throwable t) {
                    Log.v(TAG ," " + t.getMessage());
                }
            });
        }else{
            idCheckFail();
        }
    }

    @OnClick(R.id.btn_signup_finish)
    void Click_signup_finish(){
        if (mCheckRepeat){
            RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
            Call<ServerResult> call = retrofitSingleton.getRegisterUser(
                    String.valueOf(mUserProfile.getId()), mUserProfile.getProfileImagePath(),
                    etMoaId.getText().toString(), etNickname.getText().toString());

            call.enqueue(new Callback<ServerResult>() {
                @Override
                public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                    if (response.body().getmResult()){
                        Toast.makeText(MoaSignupActivity.this, R.string.moa_signup_finish_success, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MoaSignupActivity.this, MoaNoGroupActivity.class));
                        finish();
                    }else {
                        Toast.makeText(MoaSignupActivity.this, "다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ServerResult> call, Throwable t) {
                    Log.v(TAG ," " + t.getMessage());
                }
            });

        }else{
            Toast.makeText(MoaSignupActivity.this, R.string.moa_signup_finish_fail, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moa_signup);

        ButterKnife.bind(this);
        initializeProfileView();
    }

    private void initializeProfileView() {
        mUserProfile = UserProfile.loadFromCache();
        if (mUserProfile != null) {
            mProfileInformation.setUserProfile(mUserProfile);
        }

        KakaoTalkService.requestProfile(new KakaoTalkResponseCallback<KakaoTalkProfile>() {
            @Override
            public void onSuccess(KakaoTalkProfile result) {
                applyTalkProfileToView(result);
            }
        });
    }

    private void applyTalkProfileToView(final KakaoTalkProfile kakaoTalkProfile) {
        if (mProfileInformation != null) {
            final String profileImageURL = kakaoTalkProfile.getProfileImageUrl();
            if (profileImageURL != null)
                mProfileInformation.setProfileURL(profileImageURL);

            final String nickName = kakaoTalkProfile.getNickName();
            etNickname.setText(nickName);
        }
    }

    // 영소문자 숫자 검사
    private boolean checkInputOnlyNumberAndAlphabet(String textInput) {

        char chrInput;

        if (textInput.length() > 0){
            for (int i = 0; i < textInput.length(); i++) {

                chrInput = textInput.charAt(i); // 입력받은 텍스트에서 문자 하나하나 가져와서 체크

                if (chrInput >= 0x61 && chrInput <= 0x7A) {
                    // 영문(소문자) OK!
                }
                else if (chrInput >= 0x30 && chrInput <= 0x39) {
                    // 숫자 OK!
                }
                else {
                    return false;   // 영문자도 아니고 숫자도 아님!
                }
            }
            return true;
        }
        return  false;
    }

    private void idCheckFail() {
        Toast.makeText(MoaSignupActivity.this, R.string.moa_signup_repeat_fail, Toast.LENGTH_SHORT).show();
        etMoaId.setText("");
        mCheckRepeat = false;
    }

    private void idCheckSuccess() {
        new MaterialDialog.Builder(this)
                .content(R.string.moa_signup_repeat_success)
                .positiveText("확인")
                .positiveColorRes(R.color.moa_blue)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mCheckRepeat = true;
                        etMoaId.setEnabled(false);
                    }
                })
                .negativeText("취소")
                .negativeColorRes(R.color.moa_blue)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        etMoaId.setText("");
                    }
                })
                .show();
    }

    public abstract class KakaoTalkResponseCallback<T> extends TalkResponseCallback<T> {

        @Override
        public void onNotKakaoTalkUser() {
            Toast.makeText(MoaSignupActivity.this, "not a KakaoTalk user", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(ErrorResult errorResult) {
            Toast.makeText(MoaSignupActivity.this, "failure : " + errorResult, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onSessionClosed(ErrorResult errorResult) {
            //redirectLoginActivity();
        }

        @Override
        public void onNotSignedUp() {
            //redirectSignupActivity();
        }

        @Override
        public void onDidStart() {
            //showWaitingDialog();
        }

        @Override
        public void onDidEnd() {
           // cancelWaitingDialog();
        }
    }

}
