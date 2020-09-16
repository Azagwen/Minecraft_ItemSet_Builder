package com.azagwen.builders;

import com.azagwen.ISBConstants;
import com.azagwen.ISBFileWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RecipeDataBuilder implements ISBConstants {

    public RecipeDataBuilder(String path) {
        JSONObject mainMap = new JSONObject();
        JSONObject keyMap = new JSONObject();
        JSONObject oreMap = new JSONObject();
        JSONObject stickMap = new JSONObject();
        JSONObject resultMap = new JSONObject();
        String material = matTextBox.getText().toLowerCase();
        String type = "_" + typeTextBox.getText();

        oreMap.put("M", namespace + material + (isFieldEmpty(typeTextBox) ? "" : type));
        stickMap.put("S", "minecraft:stick");

        makeObjOrdered(mainMap);
        makeObjOrdered(keyMap);

        for (int i = 0; i < itemType.length; i++) {
            JSONArray currentPattern = setCurrentPattern(i);

            if (i < 5) {
                keyMap.put("M", oreMap);
                keyMap.put("S", stickMap);
            } else {
                keyMap.remove("S");
            }

            resultMap.put("Item", namespace + material + itemType[i]);

            mainMap.put("type", "minecraft:crafting_shaped");
            mainMap.put("pattern", currentPattern);
            mainMap.put("key", keyMap);
            mainMap.put("result", resultMap);

            File directory = new File(path + "\\recipes");
            if (!directory.exists()) {
                try {
                    Files.createDirectories(Paths.get(directory.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (i != 10)
            new ISBFileWriter(directory.getAbsolutePath(), mainMap, itemType[i]);
        }
    }

    private JSONArray setCurrentPattern(int iteration) {
        JSONArray hoe = new JSONArray();
        JSONArray shovel = new JSONArray();
        JSONArray pickaxe = new JSONArray();
        JSONArray axe = new JSONArray();
        JSONArray sword = new JSONArray();
        JSONArray helmet = new JSONArray();
        JSONArray chestplate = new JSONArray();
        JSONArray leggings = new JSONArray();
        JSONArray boots = new JSONArray();
        JSONArray block = new JSONArray();

        hoe.put("MM");
        hoe.put(" S");
        hoe.put(" S");
        shovel.put("M");
        shovel.put("S");
        shovel.put("S");
        pickaxe.put("MMM");
        pickaxe.put(" S ");
        pickaxe.put(" S ");
        axe.put("MM");
        axe.put("MS");
        axe.put(" S");
        sword.put("M");
        sword.put("S");
        sword.put("S");

        helmet.put("MMM");
        helmet.put("M M");
        chestplate.put("M M");
        chestplate.put("MMM");
        chestplate.put("MMM");
        leggings.put("MMM");
        leggings.put("M M");
        leggings.put("M M");
        boots.put("M M");
        boots.put("M M");

        block.put("MMM");
        block.put("MMM");
        block.put("MMM");

        switch (iteration) {
            case 0:
                return hoe;
            case 1:
                return shovel;
            case 2:
                return pickaxe;
            case 3:
                return axe;
            case 4:
                return sword;
            case 5:
                return helmet;
            case 6:
                return chestplate;
            case 7:
                return leggings;
            case 8:
                return boots;
            case 9:
                return block;
            default:
                System.out.println("empty array created");
                return new JSONArray();
        }
    }

    private void makeObjOrdered(JSONObject object) {
        try {
            Field changeMap = object.getClass().getDeclaredField("map");
            changeMap.setAccessible(true);
            changeMap.set(object, new LinkedHashMap<>());
            changeMap.setAccessible(false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isFieldEmpty(JTextField field) {
        return field.getText().equals("");
    }
}