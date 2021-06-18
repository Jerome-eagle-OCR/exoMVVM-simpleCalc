package com.cleanup.exomvvm_simplecalc;

import com.cleanup.exomvvm_simplecalc.model.SimpleCalc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleCalcTest {

    final SimpleCalc mSimpleCalc = new SimpleCalc();

    @Test
    public void performAddition() {
        //Given_OneAndOneEntered_When_PerformAddition_Then_ResultIsTwo
        assertEquals(2, mSimpleCalc.performAddition(1, 1), 0);
    }

    @Test
    public void performDivision() {
        //Given_OneAndTwoEntered_When_PerformDivision_Then_ResultIsOneHalf
        assertEquals(1 / 2f, mSimpleCalc.performDivision(1, 2), 0);
    }

    @Test
    public void performMultiplication() {
        //Given_TwoAndThreeEntered_When_PerformMultiplication_Then_ResultIsSix
        assertEquals(6, mSimpleCalc.performMultiplication(2, 3), 0);
    }

    @Test
    public void performSubtraction() {
        //Given_ThreeAndOneEntered_When_PerformSubtraction_Then_ResultIsTwo
        assertEquals(2, mSimpleCalc.performSubtraction(3, 1), 0);
    }
}