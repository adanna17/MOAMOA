package kr.co.mashup.moamoa.ui.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.mashup.moamoa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoaSettingFragment extends Fragment {


    public MoaSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moa_setting, container, false);
    }

}
