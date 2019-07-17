package ru.otus.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.qa.service.CalculatorService;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorServiceTests {

    @Autowired
    private CalculatorService calculatorService;

    private int a, b;

    @BeforeEach
    void setValues(){
        a = new Random().nextInt();
        b = new Random().nextInt();
    }

    @Test
    void plusMethod(){
        assertEquals(
                a + b, calculatorService.plus(a, b)
        );
    }

    @Test
    void minusMethod(){
        assertEquals(
                a - b, calculatorService.minus(a, b)
        );
    }

    @Test
    void multiplyMethod(){
        assertEquals(
                a * b, calculatorService.multiply(a, b)
        );
    }

    @Test
    void divideMethod(){
        assertEquals(
                a / b, calculatorService.divide(a, b)
        );
    }

    @Test
    void divisionByZero(){
        assertThrows(
                ArithmeticException.class, () -> calculatorService.divide(a, 0)
        );
    }
}