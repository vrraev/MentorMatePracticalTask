package main.java.com.raev.io;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    private final String reportPath = "src\\main\\java\\com\\raev\\data\\Report.csv";

    private List<String[]> list;

    public CsvWriter(List<String[]> list) {
        this.list = list;
    }

    public void writeData()
    {
        File file = new File(reportPath);
        try (FileWriter outputFile = new FileWriter(file);
             CSVWriter writer = new CSVWriter(outputFile)){

            outputFile.flush();
            writer.flush();

            String[] header = { "Name", "Score" };
            writer.writeNext(header);

            writer.writeAll(list);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nReport generated in folder: " + reportPath);
    }
}
