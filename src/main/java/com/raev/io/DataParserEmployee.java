package main.java.com.raev.io;

import com.google.gson.Gson;
import main.java.com.raev.model.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataParserEmployee {

    private Path jsonFilePath;

    public DataParserEmployee(Path jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public Employee[] parseData() {
        Gson gson = new Gson();
        Employee[] employees = gson.fromJson(readFile(), Employee[].class);

        return employees;
    }

    private String readFile(){
        String data = null;
        try {
            data = Files.readString(jsonFilePath);
        } catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }
}
