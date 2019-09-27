package com.sing.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("CommonUtils Testing")
class CommonUtilsTest {

    @DisplayName("Should be a valid list when data is supplied")
    @Test
    void testTransformation() throws IOException {
        assertSame(ArrayList.class, CommonUtils.getTransactionList("src/test/resources/input.csv").getClass());
    }

    @DisplayName("Should convert String to Transaction")
    @Test
    void testCreateTransaction() {
        String input = "TX10001,ACC112233,ACC332211,23/05/2019 18:18:18,1.23,PAYMENT";
        assertEquals(123l, new CommonUtils().createTransactionRecordFromInput(input).getAmountInCents());
    }

}