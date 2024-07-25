package com.mustafaaslantas;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.mustafaaslantas.runner.TestRunner;

public class Main {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestRunner.class);
        for (Failure failure : result.getFailures()) {
           System.out.println(failure.toString());
        }

        System.out.println("Başarılı: " + result.wasSuccessful());


    }
}