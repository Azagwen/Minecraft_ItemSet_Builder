package com.azagwen.builders;

import com.azagwen.ISBConstants;
import com.azagwen.ISBFileWriter;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BlockstateBuilder implements ISBConstants {

    public BlockstateBuilder(String path) {
        JSONObject mainMap = new JSONObject();
        JSONObject modelMap = new JSONObject();
        JSONObject variantMap = new JSONObject();
        String material = matTextBox.getText().toLowerCase();

        for (int i = 0; i < itemType.length; i++) {
            if (i > 8) {
                modelMap.put("model", namespace + "block" + material + itemType[i]);
                variantMap.put("", modelMap);
                mainMap.put("variants", variantMap);

                File directory = new File(path + "\\blockstates");
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
}
