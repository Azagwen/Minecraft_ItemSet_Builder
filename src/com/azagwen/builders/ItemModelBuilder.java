package com.azagwen.builders;


import com.azagwen.ISBConstants;
import com.azagwen.ISBFileWriter;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ItemModelBuilder implements ISBConstants {

    public ItemModelBuilder(String path) {
        JSONObject mainMap = new JSONObject();
        JSONObject textureMap = new JSONObject();
        String material = matTextBox.getText().toLowerCase();

        for (int i = 0; i < itemType.length; i++) {
            String currentFolder = "";
            String modelParrent = "";

            if (i < 5) {
                currentFolder = toolsFolder;
                modelParrent = "minecraft:item/handheld";
            } else if (i < 8) {
                currentFolder = armorFolder;
                modelParrent = "minecraft:item/generated";
            } else {
                currentFolder = blocksFolder;
                modelParrent = namespace + "block/" + material + itemType[i];
            }

            textureMap.put("layer0", namespace + currentFolder + material + itemType[i]);
            mainMap.put("parent", modelParrent);
            if (i < 9)
                mainMap.put("textures", textureMap);
            else
                mainMap.remove("textures");

            File directory = new File(path + "\\models\\item");
            if (!directory.exists()) {
                try {
                    Files.createDirectories(Paths.get(directory.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            new ISBFileWriter(directory.getAbsolutePath(), mainMap, itemType[i]);
        }
    }
}