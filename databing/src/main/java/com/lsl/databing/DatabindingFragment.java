package com.lsl.databing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsl.databing.databinding.FragmentDatabindBinding;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/7/4.
 */

public class DatabindingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDatabindBinding binding=FragmentDatabindBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
