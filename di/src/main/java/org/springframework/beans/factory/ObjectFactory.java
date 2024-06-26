package org.springframework.beans.factory;

import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.interfaces.BeanPostProcessor;
import org.springframework.beans.scanner.PackagesReaderWithReflection;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.*;

import java.util.stream.Collectors;

public class ObjectFactory {

    private PackagesReaderWithReflection packagesReader;
    private List<BeanPostProcessor> configurators = new ArrayList<>();
    private ApplicationContext applicationContext;

    public ObjectFactory(ApplicationContext applicationContext) throws Exception {
        for (Class<? extends BeanPostProcessor> aClass : applicationContext.getPackagesReaderWithReflection().getReflectionsSpring().getSubTypesOf(BeanPostProcessor.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

        this.applicationContext = applicationContext;

    }


    public <T> T createObject(Class<? extends T> implClass) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        T t = create(implClass);
        configure(t);
        return t;
    }


    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> {
            try {
                objectConfigurator.configure(t, applicationContext);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private <T> T create(Class<T> implClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Set<Constructor<?>> constructors = Arrays.stream(implClass.getDeclaredConstructors()).filter(constructor -> constructor.isAnnotationPresent(Autowired.class)).collect(Collectors.toSet());

        if (constructors.size() == 1 ) {
            Constructor<?> constructor = constructors.iterator().next();
            return createObgectWithConstructorAutowired(constructor, implClass);
        }
        if (constructors.size() >  1 ){
            throw new RuntimeException ( implClass.getName() + " must have exactly one constructor");
        }
        return implClass.getDeclaredConstructor().newInstance();
    }


    private <T> T createObgectWithConstructorAutowired(Constructor<?> constructor, Class<T> implClass) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<Object> objectList = new ArrayList<>();

        for (Class<?> parameter : constructor.getParameterTypes()) {
            if (parameter.isPrimitive()){
//                Если это будет boolean значение я просто  не знаю что мнне делать
                objectList.add(0);

            } else {

                Object object = applicationContext.getObject(parameter);
                objectList.add(object);

            }

        }
        return (T) constructor.newInstance(objectList.toArray());
    }
}
