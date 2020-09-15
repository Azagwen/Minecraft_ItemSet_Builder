package com.azagwen;

import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ISBFileWriter implements ISBConstants {

    public ISBFileWriter(String path, JSONObject content, int iteration, String[] type) {
        String material = matTextBox.getText();

        try {
            File newFile = new File(path + "\\" + material + type[iteration] + ".json");
            if (!newFile.exists()) {
                FileWriter myWriter = new FileWriter(newFile.getAbsolutePath());
                myWriter.write(content.toString(4));
                myWriter.close();

                console.setForeground(ISB_green);
                console.setText("Done.");
                if (isFieldEmpty(toolFolderTextBox) || isFieldEmpty(armorFolderTextBox) || isFieldEmpty(namespaceTextBox)) {
                    GUI.delayedConsoleMSG("Empty fields defaulted to Vanilla values.", 2);
                    GUI.delayedConsoleMSG("", 5);
                } else {
                    GUI.delayedConsoleMSG("", 2);
                }
            } else {
                FileWriter myWriter = new FileWriter(newFile.getAbsolutePath());
                myWriter.write(content.toString(4));
                myWriter.close();

                console.setForeground(ISB_yellow);
                console.setText("Some files already existed, They have been overwritten.");
                if (isFieldEmpty(toolFolderTextBox) || isFieldEmpty(armorFolderTextBox) || isFieldEmpty(namespaceTextBox)) {
                    GUI.delayedConsoleMSG("Empty fields defaulted to Vanilla values.", 3);
                    GUI.delayedConsoleMSG("", 6);
                } else {
                    GUI.delayedConsoleMSG("", 2);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static boolean isFieldEmpty(JTextField field) {
        return field.getText().equals("");
    }
}