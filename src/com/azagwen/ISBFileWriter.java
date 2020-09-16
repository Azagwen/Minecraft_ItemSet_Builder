package com.azagwen;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ISBFileWriter implements ISBConstants {

    public ISBFileWriter(String path, JSONObject content, String type) {
        String material = matTextBox.getText().toLowerCase();

        try {
            File newFile = new File(path + "\\" + material + type + ".json");
            if (!newFile.exists()) {
                FileWriter myWriter = new FileWriter(newFile.getAbsolutePath());
                myWriter.write(content.toString(4));
                myWriter.close();

                printDoneMessage(ISB_green, "Done.", 2, 5);
            } else {
                FileWriter myWriter = new FileWriter(newFile.getAbsolutePath());
                myWriter.write(content.toString(4));
                myWriter.close();

                printDoneMessage(ISB_yellow, "Some files already existed, They have been overwritten.", 3, 6);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void printDoneMessage(Color color, String msg, int defMsgDelay, int defMsgClearDelay) {
        console.setForeground(color);
        console.setText(msg);
        if (isFieldEmpty(toolTextBox) || isFieldEmpty(armorTextBox) || isFieldEmpty(namespaceTextBox) || isFieldEmpty(typeTextBox)) {
            GUI.delayedConsoleMSG("Empty fields defaulted to Vanilla values.", defMsgDelay);
            GUI.delayedConsoleMSG("", defMsgClearDelay);
        } else {
            GUI.delayedConsoleMSG("", 2);
        }
    }

    private static boolean isFieldEmpty(JTextField field) {
        return field.getText().equals("");
    }
}