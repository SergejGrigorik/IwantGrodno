package org.springframework.beans.factory;

import org.springframework.beans.annotation.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PackageReader {
    private Map<String, Object> singletons = new HashMap<>();
    private ArrayList<Class> interfaces = new ArrayList<Class>();
    public Map<String, Object> getSingletons() {
        return singletons;
    }
    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }


    public void instantiateBean(String basePackage) throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String path = basePackage.replace('.', '\\');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> q = Files.walk(Paths.get(resources.nextElement().toURI()))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        for (File currentFile : q) {
            String fileName = currentFile.getName();
            if (fileName.endsWith(".class")) {
                String s = String.valueOf(currentFile);
                String r = s.substring(s.lastIndexOf(path), s.lastIndexOf("\\"));
                String path2 = r.replace('\\', '.');
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                Class classObject = Class.forName(path2 + "." + className);

                if (classObject.isAnnotationPresent(Component.class)) {
                    Object object = classObject.newInstance();
                    String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                    singletons.put(beanName, object);
                }
                if (classObject.isInterface()) {
                    interfaces.add(classObject);
                }
            }
        }
    }

}
