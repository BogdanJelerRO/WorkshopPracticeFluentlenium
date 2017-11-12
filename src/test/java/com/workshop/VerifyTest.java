package com.workshop;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

public class VerifyTest {
    @Test
    public void verifyTestSetup() {
        assertTrue(true);
        assertFalse(false);
    }
}
