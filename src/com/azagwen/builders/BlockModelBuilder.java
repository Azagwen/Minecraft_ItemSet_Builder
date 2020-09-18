package com.azagwen.builders;

import com.azagwen.ISBConstants;
import com.azagwen.ISBFileWriter;
import org.json.JSONObject;

import java.io.File;

public class BlockModelBuilder implements ISBConstants {

    public BlockModelBuilder(String path, String namespace, String blocksFolder) {
        JSONObject mainMap = new JSONObject();
        JSONObject textureMap = new JSONObject();
        String material = matTextBox.getText().toLowerCase();

        for (int i = 0; i < itemType.length; i++) {
            String modelParrent = "minecraft:cube_all";

            if (i > 9) {
                textureMap.put("all", namespace + blocksFolder + material + itemType[i]);
                mainMap.put("parent", modelParrent);
                mainMap.put("textures", textureMap);

                File directory = new File(path + "\\models\\block\\");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                new ISBFileWriter(directory.getAbsolutePath(), mainMap, itemType[i]);
            }
        }
    }
}
