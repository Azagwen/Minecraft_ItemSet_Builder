package com.azagwen;

import javax.swing.*;
import java.awt.*;

public interface ISBConstants {
    JTextField console = new JTextField();
    JTextField matTextBox = new HintTextField("Material (ex: iron, diamond...)");
    JTextField namespaceTextBox = new HintTextField("Mod namespace");
    JTextField typeTextBox = new HintTextField("Ingredient type (ex: ingot)");
    JTextField toolTextBox = new HintTextField("Tools");
    JTextField armorTextBox = new HintTextField("Armor items");
    JTextField blockTextBox = new HintTextField("Blocks");
    JTextField oreItemTextBox = new HintTextField("Ore Items (ex: ingots)");

    ColoredToggleButton makeItemModels = new ColoredToggleButton("Create Item models");
    ColoredToggleButton makeBlockModels = new ColoredToggleButton("Create Block models");
    ColoredToggleButton makeRecipes = new ColoredToggleButton("Create Recipes");
    ColoredToggleButton makeLootTables = new ColoredToggleButton("Create Loot-Tables");

    JLabel texLabel = new JLabel("Texture folders: ");

    String[] itemType = {
            "_hoe",         //0
            "_shovel",      //1
            "_pickaxe",     //2
            "_axe",         //3
            "_sword",       //4

            "_ingot",       //5
            "_helmet",      //6
            "_chestplate",  //7
            "_leggings",    //8
            "_boots",       //9

            "_block",       //10
            "_ore"          //11
    };

    Color ISB_red = new Color(192, 64, 64);
    Color ISB_yellow = new Color(192, 192, 64);
    Color ISB_green = new Color(64, 192, 64);
}
