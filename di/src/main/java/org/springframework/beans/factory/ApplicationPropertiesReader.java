package org.springframework.beans.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ApplicationPropertiesReader {
    private String path;
    ClassLoader classLoader;
    String packages;

    FileInputStream fis;
    Properties property = new Properties();


    public void pathApplicProp(String path, ClassLoader classLoader) throws IOException, URISyntaxException  {
        this.path = path;
        this.classLoader = classLoader;

        instantiateBean();
    }

    public String instantiateBean ()throws IOException, URISyntaxException {
        Enumeration<URL> resources = classLoader.getResources(path);
        String file = String.valueOf(Paths.get(resources.nextElement().toURI()).toFile());
        String file1 = file.substring(0, file.length() - path.length());

        return file1;
    }

    public String setProperty() {
        try {
            fis = new FileInputStream(instantiateBean() + "application.properties");
            property.load(fis);
            String host = property.getProperty("my.param.db");
        } catch (IOException | URISyntaxException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        return property.getProperty("my.param.db");

    }


}






