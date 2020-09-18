package com.azagwen.builders;

import com.azagwen.ISBConstants;
import com.azagwen.ISBFileWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class LootTableBuilder implements ISBConstants {

    public LootTableBuilder(String path, String namespace) {
        JSONObject mainMap = new JSONObject();
        JSONObject poolMap = new JSONObject();
        JSONObject entryMap = new JSONObject();
        JSONObject conditionMap = new JSONObject();
        String material = matTextBox.getText().toLowerCase();

        makeObjOrdered(mainMap);
        makeObjOrdered(poolMap);
        makeObjOrdered(entryMap);

        for (int i = 10; i < itemType.length; i++) {
            JSONArray poolArray = new JSONArray();
            JSONArray entriesArray = new JSONArray();
            JSONArray conditionsArray = new JSONArray();

            entryMap.put("type", "minecraft:item");
            entryMap.put("name", namespace + "block/" + material + itemType[i]);
            entriesArray.put(entryMap);

            conditionMap.put("condition", "minecraft:survives_explosion");
            conditionsArray.put(conditionMap);

            poolMap.put("rolls", 1);
            poolMap.put("entries", entriesArray);
            poolMap.put("conditions", conditionsArray);
            poolArray.put(poolMap);

            mainMap.put("type", "minecraft:block");
            mainMap.put("pools", poolArray);

            File directory = new File(path + "\\loot_tables\\blocks\\");
            if (!directory.exists()) {

                directory.mkdirs();
            }
            new ISBFileWriter(directory.getAbsolutePath(), mainMap, itemType[i]);
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
}
