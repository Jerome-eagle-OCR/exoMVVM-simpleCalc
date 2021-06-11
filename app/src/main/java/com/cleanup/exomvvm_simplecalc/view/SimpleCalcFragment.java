package com.cleanup.exomvvm_simplecalc.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.exomvvm_simplecalc.R;
import com.cleanup.exomvvm_simplecalc.viewmodel.SimpleCalcViewModel;
import com.google.android.material.snackbar.Snackbar;

public class SimpleCalcFragment extends Fragment {

    private SimpleCalcViewModel mViewModel;

    private ToggleButton additionBtn;
    private ToggleButton subtractionBtn;
    private ToggleButton multiplicationBtn;
    private ToggleButton divisionBtn;
    private EditText firstNumber;
    private EditText secondNumber;
    private TextView result;

    public static SimpleCalcFragment newInstance() {
        return new SimpleCalcFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_calc_fragment, container, false);

        setWidgets(view);
        setListeners();

        return view;
    }

    private void setWidgets(View view) {
        additionBtn = view.findViewById(R.id.plus);
        additionBtn.setChecked(true);
        subtractionBtn = view.findViewById(R.id.minus);
        multiplicationBtn = view.findViewById(R.id.multiply);
        divisionBtn = view.findViewById(R.id.divide);
        firstNumber = view.findViewById(R.id.first_number);
        secondNumber = view.findViewById(R.id.second_number);
        result = view.findViewById(R.id.result);
    }

    private void setListeners() {
        additionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageToggleBtnCheck(v);
            }
        });
        subtractionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageToggleBtnCheck(v);
            }
        });
        multiplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageToggleBtnCheck(v);
            }
        });
        divisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageToggleBtnCheck(v);
            }
        });

        firstNumber.addTextChangedListener(new TextWatcher() {
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
        firstNumber.setText("0");
        secondNumber.addTextChangedListener(new TextWatcher() {
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
        secondNumber.setText("0");
    }

    private void manageToggleBtnCheck(View v) {
        additionBtn.setChecked(false);
        subtractionBtn.setChecked(false);
        multiplicationBtn.setChecked(false);
        divisionBtn.setChecked(false);
        ToggleButton vCasted = (ToggleButton) v;
        vCasted.setChecked(true);
        mViewModel.setCalcType(vCasted.getTextOff().toString());
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SimpleCalcViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getCalcResultLiveData().observe(this, calcResult -> result.setText(String.valueOf(calcResult)));
        mViewModel.getErrorMessageLiveData().observe(this, s -> Snackbar.make(requireView(), s,
                Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.teal_700)).show());
    }
}