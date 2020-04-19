package org.example.springboot.r6api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OperatorIdx {
    private static final Map<String, String> indexToOperator;
    static {
        indexToOperator = new HashMap<>();
        Gson gson = new Gson();
        try {
            String prefix = "operatorpvp_";
            JsonObject object = gson.fromJson(new FileReader("operator.json"), JsonObject.class);

            Set<Map.Entry<String, JsonElement>> entrySet = object.entrySet();
            for(Map.Entry<String, JsonElement> entry : entrySet) {
                String operatorName = entry.getKey();
                JsonObject value = entry.getValue().getAsJsonObject();
                String index = value.get("index").getAsString();
                value = value.get("uniqueStatistic").getAsJsonObject().get("pvp").getAsJsonObject();

                // ex : operatorpvp_caveira_interrogations:1:8
                // return caveira_interrogations
                String statisticId = value.get("statisticId").getAsString().split(":")[0].substring(prefix.length());

                indexToOperator.put(index, operatorName);
                indexToOperator.put(operatorName, statisticId);
                indexToOperator.put(statisticId, index);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getIndexToOperator() {
        return indexToOperator;
    }
}
