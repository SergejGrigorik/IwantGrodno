package org.springframework.beans.postprocessor;

import org.springframework.beans.annotation.Value;
import org.springframework.beans.factory.ApplicationContext;
import org.springframework.beans.interfaces.BeanPostProcessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ValueBeanPostProcessor implements BeanPostProcessor {
    private Map<String, String> propertiesMap;


    public ValueBeanPostProcessor() throws FileNotFoundException {
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));

    }

    @Override

    public void configure(Object t,ApplicationContext context) throws IllegalAccessException {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation != null) {
                String value = annotation.name().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.name());
                field.setAccessible(true);
                field.set(t,value);
            }
        }
    }
}
