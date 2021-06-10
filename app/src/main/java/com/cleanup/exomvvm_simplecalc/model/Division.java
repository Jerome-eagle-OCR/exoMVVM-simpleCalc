package com.cleanup.exomvvm_simplecalc.model;

public class Division {

    public float performDivision(float firstNumber, float secondNumber) {
        float result = 0;
        try {
            result = firstNumber / secondNumber;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
