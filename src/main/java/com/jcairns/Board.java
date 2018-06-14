package com.jcairns;

/**
 * Game Board
 *
 * Created by jcairns on 7/22/16.
 */
class Board {

    private final FixedMap boardMap;

    Board(final int width, final int height) {
        boardMap = new FixedMap(width, height);
    }


    /**
     * Drop s at row x
     *
     * @param s - shape to drop
     * @param colX - col to drop at
     * @return boolean - True if piece was dropped, false if game is over
     *
     * @throws ArrayIndexOutOfBoundsException - if row is invalid
     */
    boolean drop(final Shape s, final int colX) {
        final int width = boardMap.getWidth();
        if((colX >= 0) && (colX < width) && (colX+s.width) <= width) {
            final int height = boardMap.getHeight();
            int y=height-s.height;
            // can a piece be added to the board?
            if(boardMap.collision(colX, y, s.bitmap)) {
                return false; // Game Over Man!
            } else {
                y--;
            }

            for(; y>=0; y--) {
                // how far does it fall
                if(boardMap.collision(colX, y, s.bitmap)) {
                    // collide at y so place piece at the next line up
                    boardMap.set(colX, y+1, s.bitmap);
                    detectTetris(y+1, y+1+s.height);
                    break;
                }
            }

            // catch the case where it falls to the floor
            if(y<0) {
                // set it on the bottom
                boardMap.set(colX, 0, s.bitmap);
                detectTetris(0, s.height);
            }

        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

        return true;
    }

    /**
     * calculate the height of the current stack
     *
     * @return int - the height of the current stack in blocks
     */
    int height() {
        final int width = boardMap.getWidth();
        final int height = boardMap.getHeight();
        for(int y=0; y<height; y++) {
            int x=0;
            scan:
            for(;x<width; x++) {
                if(boardMap.get(x, y)) {
                    break scan;
                }
            }
            // this row is empty
            if(x == width) {
                return y;
            }
        }
        return 0;
    }

    /**
     * clear everything from the game board
     */
    void clear() {
        boardMap.clear();
    }

    /**
     * print out a graphical representation of the
     * current game board
     */
    void drawBoard() {
        final int height = Math.min(height(), boardMap.getHeight()-1);
        final int width = boardMap.getWidth();
        for(int j=height; j>=0; j--) {
            System.out.print('[');
            for(int i=0; i<width; i++) {
                if(boardMap.get(i, j)) {
                    System.out.print('*');
                } else {
                    System.out.print('_');
                }
            }
            System.out.println(']');
        }
        System.out.print('[');
        for(int i=0; i<width; i++) {
            System.out.print('=');
        }
        System.out.println(']');

    }

    /*
     * Detect when blocks saturate a row - "TETRIS"
     */
    private void detectTetris(final int y1, final int y2) {
        final int width = boardMap.getWidth();
        for(int y=y1; y<y2;) {
            int x = 0;
            scan:
            for(; x<width; x++) {
                if(!boardMap.get(x, y)) {
                    break scan;
                }
            }
            if(x == width) {
                // TETRIS!   WHOOP WHOOP
                final int top = height()-1;
                for(int j=y; j<top; j++) {
                    for(x=0; x<width; x++) {
                        boardMap.set(x, j, boardMap.get(x, j+1));
                    }
                }
                for(x=0; x<width; x++) {
                    boardMap.clear(x, top);
                }
            } else {
                y++;
            }
        }
    }
}
