package ru.otus.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerMockTests {

    @Autowired
    private MockMvc mock;

    private int a, b;

    @BeforeEach
    void setValues(){
        a = new Random().nextInt();
        b = new Random().nextInt();
    }

    @Test
    void testPlus() throws Exception {
        Integer expectedResult = a + b;
        this.checkCalculatorResult("plus", a, b, expectedResult);
    }

    @Test
    void testMinus() throws Exception {
        Integer expectedResult = a - b;
        this.checkCalculatorResult("minus", a, b, expectedResult);
    }

    @Test
    void testMultiply() throws Exception {
        Integer expectedResult = a * b;
        this.checkCalculatorResult("multiply", a, b, expectedResult);
    }

    @Test
    void testDivide() throws Exception {
        Integer expectedResult = a / b;
        this.checkCalculatorResult("divide", a, b, expectedResult);
    }

    private void checkCalculatorResult(String operationName, int a, int b, Integer expectedResult) throws Exception {
        mock.perform(get(String.format("/calc?operation=%s&a=%s&b=%s", operationName, a, b)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult.toString()));
    }
}