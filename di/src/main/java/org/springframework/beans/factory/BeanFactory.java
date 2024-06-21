package org.springframework.beans.factory;

import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.annotation.Component;
import org.springframework.beans.interfaces.BeanNameAware;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class BeanFactory {
    private Map<String, Object> singletons = new HashMap<>();
    private ArrayList<Class> interfaces = new ArrayList<Class>();

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }

    public Map<String, Object> getSingletons() {
        return singletons;
    }

    public ArrayList<Class> getInterfaces() {
        return interfaces;
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

//    private void populatePropertiesSet(){
//        for (Object object : singletons){
//
//        }
//    }


    public void populatePropertiesSetField() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (Object object : singletons.values()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class) && !field.getType().isInterface()) {
                    SetFieldDependency(field, object);
                } else if (field.isAnnotationPresent(Autowired.class) && field.getType().isInterface()) {
                    SetFieldDependencyInterf(field, object);
                }
            }
        }
    }

    private void SetFieldDependencyInterf(Field field, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Object dependency : singletons.values()) {
            Class[] interfaces = dependency.getClass().getInterfaces();
            for (Class interf : interfaces) {
                if (interf.equals(field.getType())) {



                    String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    Method setter = object.getClass().getMethod(setterName, interf);
                    setter.invoke(object, dependency);
                }
            }

        }
    }

    private void SetFieldDependency(Field field, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Object dependency : singletons.values()) {
            if (dependency.getClass().equals(field.getType())) {
                String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Method setter = object.getClass().getMethod(setterName, dependency.getClass());
                setter.invoke(object, dependency);
            }
        }
    }



    public void populatePropertiesSetMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Object object : singletons.values()) {
            Method[] methods = object.getClass().getDeclaredMethods();
            for (Method method : methods) {
                Class [] paramTypes = method.getParameterTypes();
                for (Class patamType : paramTypes) {
                    if (method.isAnnotationPresent(Autowired.class) && patamType.isInterface()) {
                        setMethodInterf(method, object);
                    } else if (method.isAnnotationPresent(Autowired.class) && !patamType.isInterface()) {
                        setMethod(method, object,patamType);

                    }
                }
            }
        }
    }

    private void setMethod(Method method, Object object,Class parametrType) throws InvocationTargetException, IllegalAccessException {
        for (Object dependency : singletons.values()) {
                if (dependency.getClass().equals(parametrType)) {
                    method.invoke(object, dependency);
                }
        }
    }

    private void setMethodInterf(Method method, Object object) throws InvocationTargetException, IllegalAccessException {
        for (Object dependency : singletons.values()) {
            Class[] interfaces = dependency.getClass().getInterfaces();
            for (Class interfaceClass : interfaces)
                if (interfaceClass.getName().equals(method.getParameterTypes()[0].getName())) {
                    method.invoke(object, dependency);
                }
        }

    }
    public void populatePropertiesSetConstructor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        for (Object object : singletons.values()) {
            Constructor[] constructors = object.getClass().getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                Class [] paramTypes = constructor.getParameterTypes();
                for (Class patamType : paramTypes) {
                    if (constructor.isAnnotationPresent(Autowired.class) && patamType.isInterface()) {
                        setConstructorInterf(constructor, object,patamType);
                    } else if (constructor.isAnnotationPresent(Autowired.class) && !patamType.isInterface()) {
                        setConstructor(constructor,patamType);

                    }
                }
            }
        }
    }

    private void setConstructor(Constructor constructor, Class parametrType) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Object dependency : singletons.values()) {
            if (dependency.getClass().equals(parametrType)) {
                constructor.newInstance(dependency);
            }
        }
    }

    private void setConstructorInterf(Constructor constructor, Object object,Class parametrType) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Object dependency : singletons.values()) {
            Class<?>[] interfaces = dependency.getClass().getInterfaces();
            for (Class interfac : interfaces){
                if (interfac.equals(parametrType)){
                    constructor.;
                    ;
                }
            }
        }

    }


    public void injectBeanNames() {
        for (String beanName : singletons.keySet()) {
            Object object = singletons.get(beanName);
            if (object instanceof BeanNameAware) {
                ((BeanNameAware) object).setBeanName(beanName);
            }
        }
    }


}
