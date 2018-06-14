package com.jcairns;

import org.junit.Assert;
import org.junit.Test;

public class FixdTetTest
{
    @Test
    public void testProcess()
    {
        final FixdTet ft = new FixdTet();
        Assert.assertEquals(2, ft.processLine("Q1"));
        Assert.assertEquals(4, ft.processLine("Q1, Q2"));
        Assert.assertEquals(3, ft.processLine("L0"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidRow() {
        final FixdTet ft = new FixdTet();
        ft.processLine("L9");
        Assert.fail();
    }
}
