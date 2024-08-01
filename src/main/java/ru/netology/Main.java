package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILE_NAME_SRC = "src/main/resources/data.json";

    public static void main(String[] args) throws ParseException {

        String json = readString(FILE_NAME_SRC);
        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
    }


    public static String readString(String fileName) {
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader(fileName)) {
            Object result = parser.parse(fileReader);
            JSONArray jsonArray = (JSONArray) result;
            return jsonArray.toJSONString();
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Ошибка чтения: " + fileName, e);
        }
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        Gson gson = new GsonBuilder().create();
        JSONParser parser = new JSONParser();
        List<Employee> employees = new ArrayList<>();

        JSONArray jsonArray = (JSONArray) parser.parse(json);
        for (Object jsonObject : jsonArray) {
            Employee employee = gson.fromJson(jsonObject.toString(), Employee.class);
            employees.add(employee);
        }

        return employees;
    }
}