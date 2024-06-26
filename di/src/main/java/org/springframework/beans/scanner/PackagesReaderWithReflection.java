package org.springframework.beans.scanner;

import org.reflections.Reflections;
import org.springframework.beans.annotation.Component;

import java.util.HashMap;
import java.util.Set;

public class PackagesReaderWithReflection {
    private Reflections reflections;
    private Reflections reflections1;
    private Set<Class<?>> classes;



    public PackagesReaderWithReflection(String packageToScan) {
        reflections = new Reflections(packageToScan);
        createClassesWithAnnatationComponent();
    }

    public Reflections getReflectionsSpring() {

        return reflections1 = new Reflections("org.springframework");
    }

    private Set<Class<?>> createClassesWithAnnatationComponent() {
        classes = reflections.getTypesAnnotatedWith(Component.class);
        return classes;
    }



    public Set<Class<?>> getClassesWithAnnatationComponent() {

        return createClassesWithAnnatationComponent();
    }




    public <T> Class<? extends T> getImplClass(Class<T> type) {
        Set<Class<? extends T>> classes = reflections.getSubTypesOf(type);
        if (classes.size() != 1) {
            throw new RuntimeException(type + " has 0 or more than one impl please update your config");
        }
        return classes.iterator().next();
    }

    public Reflections getReflections() {
        return reflections;
    }

}
