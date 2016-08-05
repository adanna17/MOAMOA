package kr.co.mashup.moamoa.ui.init;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.KakaoProfileInformation;
import kr.co.mashup.moamoa.ui.main.MoaMainActivity;

public class MoaNoGroupActivity extends AppCompatActivity {

    private UserProfile userProfile;

    @BindView(R.id.com_kakao_profileinformation)
    KakaoProfileInformation profileInformation;

    @OnClick(R.id.btn_init_pass)
    void ClickPass(){
        final Intent intent = new Intent(getApplicationContext(), MoaMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moa_nogroup);

        ButterKnife.bind(this);

        initializeProfileView();
    }

    private void initializeProfileView() {

        // 로그인 하면서 caching되어 있는 profile를 그린다.
        userProfile = UserProfile.loadFromCache();
        if (userProfile != null) {
            profileInformation.setUserProfile(userProfile);
        }

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e("TAG", "Session closed");
            }

            @Override
            public void onNotSignedUp() {
                Log.e("TAG", "No signedUp");
            }

            @Override
            public void onSuccess(UserProfile result) {
                applyTalkProfileToView(result);
            }
        });
    }

    // profile view에서 talk profile을 update 한다.
    private void applyTalkProfileToView(final UserProfile userProfile) {
        if (profileInformation != null) {
            if (userProfile != null) {
                profileInformation.setUserProfile(userProfile);
            }
            final String profileImageURL = userProfile.getProfileImagePath();
            if (profileImageURL != null)
                profileInformation.setProfileURL(profileImageURL);

        }
    }
}
