package framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    public static Logger getLogger(String logName) {
        return LogManager.getLogger(logName);
    }
    
}
