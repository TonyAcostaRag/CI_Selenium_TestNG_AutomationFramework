package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {}

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("target/TestReport.html");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
