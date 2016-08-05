package kr.co.mashup.moamoa.ui.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

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
import kr.co.mashup.moamoa.server.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoaSignupActivity extends AppCompatActivity {

    public static final String TAG = MoaSignupActivity.class.getSimpleName();

    private UserProfile userProfile;
    private boolean check_text;
    private boolean check_repeat;

    @BindView(R.id.com_kakao_profileinformation)
    KakaoProfileInformation profileInformation;

    @BindView(R.id.edit_signup_moaid)
    EditText editText_moaid;

    @BindView(R.id.edit_signup_username)
    EditText editText_username;

    @OnClick(R.id.btn_signup_repeat)
    void Check_ID_repeat() {
        if (checkInputOnlyNumberAndAlphabet(editText_moaid.getText().toString())){
            check_text = true;

            RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
            Call<User> call = retrofitSingleton.getUserInfo("564574","http://mashup.co.kr","zzzzzz","김정민");

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.v(TAG ," " + response.body().getNickname());

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.v(TAG ," " + t.getMessage());
                }
            });
        }else{
            Toast.makeText(MoaSignupActivity.this, "이미 사용중이거나 사용할 수 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
            editText_moaid.setText("");
        }
    }

    @OnClick(R.id.btn_signup_finish)
    void Click_signup_finish(){
//        if (check_text){
//            Toast.makeText(MoaSignupActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
//            final Intent intent = new Intent(getApplicationContext(), MoaNoGroupActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            startActivity(intent);
//            finish();
//        }else{
//            Toast.makeText(MoaSignupActivity.this, "중복확인을 해주세요.", Toast.LENGTH_SHORT).show();
//        }
//        //check_repeat 이 true이면 다음 페이지로, false이면 아이디 중복확인이 되지 않았습니다 메세지 표시

        KakaoTalkService.requestProfile(new KakaoTalkResponseCallback<KakaoTalkProfile>() {
            @Override
            public void onSuccess(KakaoTalkProfile result) {
                Toast.makeText(getApplicationContext(), "success to get talk profile", Toast.LENGTH_SHORT).show();
                Log.v(TAG, result.getProfileImageUrl());
//                applyTalkProfileToView(result);
                //profileInformation.setUserProfile(result);
            }
        });

        Log.v(TAG, String.valueOf(userProfile.getId()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moa_signup);

        ButterKnife.bind(this);

        initializeProfileView();
    }

    private void initializeProfileView() {

        // 로그인 하면서 caching되어 있는 profile를 그린다.
        userProfile = UserProfile.loadFromCache();
        if (userProfile != null) {
            profileInformation.setUserProfile(userProfile);
        }
    }

    // profile view에서 talk profile을 update 한다.
    private void applyTalkProfileToView(final KakaoTalkProfile kakaoTalkProfile) {
        if (profileInformation != null) {
            if (userProfile != null) {
                profileInformation.setUserProfile(userProfile);
            }
            final String profileImageURL = userProfile.getProfileImagePath();
            if (profileImageURL != null)
                profileInformation.setProfileURL(profileImageURL);

            final String nickName = userProfile.getNickname();
            editText_username.setText(nickName);


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
                    Log.v("글자검사","소문자입니다.");
                }
                else if (chrInput >= 0x30 && chrInput <= 0x39) {
                    // 숫자 OK!
                    Log.v("글자검사","숫자입니다.");
                }
                else {
                    return false;   // 영문자도 아니고 숫자도 아님!
                }
            }
            return true;
        }
        return  false;
    }

    public abstract class KakaoTalkResponseCallback<T> extends TalkResponseCallback<T> {

        @Override
        public void onNotKakaoTalkUser() {
            //KakaoToast.makeToast(self, "not a KakaoTalk user", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(ErrorResult errorResult) {
            //KakaoToast.makeToast(self, "failure : " + errorResult, Toast.LENGTH_LONG).show();
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
