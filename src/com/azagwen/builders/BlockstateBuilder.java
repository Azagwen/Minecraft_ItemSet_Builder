package com.azagwen.builders;

import com.azagwen.ISBConstants;
import com.azagwen.ISBFileWriter;
import org.json.JSONObject;

import java.io.File;

public class BlockstateBuilder implements ISBConstants {

    public BlockstateBuilder(String path, String namespace) {
        JSONObject mainMap = new JSONObject();
        JSONObject modelMap = new JSONObject();
        JSONObject variantMap = new JSONObject();
        String material = matTextBox.getText().toLowerCase();

        for (int i = 0; i < itemType.length; i++) {
            if (i > 9) {
                modelMap.put("model", namespace + "block/" + material + itemType[i]);
                variantMap.put("", modelMap);
                mainMap.put("variants", variantMap);

                File directory = new File(path + "\\blockstates\\");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                new ISBFileWriter(directory.getAbsolutePath(), mainMap, itemType[i]);
            }
        }
    }
}
