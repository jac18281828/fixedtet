package com.jcairns;

/**
 * Shape definition
 *
 * Created by jcairns on 7/22/16.
 */
enum Shape {
    Q(new boolean[][]{{true, true}, {true, true}}),
    Z(new boolean[][]{{false, true, true}, {true, true, false}}),
    S(new boolean[][]{{true, true, false}, {false, true, true}}),
    T(new boolean[][]{{false, true, false}, {true, true, true}}),
    I(new boolean[][]{{true, true, true, true}}),
    L(new boolean[][]{{true, true}, {true, false}, {true, false}}),
    J(new boolean[][]{{true, true}, {false, true}, {false, true}}),
    ;


    public final BitMap bitmap;
    public final int width;
    public final int height;

    Shape(boolean[][] shape) {
        width = shape[0].length;
        height = shape.length;
        final FixedMap map = new FixedMap(width, height);
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                if(shape[j][i]) {
                    map.set(i, j);
                }
            }
        }
        bitmap = map;
    }
}
