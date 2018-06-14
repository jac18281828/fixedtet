package com.jcairns;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jcairns on 7/22/16.
 */
public class BoardTest {

    @Test
    public void testLJ() {
        final Board b = new Board(10, 10);
        b.drop(Shape.L, 0);
        b.drop(Shape.J, 0);
        b.drawBoard();
        Assert.assertEquals(6, b.height());

    }

    @Test
    public void simpleDrop() {
        final Board b = new Board(10, 100);

        for(int i=0; i<10- Shape.L.width; i++) {
            b.drop(Shape.L, i);
            Assert.assertEquals(3, b.height());
            b.clear();
        }

        b.drop(Shape.L, 3);
        b.drop(Shape.Q, 3);
        Assert.assertEquals(5, b.height());
        b.clear();

        b.drop(Shape.L, 3);
        b.drop(Shape.Q, 4);
        Assert.assertEquals(3, b.height());
        b.clear();

        b.drop(Shape.Q, 1);
        Assert.assertEquals(2, b.height());
        b.clear();

        Assert.assertEquals(0, b.height());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void invalidDrop() {
        final Board b = new Board(10, 100);
        b.drop(Shape.Q, 9);
    }

    @Test
    public void testTZI() {
        final Board b = new Board(10, 10);
        b.drop(Shape.T, 0);
        b.drop(Shape.Z, 2);
        b.drop(Shape.I, 4);
        b.drawBoard();
        Assert.assertEquals(3, b.height());
    }

    @Test
    public void testNeighbors() {
        final Board b = new Board(10, 10);
        for(int i=0; i<10; i+=2) {
            b.drop(Shape.Q, i);
        }
        Assert.assertEquals(0, b.height());
        b.drop(Shape.I, 6);
        b.drawBoard();
        Assert.assertEquals(1, b.height());
    }

    @Test
    public void testGameOver() {
        final Board b = new Board(10, 10);
        for(int i=0; i<10; i+=2) {
            b.drop(Shape.Q, 8);
        }
        Assert.assertFalse(b.drop(Shape.Q, 8));
    }

    @Test
    public void testI0I4Q8() {
        final Board b = new Board(10, 10);
        b.drop(Shape.I, 0);
        b.drop(Shape.I, 4);
        b.drop(Shape.Q, 8);
        b.drawBoard();
        Assert.assertEquals(1, b.height());
    }

    @Test
    public void testT1Z3I4() {
        final Board b = new Board(10, 10);
        b.drop(Shape.T, 1);
        b.drop(Shape.Z, 3);
        b.drop(Shape.I, 4);
        b.drawBoard();
        Assert.assertEquals(4, b.height());
    }

    @Test
    public void testQIQQ() {
        final Board b = new Board(10, 10);
        b.drop(Shape.Q, 0);
        b.drop(Shape.I, 2);
        b.drop(Shape.I, 6);
        b.drop(Shape.I, 0);
        b.drop(Shape.I, 6);
        b.drop(Shape.I, 6);
        b.drop(Shape.Q, 2);
        b.drop(Shape.Q, 4);
        b.drawBoard();
        Assert.assertEquals(3, b.height());
    }

    @Test
    public void testLZZZZ() {
        final Board b = new Board(10, 10);
        b.drop(Shape.L, 0);
        b.drop(Shape.Z, 1);
        b.drop(Shape.Z, 3);
        b.drop(Shape.Z, 5);
        b.drop(Shape.Z, 7);
        b.drawBoard();
        Assert.assertEquals(2, b.height());
    }
}
