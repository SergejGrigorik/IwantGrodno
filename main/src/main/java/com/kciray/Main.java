package com.kciray;

import com.kciray.controller.impl.Controller;
import org.springframework.Application;
import org.springframework.factory.ApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        Class<Main> mainClass = Main.class;
        String s = mainClass.getPackage().getName();
        ApplicationContext application = Application.run(s);
        application.getObject(Controller.class).execute();
        
    }
}

