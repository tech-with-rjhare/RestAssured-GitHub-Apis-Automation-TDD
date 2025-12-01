package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AllureReportManager {


    /**
     * Generates Allure HTML report and opens it in the browser.
     *
     * //@param resultsDir Path where Allure JSON results are stored (e.g., "allure-results")
     * @param reportDir  Path where the HTML report should be saved (e.g., "reports/allure-report")
     */
    public static void generateAndOpenReport(String reportDir) {


        try {
            String mvnWrapper = System.getProperty("os.name").toLowerCase().contains("win") ? "mvnw.cmd" : "./mvnw";
            // 1. Generate Allure report
            ProcessBuilder generateReport = new ProcessBuilder(mvnWrapper, "allure:report");
            generateReport.inheritIO().start().waitFor();

            // 2. Open report in browser
            File indexFile = new File(reportDir + "/index.html");
            if (indexFile.exists()) {
                Desktop.getDesktop().browse(indexFile.toURI());
                System.out.println("✅ Allure report generated and opened successfully!");
            } else {
                System.err.println("❌ Report not found at: " + indexFile.getAbsolutePath());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("❌ Failed to generate or open Allure report.");
        }
    }


}
