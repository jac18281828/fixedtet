package com.jcairns;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jcairns on 7/22/16.
 */
public class ShapeTest {
    @Test
    public void dimenstionTest() {
        Assert.assertEquals(2, Shape.L.width);
        Assert.assertEquals(3, Shape.L.height);
        Assert.assertEquals(2, Shape.J.width);
        Assert.assertEquals(3, Shape.J.height);
        Assert.assertEquals(2, Shape.Q.width);
        Assert.assertEquals(2, Shape.Q.height);
        Assert.assertEquals(3, Shape.Z.width);
        Assert.assertEquals(2, Shape.Z.height);
        Assert.assertEquals(3, Shape.S.width);
        Assert.assertEquals(2, Shape.S.height);
        Assert.assertEquals(1, Shape.I.height);
        Assert.assertEquals(4, Shape.I.width);
    }
}
