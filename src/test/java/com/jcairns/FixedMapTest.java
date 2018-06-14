package com.jcairns;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jcairns on 7/22/16.
 */
public class FixedMapTest {

    @Test
    public void setGetTest() {
        final FixedMap fm = new FixedMap(10, 10);
        fm.set(0, 0);
        fm.set(1, 1);
        Assert.assertTrue(fm.get(0, 0));
        Assert.assertTrue(fm.get(1, 1));
        fm.clear(0, 0);
        Assert.assertFalse(fm.get(0, 0));
        Assert.assertTrue(fm.get(1, 1));
    }

    @Test
    public void collisionTest() {
        final FixedMap fm = new FixedMap(10, 10);
        fm.set(0, 0);
        fm.set(0, 1);
        fm.set(1, 0);
        fm.set(1, 1);

        final FixedMap fm2 = new FixedMap(4, 4);
        fm2.set(0, 1);
        fm2.set(1, 0);
        fm2.set(1, 1);

        Assert.assertEquals(true,  fm.collision(0, 0, fm2));
        Assert.assertEquals(true,  fm.collision(0, 0, fm2));

        Assert.assertEquals(true,  fm.collision(1, 0, fm2));
        Assert.assertEquals(true,  fm.collision(0, 1, fm2));
        Assert.assertEquals(false,  fm.collision(1, 1, fm2));
    }

}
