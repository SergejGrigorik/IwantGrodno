package org.springframework.beans.postprocessor;

import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.factory.ApplicationContext;
import org.springframework.beans.interfaces.BeanPostProcessor;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AutowiredBeanPostProcessor implements BeanPostProcessor {
    private Map<String, String> propertiesMap;


    @Override
    public void configure(Object bean, ApplicationContext context) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(bean, object);
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
            }
        }

//        Set<Constructor<?>> constructors = Arrays.stream(
//                        bean.getClass().getDeclaredConstructors())
//                .filter(constructor -> constructor.isAnnotationPresent(Autowired.class))
//                .collect(Collectors.toSet());
//        if (constructors.size() > 1) {
//            throw new IllegalAccessException(bean.getClass().getName() + " must have exactly one constructor");
//        }
//        for (Constructor constructor : constructors) {
//            constructor.setAccessible(true);
//            Parameter[] parameterTypes = constructor.getParameters();
//            for (Parameter parameterType : parameterTypes) {
//                parameterType.getType();
//                Object object = context.getObject(parameterType.getType());
//                for (Field field : bean.getClass().getDeclaredFields()) {
//                    if (field.getType().equals(parameterType.getType())) {
//                        field.setAccessible(true);
//                        field.set(bean, object);
//                    }
//                }
//
//            }
//        }
    }
}
