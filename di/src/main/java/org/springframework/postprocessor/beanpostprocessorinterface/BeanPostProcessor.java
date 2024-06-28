package org.springframework.postprocessor.beanpostprocessorinterface;

import org.springframework.factory.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

public interface BeanPostProcessor {
    void configure(Object bean, ApplicationContext context) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

}
