package org.springframework.beans.interfaces;

import org.springframework.beans.factory.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

public interface BeanPostProcessor {
    void configure(Object bean, ApplicationContext context) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

}
