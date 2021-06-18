package com.cleanup.exomvvm_simplecalc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.cleanup.exomvvm_simplecalc.model.SimpleCalc;

public class SimpleCalcViewModel extends ViewModel {

    private final SimpleCalc mSimpleCalc = new SimpleCalc();
    private String mCalcType = "";
    private float mFirstNumber;
    private float mSecondNumber;
    private boolean isFirstNumberOk;
    private boolean isSecondNumberOk;
    private boolean isCalculable;
    private float mCalcResult;
    private Snackable listener;

    private final MutableLiveData<Float> calcResultMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> firstNumberCorrectionMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> secondNumberCorrectionMutableLiveData = new MutableLiveData<>();

    public SimpleCalcViewModel() {
    }

    public void setListener(SimpleCalcFragment activity) {
        this.listener = activity;
    }

    private void sendErrorMessage(String errorMessage) {
        if (listener != null) {
            listener.showErrorMessage(errorMessage);
        }
    }

    public LiveData<String> getCalcResultLiveData() {
        return Transformations.map(calcResultMutableLiveData, calcResultLiveData -> {
            if (isFirstNumberOk && isSecondNumberOk && isCalculable) {
                return "" + calcResultLiveData;
            } else {
                return "...";
            }
        });
    }

    public LiveData<String> getFirstNumberCorrectionLiveData() {
        return firstNumberCorrectionMutableLiveData;
    }

    public LiveData<String> getSecondNumberCorrectionlivedata() {
        return secondNumberCorrectionMutableLiveData;
    }

    public void setCalcType(String calcType) {
        mCalcType = calcType;
        this.calculate();
    }

    public void setNumber(String number) {
        if (number.substring(1).isEmpty() || number.endsWith(".")) {
            if (number.charAt(0) == 'F') isFirstNumberOk = false;
            if (number.charAt(0) == 'S') isSecondNumberOk = false;
        } else {
            boolean unusefulZero = number.substring(1).startsWith("0") && number.length() > 2;
            switch (number.charAt(0)) {
                case 'F':
                    if (unusefulZero) {
                        firstNumberCorrectionMutableLiveData.setValue(number.substring(2));
                    }
                    mFirstNumber = Float.parseFloat(number.substring(1));
                    isFirstNumberOk = true;
                    break;
                case 'S':
                    if (unusefulZero) {
                        secondNumberCorrectionMutableLiveData.setValue(number.substring(2));
                    }
                    mSecondNumber = Float.parseFloat(number.substring(1));
                    isSecondNumberOk = true;
                    break;
            }
        }
        this.calculate();
    }

    public void calculate() {
        isCalculable = false;
        if (!mCalcType.isEmpty() && (!isFirstNumberOk || !isSecondNumberOk)) {
            this.sendErrorMessage("Au moins un nombre est invalide.\nVeuillez le corriger.");
        } else if (mCalcType.equals("÷") && mSecondNumber == 0) {
            this.sendErrorMessage("Division par zéro impossible !\nVeuillez modifier le second nombre.");
        } else if (mCalcType.isEmpty() && isFirstNumberOk && isSecondNumberOk) {
            this.sendErrorMessage("Sélectionner un opérateur pour afficher un résultat.\n");
        } else {
            isCalculable = true;
            switch (mCalcType) {
                case "+":
                    mCalcResult = mSimpleCalc.performAddition(mFirstNumber, mSecondNumber);
                    break;
                case "-":
                    mCalcResult = mSimpleCalc.performSubtraction(mFirstNumber, mSecondNumber);
                    break;
                case "×":
                    mCalcResult = mSimpleCalc.performMultiplication(mFirstNumber, mSecondNumber);
                    break;
                case "÷":
                    mCalcResult = mSimpleCalc.performDivision(mFirstNumber, mSecondNumber);
                    break;
                default:
                    isCalculable = false;
            }
        }
        calcResultMutableLiveData.setValue(mCalcResult);
    }
}