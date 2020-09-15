package com.azagwen;


import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ItemModelMaker implements ISBConstants {
    private static JSONObject mainMap = new JSONObject();

    public ItemModelMaker(String path) {
        JSONObject textureMap = new JSONObject();
        String material = matTextBox.getText();

        for (int i = 0; i < itemType.length; i++) {
            String currentFolder = "";
            String modelParrent = "";

            if (i < 5) {
                currentFolder = toolsFolder;
                modelParrent = "handheld";
            } else {
                currentFolder = armorFolder;
                modelParrent = "generated";
            }

            textureMap.put("layer0", namespace + currentFolder + material.toLowerCase() + itemType[i]);
            mainMap.put("parent", "minecraft:item/" + modelParrent);
            mainMap.put("textures", textureMap);

            File directory = new File(path + "\\models\\item");
            if (!directory.exists()) {
                try {
                    Files.createDirectories(Paths.get(directory.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            new ISBFileWriter(directory.getAbsolutePath(), mainMap, i, itemType);
        }
    }
}