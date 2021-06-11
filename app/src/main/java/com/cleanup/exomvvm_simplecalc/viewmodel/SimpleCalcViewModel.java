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
    private boolean isFirstNumberOk;
    private boolean isSecondNumberOk;


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

    public void setNumber(String number) {
        if (number.substring(1).isEmpty() || number.endsWith(".")) {
            calcResultMutableLiveData.setValue("###");
            if (number.charAt(0) == 'F') isFirstNumberOk = false;
            if (number.charAt(0) == 'S') isSecondNumberOk = false;
        } else {
            switch (number.charAt(0)) {
                case 'F':
                    this.mFirstNumber = Float.parseFloat(number.substring(1));
                    isFirstNumberOk = true;
                    break;
                case 'S':
                    this.mSecondNumber = Float.parseFloat(number.substring(1));
                    isSecondNumberOk = true;
                    break;
            }
            this.calculate();
        }
    }

    public void calculate() {
        calcResultMutableLiveData.setValue("");
        float calcResult = 0;
        if (!isFirstNumberOk || !isSecondNumberOk) {
            calcResultMutableLiveData.setValue("###");
        } else {
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
        }
        if (!Objects.equals(calcResultMutableLiveData.getValue(), "###"))
            calcResultMutableLiveData.setValue(String.valueOf(calcResult));
    }
}