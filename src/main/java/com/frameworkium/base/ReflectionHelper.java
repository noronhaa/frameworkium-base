package com.frameworkium.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;

public class ReflectionHelper {

    private final static Logger logger = LogManager.getLogger();

    /**
     * Calls com.frameworkium.reporting.allure.AllureLogger.logToAllure()
     * if using Frameworkium-Reporting
     *
     * @param clazz - Calling PageObjectClass for logging
     */
    public void logPageToAllureIfUsing(Class clazz) {
        try {
            Class<?> allureLoggerClass = Class.forName("com.frameworkium.reporting.allure.AllureLogger");
            Method m = allureLoggerClass.getDeclaredMethod("logToAllure", String.class);
            String message = "Page '" + clazz.getName() + "' successfully loaded";
            m.invoke(allureLoggerClass, message);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            logger.debug("AllureLogger: ClassNotFoundException - " +
                    "Not using Allure Reporting but page loaded successfully");
        }
    }

    /**
     * Calls com.frameworkium.reporting.allure.AllureProperties.create()
     * if using Frameworkium-Reporting
     */
    public void createAllureProperties() {
        try {
            Class<?> Clazz = Class.forName("com.frameworkium.reporting.allure.AllureProperties");
            Method m = Clazz.getDeclaredMethod("create");
            m.invoke(Clazz);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            logger.debug("AllureProperties: ClassNotFoundException - " +
                    "Not using Allure Reporting");
        }
    }

    /**
     * Calls com.frameworkium.reporting.allure.AllureScreenshotHelper.addScreenshotToAllureIfUsing()
     * if using Frameworkium-Reporting
     *
     * @param name of screenshot
     * @param path path to screenshot
     */
    public void addScreenshotToAllureIfUsing(String name, Path path) {
        try {
            Class<?> Clazz = Class.forName("com.frameworkium.reporting.allure.AllureScreenshotHelper");
            Method m = Clazz.getDeclaredMethod("addScreenshotToAllureIfUsing", String.class, Path.class);
            m.invoke(Clazz.newInstance(), name, path);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            logger.debug("AllureScreenshotHelper: ClassNotFoundException - " +
                    "Not using Allure Reporting");
        }
    }
}
