package com.cleanup.exomvvm_simplecalc.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.exomvvm_simplecalc.model.SimpleCalc;

import java.util.Objects;

public class SimpleCalcViewModel extends ViewModel {

    private final SimpleCalc mSimpleCalc = new SimpleCalc();
    private String mCalcType = "+";
    private float mFirstNumber;
    private float mSecondNumber;


    private final MutableLiveData<String> calcResultMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessageMutableLiveData = new MutableLiveData<>();

    public SimpleCalcViewModel() {
    }

    public LiveData<String> getCalcResultLiveData() {
        return calcResultMutableLiveData;
    }

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageMutableLiveData;
    }

    public void setCalcType(String calcType) {
        mCalcType = calcType;
        this.calculate();
    }

    public void setFirstNumber(float firstNumber) {
        this.mFirstNumber = firstNumber;
        this.calculate();
    }

    public void setSecondNumber(float secondNumber) {
        this.mSecondNumber = secondNumber;
        this.calculate();
    }

    public void calculate() {
        calcResultMutableLiveData.setValue("");
        float calcResult = 0;
        switch (mCalcType) {
            case "+":
                calcResult = mSimpleCalc.performAddition(mFirstNumber, mSecondNumber);
                break;
            case "-":
                calcResult = mSimpleCalc.performSubtraction(mFirstNumber, mSecondNumber);
                break;
            case "×":
                calcResult = mSimpleCalc.performMultiplication(mFirstNumber, mSecondNumber);
                break;
            case "÷":
                if (mSecondNumber == 0) {
                    errorMessageMutableLiveData.setValue("Division par zéro impossible !\nVeuillez corriger le second nombre");
                    calcResultMutableLiveData.setValue("###");
                } else {
                    calcResult = mSimpleCalc.performDivision(mFirstNumber, mSecondNumber);
                }
                break;
        }
        if (!Objects.equals(calcResultMutableLiveData.getValue(), "###"))
            calcResultMutableLiveData.setValue(String.valueOf(calcResult));
    }
}