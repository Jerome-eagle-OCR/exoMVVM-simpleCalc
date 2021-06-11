package com.cleanup.exomvvm_simplecalc.model;

public class SimpleCalc {

    public float performAddition(float firstNumber, float secondNumber) {
        return firstNumber + secondNumber;
    }

    public float performDivision(float firstNumber, float secondNumber) {
        float result = 0;
        try {
            result = firstNumber / secondNumber;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public float performMultiplication(float firstNumber, float secondNumber) {
        return firstNumber * secondNumber;
    }

    public float performSubtraction(float firstNumber, float secondNumber) {
        return firstNumber - secondNumber;
    }
}
