package com.cleanup.exomvvm_simplecalc.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.exomvvm_simplecalc.R;
import com.cleanup.exomvvm_simplecalc.viewmodel.SimpleCalcViewModel;

public class SimpleCalcFragment extends Fragment {

    private SimpleCalcViewModel mViewModel;

    public static SimpleCalcFragment newInstance() {
        return new SimpleCalcFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_calc_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SimpleCalcViewModel.class);
        // TODO: Use the ViewModel
    }
}