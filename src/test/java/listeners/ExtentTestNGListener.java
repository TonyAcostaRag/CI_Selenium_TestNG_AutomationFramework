package listeners;

import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import framework.Log;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static final Logger log = Log.getLogger(
        ExtentTestNGListener.class.getName()
    );

    @Override
    public void onStart(ITestContext context) {
        log.info(">>> TESTNG LISTENER STARTED <<<", context.getName());
    }


    @Override
    public void onTestStart(ITestResult result) {
        log.info("---> Starting test: {} ", result.getMethod().getMethodName());

        String correlationId = UUID.randomUUID().toString().substring(0, 8);
        ThreadContext.put("CID", correlationId);

        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );

        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASSED TEST: {}", result.getMethod().getMethodName());
        ExtentTestManager.getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            log.error("FAILED TEST: {}", result.getMethod().getMethodName(),
                                result.getThrowable());
            ExtentTestManager.getTest().fail(result.getThrowable());
        } catch (Exception e) {
            log.error("Listener failed during onTestFailure", e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("TEST SUITE FINISHED: {}", context.getName());
        ThreadContext.clearAll();
        extent.flush();
    }
}
