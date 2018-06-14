package com.jcairns;

/**
 * abstraction of a block map for testing collisions in the game
 *
 * Created by jcairns on 7/22/16.
 */
interface BitMap {
    /**
     * @return int - the width of the bitmap
     */
    int getWidth();

    /**
     * @return int - the height of the bitmap
     */
    int getHeight();

    /**
     * @param x - x coordinate
     * @param y - y coordinate
     * @return boolean - True if coordinate is set, false otherwise
     */
    boolean get(int x, int y);
}
