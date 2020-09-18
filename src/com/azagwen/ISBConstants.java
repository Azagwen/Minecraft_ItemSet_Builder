package com.azagwen;

import javax.swing.*;
import java.awt.*;

public interface ISBConstants {
    JTextField console = new JTextField();
    JTextField matTextBox = new HintTextField("Material");
    JTextField namespaceTextBox = new HintTextField("Mod namespace");
    JTextField typeTextBox = new HintTextField("Ingredient type (ex: ingot)");
    JTextField toolTextBox = new HintTextField("Tools");
    JTextField armorTextBox = new HintTextField("Armor items");
    JTextField blockTextBox = new HintTextField("Blocks");
    JLabel texLabel = new JLabel("Texture folders: ");

    String namespace = (namespaceTextBox.getText().equals("") ? "minecraft" : namespaceTextBox.getText().toLowerCase()) + ":";
    String toolsFolder = (toolTextBox.getText().equals("") ? "item" : toolTextBox.getText().toLowerCase()) + "/";
    String armorFolder = (armorTextBox.getText().equals("") ? "item" : armorTextBox.getText().toLowerCase()) + "/";
    String blocksFolder = (blockTextBox.getText().equals("") ? "block" : blockTextBox.getText().toLowerCase()) + "/";

    String[] itemType = {
            "_hoe",
            "_shovel",
            "_pickaxe",
            "_axe",
            "_sword",

            "_ingot",
            "_helmet",
            "_chestplate",
            "_leggings",
            "_boots",

            "_block",
            "_ore"
    };

    Color ISB_red = new Color(192, 64, 64);
    Color ISB_yellow = new Color(192, 192, 64);
    Color ISB_green = new Color(64, 192, 64);
}
