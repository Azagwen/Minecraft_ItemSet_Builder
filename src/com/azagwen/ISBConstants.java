package com.azagwen;

import javax.swing.*;
import java.awt.*;

public interface ISBConstants {
    JTextField console = new JTextField();
    JTextField matTextBox = new JTextField();
    JTextField toolFolderTextBox = new JTextField();
    JTextField armorFolderTextBox = new JTextField();
    JTextField namespaceTextBox = new JTextField();
    JLabel matLabel = new JLabel("Material");
    JLabel toolLabel = new JLabel("Tools texture folder name");
    JLabel armorLabel = new JLabel("Armor items texture folder name");
    JLabel namespaceLabel = new JLabel("Mod namespace");

    String namespace = (namespaceTextBox.getText().equals("") ? "minecraft" : namespaceTextBox.getText()) + ":";
    String toolsFolder = (toolFolderTextBox.getText().equals("") ? "item" : toolFolderTextBox.getText()) + "/";
    String armorFolder = (armorFolderTextBox.getText().equals("") ? "item" : armorFolderTextBox.getText()) + "/";

    String[] itemType = {
            "_hoe",
            "_shovel",
            "_pickaxe",
            "_axe",
            "_sword",
            "_helmet",
            "_chestplate",
            "_leggings",
            "_boots"
    };

    Color ISB_red = new Color(192, 64, 64);
    Color ISB_yellow = new Color(192, 192, 64);
    Color ISB_green = new Color(64, 192, 64);
}
