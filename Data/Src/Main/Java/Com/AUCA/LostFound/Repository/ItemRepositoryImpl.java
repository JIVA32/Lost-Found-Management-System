package Com.AUCA.LostFound.Repository;

import Com.AUCA.LostFound.Model.Item;

import java.io.*;
import java.util.*;

/**
 * File-based implementation of ItemRepository using JSON serialization.
 */
public class ItemRepositoryImpl implements ItemRepository {
    private static final String FILE_PATH = "Data/items.json";

    @Override
    public void save(Item item) {
        List<Item> items = findAll();
        items.add(item);
        writeToFile(items);
    }

    @Override
    public List<Item> findAll() {
        return readFromFile();
    }

    private void writeToFile(List<Item> items) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("[");
            for (int i = 0; i < items.size(); i++) {
                writer.println(items.get(i).toJson());
                if (i < items.size() - 1) {
                    writer.println(",");
                }
            }
            writer.println("]");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    private List<Item> readFromFile() {
        List<Item> items = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return items;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line.trim());
            }
            String jsonStr = json.toString();
            if (jsonStr.isEmpty() || jsonStr.equals("[]")) {
                return items;
            }
            // Simple parsing: remove [ and ], split by },{
            jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
            if (jsonStr.isEmpty()) {
                return items;
            }
            String[] itemJsons = jsonStr.split("\\},\\{");
            for (String itemJson : itemJsons) {
                if (!itemJson.startsWith("{")) {
                    itemJson = "{" + itemJson;
                }
                if (!itemJson.endsWith("}")) {
                    itemJson = itemJson + "}";
                }
                items.add(Item.fromJson(itemJson));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file", e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        List<Item> items = findAll();
        return items.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deleteById(String id) {
        List<Item> items = findAll();
        items.removeIf(item -> item.getId().equals(id));
        writeToFile(items);
    }
}