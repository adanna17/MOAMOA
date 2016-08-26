package kr.co.mashup.moamoa.ui.init;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.common.KakaoProfileInformation;
import kr.co.mashup.moamoa.server.RetrofitSingleton;
import kr.co.mashup.moamoa.server.ServerUser;
import kr.co.mashup.moamoa.ui.main.MoaMainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoaNoGroupActivity extends AppCompatActivity {

    public static final String TAG = MoaNoGroupActivity.class.getSimpleName();

    //String userMoaId;

    @BindView(R.id.com_kakao_profileinformation)
    KakaoProfileInformation profileInformation;

    @BindView(R.id.tv_nogroup_moaname)
    TextView tvMoaName;

    @OnClick(R.id.btn_init_newgroup)
    void clickNewGroup(){
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

        //initializeProfileView();
    }

//    private void initializeProfileView() {
//
//        Intent intent = getIntent();
//        userMoaId = intent.getExtras().getString("moaId");
//
//
//        RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
//        Call<ServerUser> call = retrofitSingleton.getUserInfo(userMoaId);
//
//        call.enqueue(new Callback<ServerUser>() {
//            @Override
//            public void onResponse(Call<ServerUser> call, Response<ServerUser> response) {
//                tvMoaName.setText("현재 '" + response.body().getUser_profile_name() + "' 님이 속한 그룹이 없습니다.");
//                profileInformation.setProfileURL(response.body().getUser_profile_image());
//            }
//
//            @Override
//            public void onFailure(Call<ServerUser> call, Throwable t) {
//                Toast.makeText(MoaNoGroupActivity.this, R.string.moa_error,Toast.LENGTH_SHORT).show();
//                Log.v(TAG ," " + t.getMessage());
//            }
//        });
//    }

}
