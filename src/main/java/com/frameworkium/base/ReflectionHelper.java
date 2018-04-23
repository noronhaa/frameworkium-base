package com.frameworkium.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReflectionHelper {

    protected final Logger logger = LogManager.getLogger(this);

    public static void main(String[] args) {
        new ReflectionHelper().addScreenshotToAllureIfUsing("", Paths.get(""));
    }

    /**
     * Calls com.frameworkium.reporting.allure.AllureLogger.logToAllure() if User is using Frameworkium-Reporting
     * @param clazz - Calling PageObjectClass for logging
     */
    public void logPageToAllureIfUsing(Class clazz){
        String message = "Page '" + clazz.getName() + "' successfully loaded";
        try {
            Class<?> Clazz = Class.forName("com.frameworkium.reporting.allure.AllureLogger");
            Method m = Clazz.getDeclaredMethod("logToAllure", String.class);
            m.invoke(Clazz,message);
        } catch (NoSuchMethodException e) {
            //
        } catch (ClassNotFoundException e) {
            logger.warn("AllureLogger: ClassNotFoundException - " +
                    "Not using Allure Reporting but page loaded successfully");
        } catch (IllegalAccessException e) {
            //
        } catch (InvocationTargetException e) {
            //
        }
    }

    /**
     * Calls com.frameworkium.reporting.allure.AllureProperties.create() if User is using Frameworkium-Reporting
     */
    public void createAllureProperties(){
        try {
            Class<?> Clazz = Class.forName("com.frameworkium.reporting.allure.AllureProperties");
            Method m = Clazz.getDeclaredMethod("create");
            m.invoke(Clazz);
        } catch (NoSuchMethodException e) {
            //
        } catch (ClassNotFoundException e) {
            logger.warn("AllureProperties: ClassNotFoundException - " +
                    "Not using Allure Reporting");
        } catch (IllegalAccessException e) {
            //
        } catch (InvocationTargetException e) {
            //
        }
    }

    /**
     * Calls com.frameworkium.reporting.allure.AllureScreenshotHelper.addScreenshotToAllureIfUsing()
     * if User is using Frameworkium-Reporting
     * @param name of screenshot
     * @param path path to screenshot
     */
    public void addScreenshotToAllureIfUsing(String name, Path path){
        System.out.println("adding screenshot to allure");
        try {
            Class<?> Clazz = Class.forName("com.frameworkium.reporting.allure.AllureScreenshotHelper");
            Method m = Clazz.getDeclaredMethod("addScreenshotToAllureIfUsing",String.class, Path.class);
            m.invoke(Clazz.newInstance(),name,path);
        } catch (NoSuchMethodException e) {
            //
        } catch (ClassNotFoundException e) {
            logger.warn("AllureScreenshotHelper: ClassNotFoundException - " +
                    "Not using Allure Reporting");
        } catch (IllegalAccessException e) {
            //
        } catch (InvocationTargetException e) {
            //
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


}
