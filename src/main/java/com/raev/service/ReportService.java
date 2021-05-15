package main.java.com.raev.service;

import main.java.com.raev.io.CsvWriter;
import main.java.com.raev.io.DataParserEmployee;
import main.java.com.raev.io.DataParserReportDef;
import main.java.com.raev.model.Employee;
import main.java.com.raev.model.ReportDef;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private Path jsonEmployeesFile;
    private Path jsonReportFile;

    public ReportService(String jsonEmployeesFilePath, String jsonReportFilePath) {
        this.jsonEmployeesFile = Path.of(jsonEmployeesFilePath);
        this.jsonReportFile = Path.of(jsonReportFilePath);
    }

    public void run(){
        generateReport();
    }

    private void generateReport(){
        DataParserEmployee dataParserEmployee = new DataParserEmployee(jsonEmployeesFile);
        DataParserReportDef dataParserReportDef = new DataParserReportDef(jsonReportFile);
        Employee[] employees = dataParserEmployee.parseData();
        ReportDef reportDef = dataParserReportDef.parseData();
        CsvWriter csvWriter = new CsvWriter(generateReportData(employees, reportDef));
        csvWriter.writeData();
    }

    private List<String[]> generateReportData(Employee[] employees, ReportDef reportDef){
        List<String[]> list = new ArrayList<>();
        double minScore = Double.MAX_VALUE;
        double maxScore = Double.MIN_VALUE;

        for (Employee employee : employees){
            double score = (double)employee.getTotalSales() / employee.getSalesPeriod();
            if (reportDef.isUseExprienceMultiplier()){
                score *= employee.getExperienceMultiplier();
            }
            employee.setScore(score);
            minScore = Math.min(minScore, score);
            maxScore = Math.max(maxScore, score);
        }

        /*
        *   Defining "score that is within the top X percent of the results":
        *   Getting MIN and MAX score.
        *   Calculating the delta range as ABSOLUTE score range as MAX - MIN.
        *   Getting the top X percent of that absolute range.
        *   Defining threshold as MAX score minus that percentage of ABSOLUTE score.
         */

        double absoluteScoreRange = maxScore - minScore;
        double scoreThreshold = maxScore - (absoluteScoreRange * reportDef.getTopPerformersThreshold() / 100);

        /*
        *   Because it is not clear if both conditions should be true,
        *   I decide that both must be true for the Employee to be included in the report.
         */

        for (Employee employee : employees){
            if (employee.getSalesPeriod() <= reportDef.getPeriodLimit() && employee.getScore() >= scoreThreshold) {
                String[] data = new String[2];
                data[0] = employee.getName();
                data[1] = String.valueOf(employee.getScore());
                list.add(data);
            }
        }
        return list;
    }

}
