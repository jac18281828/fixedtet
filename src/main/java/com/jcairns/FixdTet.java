package com.jcairns;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * main class
 */
public class FixdTet
{
    public static final String INPUT = "input.txt";
    public static final String OUTPUT = "output.txt";
    public static final int BOARD_WIDTH = 10;

    int lineNumber = 0;

    public static void main( String[] args ) throws IOException {
        final List<String> output = new ArrayList();
        final FixdTet tet = new FixdTet();
        Files.lines(new File(INPUT).toPath())
                .map(s -> s.trim())
                .filter(s -> !s.isEmpty())
                .forEach(s -> output.add(Integer.toString(tet.processLine(s))));

        Files.write(new File(OUTPUT).toPath(), output);

    }

    // process each line of the file
    int processLine(final String s) {
        lineNumber++;

        final List<String> cmd = new ArrayList();

        Pattern.compile(",")
                .splitAsStream(s)
                .map(r -> r.trim())
                .forEach(cmd::add);

        final Board b = new Board(BOARD_WIDTH, (cmd.size()+1)*3);

        cmd.forEach(c->drop(b, c));

        final int height = b.height();
        System.out.println("Processing "+s+": "+height);
        b.drawBoard();
        return b.height();
    }

    // drop each shape describe by command on board
    private void drop(final Board b, final String cmd) {
        if(cmd.length() < 2) {
            System.err.println("Invalid input at line "+lineNumber);
        }
        final Shape s = Shape.valueOf(cmd.substring(0,1));
        final int   row = Character.getNumericValue(cmd.charAt(1));

        b.drop(s, row);
    }
}
