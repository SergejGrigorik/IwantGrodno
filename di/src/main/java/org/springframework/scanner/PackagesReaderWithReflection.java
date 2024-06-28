package org.springframework.scanner;

import org.reflections.Reflections;
import org.springframework.annotation.Component;

import java.util.HashMap;
import java.util.Set;

public class PackagesReaderWithReflection {
    private Reflections reflections;
    private final HashMap<Class<?>, Class<?>> cashClassImpl = new HashMap<>();
    ;
    private Set<Class<?>> classes;
    private Reflections reflections1 = new Reflections("org.springframework");


    public PackagesReaderWithReflection(String packageToScan) {
        this.reflections = new Reflections(packageToScan);
        createClassesWithAnnatationComponent();
        createClassesWithInterf();
    }

    public Reflections getReflectionsSpring() {

        return reflections1;
    }

    public Set<Class<?>> createClassesWithAnnatationComponent() {
        classes = reflections.getTypesAnnotatedWith(Component.class);
        reflections.getSubTypesOf(Object.class);
        return classes;
    }


    private void createClassesWithInterf() {
        for (Class<?> clazz : classes) {
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> interf : interfaces) {
                cashClassImpl.put(interf, clazz);

//                Очень хотел через stream сделать в одну строчку , немного не получалось , сегодня выделю вечер на стримы и лямбды . Хочу красиво делать , как ты показывал
            }
        }
    }

    public <T> Class<? extends T> getCashClassImpl(Class<T> type) {
        return (Class<? extends T>) cashClassImpl.get(type);
    }
}
