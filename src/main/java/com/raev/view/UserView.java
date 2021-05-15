package main.java.com.raev.view;

import main.java.com.raev.service.ReportService;

import java.util.Scanner;

public class UserView {
    private Scanner scanner;

    public UserView() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        //Hint for paths
        System.out.println("Hint demo for paths:");
        System.out.println("src\\main\\java\\com\\raev\\data\\Employees.json");
        System.out.printf("src\\main\\java\\com\\raev\\data\\ReportDef.json\n\n");

        String jsonEmployeesFilePath = getInputPath("Employee");
        String jsonReportFilePath = getInputPath("Report Definition");
        ReportService reportService = new ReportService(jsonEmployeesFilePath, jsonReportFilePath);

        reportService.run();
    }

    private String getInputPath(String kindOfPath){
        System.out.flush();

        System.out.print("Please input path to JSON data file for " + kindOfPath + ": ");
        return scanner.nextLine();
    }
}
