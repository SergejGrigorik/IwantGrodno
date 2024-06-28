package org.springframework.postprocessor.autowired;

import org.springframework.factory.ApplicationContext;
import org.springframework.postprocessor.autowired.annotation.Autowired;
import org.springframework.postprocessor.beanpostprocessorinterface.BeanPostProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AutowiredBeanPostProcessor implements BeanPostProcessor {
    private Map<String, String> propertiesMap;


    @Override
    public void configure(Object bean, ApplicationContext context) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(bean, object);
                field.setAccessible(false);
            }
        }
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Autowired.class)) {
                method.setAccessible(true);
                List<Object> args = Arrays.asList(method.getParameterTypes());
                if (args.size() != 1) {
                    throw new IllegalAccessException(method.getName() + " must have exactly one argument");
                }
                Object object = context.getObject(Arrays.stream(method.getParameterTypes()).iterator().next());
                method.invoke(bean, object);
                method.setAccessible(false);
            }
        }
    }
}
