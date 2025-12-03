package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllureReportManager {

    private static String getTimeStamp() {
        return new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
    }

    public static void generateReport(){

        try {
            // Custom folder and filename for reference
            String reportFolder = System.getProperty("user.dir")+"/report/RestAssured_Automation_Allure_Report_" + getTimeStamp();

            // Run mvn allure:report
            ProcessBuilder generatePb = new ProcessBuilder("mvn allure:report");
            generatePb.inheritIO().start().waitFor();

            System.out.println("Allure report generated in target/site/allure-maven-plugin");
            System.out.println("Custom folder reference: " + reportFolder);

            // Optional: mvn allure:serve to view report immediately
            ProcessBuilder servePb = new ProcessBuilder("mvn allure:serve");
            servePb.inheritIO().start().waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
