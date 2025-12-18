package Com.AUCA.LostFound.Model;

import java.util.HashMap;
import java.util.Map;

public class Item {
    private String id;
    private String name;
    private String description;
    private String location;
    private String status;

    public Item() {
    }

    public Item(String id, String name, String description, String location, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String toJson() {
        return "{\"id\":\"" + escapeJson(id) + "\",\"name\":\"" + escapeJson(name) + "\",\"description\":\"" + escapeJson(description) + "\",\"location\":\"" + escapeJson(location) + "\",\"status\":\"" + escapeJson(status) + "\"}";
    }

    public static Item fromJson(String json) {
        // Simple parsing, assuming well-formed JSON
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
        }
        Map<String, String> map = parseJsonObject(json);
        return new Item(
                unescapeJson(map.get("id")),
                unescapeJson(map.get("name")),
                unescapeJson(map.get("description")),
                unescapeJson(map.get("location")),
                unescapeJson(map.get("status"))
        );
    }

    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }

    private static String unescapeJson(String s) {
        if (s == null) return null;
        return s.replace("\\\"", "\"").replace("\\\\", "\\").replace("\\n", "\n").replace("\\r", "\r").replace("\\t", "\t");
    }

    private static Map<String, String> parseJsonObject(String json) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = json.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replace("\"", "");
                String value = keyValue[1].trim();
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }
                map.put(key, value);
            }
        }
        return map;
    }
}
