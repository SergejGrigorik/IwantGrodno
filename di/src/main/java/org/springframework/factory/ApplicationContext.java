package org.springframework.factory;

import org.springframework.scanner.PackagesReaderWithReflection;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private PackagesReaderWithReflection packagesReader;
    private ObjectFactory objectFactory;

    private Map<Class, Object> cache = new ConcurrentHashMap<>();

    public ApplicationContext(PackagesReaderWithReflection packagesReader) throws Exception {
        this.packagesReader = packagesReader;

    }


    public <T> T getObject(Class<T> type) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        if (cache.containsKey(type)) {

            return (T) cache.get(type);
        }


        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = packagesReader.getCashClassImpl(type);
        }


        T t = objectFactory.createObject(implClass);
        cache.put(type, t);


        return t;
    }

    public PackagesReaderWithReflection getPackagesReaderWithReflection() {
        return packagesReader;
    }

    public void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }


}
