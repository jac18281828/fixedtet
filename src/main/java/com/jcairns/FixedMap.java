package com.jcairns;

import java.util.BitSet;

/**
 * A 2d fixed bitmap
 *
 * Created by jcairns on 7/22/16.
 */
final class FixedMap implements BitMap {
    private final BitSet bitSet;
    private final int width;
    private final int height;

    /**
     * @param width - width of map
     * @param height - height of map
     */
    FixedMap(final int width, final int height) {
        this.width = width;
        this.height = height;
        final int nBits = width*height;
        bitSet = new BitSet(nBits);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * set the value at (x, y)
     * @param x - coordinate
     * @param y - coordinate
     */
    void set(final int x, final int y) {
        bitSet.set(project(x, y));
    }

    /**
     * Set the bit at (x,y) to flag
     * @param x - coordinate
     * @param y - coordinate
     * @param flag - boolean value
     */
    void set(final int x, final int y, final boolean flag) {
        bitSet.set(project(x, y), flag);
    }

    /**
     * clear the bit at (x, y)
     * @param x - coordinate
     * @param y - coordinate
     */
    void clear(final int x, final int y) {
        bitSet.clear(project(x, y));
    }

    /**
     * clear the bitmap
     */
    void clear() {
        bitSet.clear();
    }

    @Override
    public boolean get(final int x, final int y) {
        return bitSet.get(project(x, y));
    }

    /**
     * set the bits specified by map translated by (x,y)
     * @param x - x shift
     * @param y - y shift
     * @param map - bitmap
     */
    void set(final int x, final int y, final BitMap map) {
        for(int dy=0; dy<map.getHeight(); dy++) {
            for(int dx=0; dx<map.getWidth(); dx++) {
                if(map.get(dx, dy) && (x+dx < width) && (y+dy<height)) {
                    set(x+dx, y+dy);
                }
            }
        }
    }

    /**
     * detect a collision with map translated by (x,y)
     * @param x
     * @param y
     * @param map
     * @return
     */
    boolean collision(final int x, final int y, final BitMap map) {
        for(int dy = 0; dy < map.getHeight(); dy++) {
            for(int dx = 0; dx < map.getWidth(); dx++) {
                if(x + dx < width && y + dy < height) {
                    if(map.get(dx, dy) && get(x + dx, y + dy)) {
                        // collision here
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int project(final int x, final int y) {
        return y*width + x;
    }

}
