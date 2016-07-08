package kr.co.mashup.moamoa.ui.category;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.mashup.moamoa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoaCategoryFragment extends Fragment {


    public MoaCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moa_category, container, false);
    }

}
