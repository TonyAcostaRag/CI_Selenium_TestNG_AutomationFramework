package listeners;
import org.apache.logging.log4j.Logger;

import framework.Log;
import io.qameta.allure.Allure;

public class TestLog {

    private static final ThreadLocal<Logger> log = 
        ThreadLocal.withInitial(() -> 
            Log.getLogger(Thread.currentThread().getName())
        );
    
    public static void pass (String message) {
        
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().pass(message);
            Allure.step(message);
        }
    }
    
    public static void info (String message) {
        log.get().info(message);
        
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().info(message);
            Allure.step(message);
        }
    }

    public static void error(String message, Throwable t) {
        log.get().error(message, t);
        
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().info(message);
            Allure.step("ERROR:" + message);
        }
    }
    
}
