package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLogger {

    protected static final Logger logger = LogManager.getLogger(Log4jLogger.class.getName());

    public static void startTestCase(String testCaseName){
        logger.info("########################################################################");
        logger.info("########################################################################");
        logger.info("######           "+testCaseName+"              ######");
        logger.info("########################################################################");
        logger.info("########################################################################");
    }

    public static void endTestCase(){
        logger.info("###############            "+"--E---N---D--"+"           ###############");
        logger.info("########################################################################");
        logger.info("-------X-------------X--------------X--------------X-------------X------");
        logger.info("X");
        logger.info("X");
        logger.info("X");
        logger.info("X");
        logger.info("X");

    }

    public static void info(String message){
        logger.info(message);
    }

    public static void error(String message){
        logger.error(message);
    }


    public static void fatal(String message){
        logger.fatal(message);
    }

    public static void warn(String message){
        logger.warn(message);
    }


    public void debug(String message){
        this.debug(message);
    }




}
