package com.cleanup.exomvvm_simplecalc.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.exomvvm_simplecalc.R;
import com.cleanup.exomvvm_simplecalc.databinding.SimpleCalcFragmentBinding;
import com.cleanup.exomvvm_simplecalc.viewmodel.SimpleCalcViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

public class SimpleCalcFragment extends Fragment {

    private SimpleCalcFragmentBinding mBinding;

    private SimpleCalcViewModel mViewModel;

    public static SimpleCalcFragment newInstance() {
        return new SimpleCalcFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = SimpleCalcFragmentBinding.inflate(inflater, container, false);
        View view = mBinding.getRoot();
        setListeners();
        return view;
    }

    private void setListeners() {
        mBinding.plus.setChecked(true);
        for (AppCompatToggleButton appCompatToggleButton : Arrays.asList(mBinding.plus, mBinding.minus, mBinding.multiply, mBinding.divide)) {
            appCompatToggleButton.setOnClickListener(this::manageToggleBtnCheck);
        }
        mBinding.firstNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.setNumber("F" + s.toString());
            }
        });
        mBinding.firstNumber.setText("0");
        mBinding.secondNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.setNumber("S" + s.toString());
            }
        });
        mBinding.secondNumber.setText("0");
    }

    private void manageToggleBtnCheck(View v) {
        mBinding.plus.setChecked(false);
        mBinding.minus.setChecked(false);
        mBinding.multiply.setChecked(false);
        mBinding.divide.setChecked(false);
        AppCompatToggleButton vCasted = (AppCompatToggleButton) v;
        vCasted.setChecked(true);
        mViewModel.setCalcType(vCasted.getTextOff().toString());
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SimpleCalcViewModel.class);
        mViewModel.getCalcResultLiveData().observe(this, calcResult -> mBinding.result.setText(String.valueOf(calcResult)));
        mViewModel.getErrorMessageLiveData().observe(this, s -> Snackbar.make(requireView(), s,
                Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.teal_700)).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}