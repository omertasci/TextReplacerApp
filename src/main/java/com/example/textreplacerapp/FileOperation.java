package com.example.textreplacerapp;

import com.example.textreplacerapp.exception.InvalidInputException;
import com.example.textreplacerapp.exception.OldTextCountException;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class FileOperation {

    private static int count = 0;
    static void modifyFile(File fileToBeModified, String oldString, String newString) {
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            String newContent = oldContent.replaceAll(oldString, newString);
            count += StringUtils.countMatches(oldContent, oldString);

            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int iterateFiles(String directoryName, String oldText, String newText) {
        File dir = new File(directoryName);

        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                modifyFile(child, oldText, newText);
            }
        }
        return count;
    }
}
