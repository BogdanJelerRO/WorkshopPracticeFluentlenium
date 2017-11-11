package com.workshop;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.is;

public class VerifyTest {
    @Test
    public void verifyTestSetup() {
        assertTrue(true);
        assertFalse(false);
    }
}
