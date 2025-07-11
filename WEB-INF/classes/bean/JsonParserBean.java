package bean;

import java.util.HashMap;
import java.util.Map;

public class JsonParserBean {
    public Map<String, String> parseJson(String jsonString){
        // 最初と最後の中括弧を取り除く
        jsonString = jsonString.substring(1, jsonString.length() - 1);

        // カンマで分割してキーと値のペアに分割
        String[] keyValuePairs = jsonString.split(",");

        // キーと値を格納するMap
        Map<String, String> result = new HashMap<>();

        for (String pair : keyValuePairs) {
            // コロンでキーと値を分割
            String[] entry = pair.split(":");

            // ダブルクォートを取り除き、トリムしてキーと値を格納
            String key = entry[0].replaceAll("\"", "").trim();
            String value = entry[1].replaceAll("\"", "").trim();

            // Mapに追加
            result.put(key, value);
        }

        return result;
    }
}