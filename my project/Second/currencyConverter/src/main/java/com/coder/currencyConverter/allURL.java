package com.coder.currencyConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class allURL {

    private static final String fileName = "allUrlFile.txt";

    public static String[] readAllUrl() {
        String[] stringMassUrl = new String[0];
        try {
            FileReader fReader = new FileReader(fileName);
            ArrayList<String> list = new ArrayList<>();
            BufferedReader reader = new BufferedReader(fReader);
            String lineUrl = reader.readLine();
            while (lineUrl != null) {
                list.add(lineUrl);
                lineUrl = reader.readLine();

            }
            stringMassUrl = list.toArray(new String[0]);
            fReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringMassUrl;
    }

    public static void addNewUrl(String url) {
        try {
            FileWriter writeNewUrl = new FileWriter(fileName, true);
            writeNewUrl.append('\n');
            writeNewUrl.append(url);
            writeNewUrl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
