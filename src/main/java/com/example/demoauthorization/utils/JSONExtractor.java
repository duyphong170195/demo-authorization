package com.example.demoauthorization.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONExtractor {
    static List<Long> areaIds = new ArrayList<>();
    public static void extractField(JSONObject jsonObject, String field, String arrayField) {
        if (jsonObject.has(field)) {
            areaIds.add( Long.valueOf(jsonObject.get(field).toString())) ;
        }
        if (jsonObject.has(arrayField)) {
            JSONArray areaIdsJson = jsonObject.getJSONArray(arrayField);
            for(int i = 0; i< areaIdsJson.length(); i++ ) {
                areaIds.add(Long.valueOf(areaIdsJson.get(i).toString()));
            }
        }

        for (String key : jsonObject.keySet()) {
            if (jsonObject.get(key) instanceof JSONObject) {
                extractField(jsonObject.getJSONObject(key), field, arrayField);
            }
        }
    }

    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "    \"name\": \"John\",\n" +
                "    \"age\": 30,\n" +
                "    \"areaId\": 50,\n" +
                "    \"address\": {\n" +
                "        \"street\": \"123 Main St\",\n" +
                "        \"areaId\": 40,\n" +
                "        \"city\": \"New York\"\n" +
                "    },\n" +
                "    \"contacts\": {\n" +
                "        \"email\": \"john@example.com\",\n" +
                "        \"phone\": {\n" +
                "            \"areaIds\": [1, 2],\n" +
                "            \"home\": \"123-456-7890\",\n" +
                "            \"work\": \"987-654-3210\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        String jsonData = "{\"areaIds\": [1, 2], \"leasing\": null, \"blockIds\": [1, 2], \"secondary\": [true, false], \"residentRoles\": [\"ZA\", \"ZC\"], \"apartmentStatuses\": [\"ABC\", \"DEF\"]}";

        JSONObject jsonObject = new JSONObject(jsonStr);
        String fieldToExtract = "city";

        extractField(jsonObject, "areaId", "areaIds");
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(areaIds);
    }
}
