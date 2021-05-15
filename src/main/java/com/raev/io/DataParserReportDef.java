package main.java.com.raev.io;

import com.google.gson.Gson;
import main.java.com.raev.model.ReportDef;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataParserReportDef {

    private Path jsonFilePath;

    public DataParserReportDef(Path jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public ReportDef parseData() {
        Gson gson = new Gson();
        ReportDef reportDef = new ReportDef();
        reportDef = gson.fromJson(readFile(), reportDef.getClass());

        return reportDef;
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
