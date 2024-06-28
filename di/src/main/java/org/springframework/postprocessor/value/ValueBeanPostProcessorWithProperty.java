package org.springframework.postprocessor.value;

import org.springframework.factory.ApplicationContext;
import org.springframework.postprocessor.beanpostprocessorinterface.BeanPostProcessor;
import org.springframework.postprocessor.value.interfaces.Value;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

public class ValueBeanPostProcessorWithProperty implements BeanPostProcessor {

    private HashMap<String, String> cashValue = new HashMap<>();

    public ValueBeanPostProcessorWithProperty() {
        createCashValue();
    }

    private void createCashValue() {
        Properties properties = new Properties();
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        try {
            properties.load(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String key : properties.stringPropertyNames())
            cashValue.put(key, properties.getProperty(key));
    }

    @Override
    public void configure(Object bean, ApplicationContext context) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        Class<?> implClass = bean.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation != null) {
                field.setAccessible(true);
                field.set(bean, cashValue.get(annotation.name()));
            }
        }
    }


}

