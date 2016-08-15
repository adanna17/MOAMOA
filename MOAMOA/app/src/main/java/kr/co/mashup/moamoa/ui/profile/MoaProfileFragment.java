package kr.co.mashup.moamoa.ui.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.mashup.moamoa.R;

public class MoaProfileFragment extends Fragment {

    public MoaProfileFragment() {
        // Required empty public constructor
    }

    public static MoaProfileFragment newInstance() {
        MoaProfileFragment fragment = new MoaProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moa_profile, container, false);
    }

}
