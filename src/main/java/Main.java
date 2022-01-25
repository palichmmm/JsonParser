import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;

public class Main {
    public static String fileJSON = "data.json";
    public static void main(String[] args) {
        String json = readString(fileJSON);
        List<Employee> list = jsonToList(json);
        if (list != null) {
            list.forEach(System.out::println);
        } else {
            System.out.println("Ошибка преобразования файла в объект!!!");
        }
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> listEmployee = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(json);
            JSONArray jsonArray = (JSONArray) object;
            for (Object obj : jsonArray) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Employee employee = gson.fromJson(String.valueOf(obj), Employee.class);
                listEmployee.add(employee);
            }
            return listEmployee;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }
    private static String readString(String file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }
}
