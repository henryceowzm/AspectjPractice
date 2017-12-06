package com.henry;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    final static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) {
        Point p = new Point(5, 5);
        logger.info(logger.toString());
        if(logger instanceof org.apache.log4j.Logger) {
            ((org.apache.log4j.Logger)logger).info(p);
            ((org.apache.log4j.Logger)logger).setLevel(Level.FATAL);
            logger.info("should not print out this line " + p);
        }else {
            logger.info("should not print out this line in else block" + p);
        }
    }
}
