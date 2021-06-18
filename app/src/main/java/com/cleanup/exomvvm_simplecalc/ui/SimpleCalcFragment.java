package com.cleanup.exomvvm_simplecalc.ui;

import android.annotation.SuppressLint;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

public class SimpleCalcFragment extends Fragment implements Snackable {

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
        mViewModel.setListener(this);
        mViewModel.getCalcResultLiveData().observe(this, calcResult -> mBinding.result.setText(calcResult));
        mViewModel.getFirstNumberCorrectionLiveData().observe(this, s -> {
            mBinding.firstNumber.setText(s);
            mBinding.firstNumber.setSelection(mBinding.firstNumber.length());
        });
        mViewModel.getSecondNumberCorrectionlivedata().observe(this, s -> {
            mBinding.secondNumber.setText(s);
            mBinding.secondNumber.setSelection(mBinding.secondNumber.length());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @SuppressLint("ShowToast")
    @Override
    public void showErrorMessage(String errorMessage) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(SimpleCalcFragment.this.getResources().getColor(R.color.teal_700))
                .setAnchorView(R.id.snackbar_text).show();
    }
}