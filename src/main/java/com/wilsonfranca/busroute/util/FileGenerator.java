package com.wilsonfranca.busroute.util;

import java.io.*;

/**
 * Utility class to generate a big file with 100.000 routes, each one with 1000 different stations
 */
public class FileGenerator {

    public static final int MAX_ROUTES = 100000;
    public static final int MAX_STATIONS = 1000;

    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {

        File file = new File("src/test/resources/routes.txt");

        PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8")), false);
        int routes = MAX_ROUTES;
        int counter = 0;
        while (true) {
            String separator = "";
            if (counter == 0) {
                writer.print(separator);
                writer.print(routes);
            } else {
                for (int i = 0; i < MAX_STATIONS; i++) {
                    int number;
                    if (i == 0) {
                        number = counter;
                    } else {
                        number = 1000 * counter + i;
                    }
                    writer.print(separator);
                    writer.print(number);
                    separator = " ";
                }
            }
            writer.println();
            if (++counter == routes + 1) {
                System.out.printf("Size: %.3f GB%n", file.length() / 1e9);
                writer.close();
                break;
            }
        }

    }
}

