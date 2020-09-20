package com.azagwen.builders;


import com.azagwen.ISBConstants;
import com.azagwen.ISBFileWriter;
import org.json.JSONObject;

import java.io.*;

public class ItemModelBuilder implements ISBConstants {

    public ItemModelBuilder(String path, String namespace, String toolsFolder, String armorFolder, String blocksFolder, String oreItemsFolder) {
        JSONObject mainMap = new JSONObject();
        JSONObject textureMap = new JSONObject();
        String material = matTextBox.getText().toLowerCase();

        for (int i = 0; i < itemType.length; i++) {
            String currentFolder = "";
            String modelParrent = "";

            if (i <= 4) {
                currentFolder = toolsFolder;
                modelParrent = "minecraft:item/handheld";
            } else if (i == 5) {
                currentFolder = oreItemsFolder;
                modelParrent = "minecraft:item/generated";
            } else if (i <= 9) {
                currentFolder = armorFolder;
                modelParrent = "minecraft:item/generated";
            } else {
                currentFolder = blocksFolder;
                modelParrent = namespace + "block/" + material + itemType[i];
            }

            textureMap.put("layer0", namespace + currentFolder + material + itemType[i]);
            mainMap.put("parent", modelParrent);
            if (i <= 9)
                mainMap.put("textures", textureMap);
            else
                mainMap.remove("textures");

            File directory = new File(path + "\\models\\item\\");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            new ISBFileWriter(directory.getAbsolutePath(), mainMap, itemType[i]);
        }
    }
}