package org.springframework.beans.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CreateBeanInMethod {
//    private Map<String,Object> singletons ;
//    public CreateBeanInMethod(Map<String,Object> singletons) {
//        this.singletons = singletons;
//
//    }
//
//    public void populatePropertiesSetMethod(Method method, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Class[] paramTypes = method.getParameterTypes();
//        for (Class patamType : paramTypes) {
//            if (patamType.isInterface()) {
//                setMethodInterf(method, object);
//            } else if (!patamType.isInterface()) {
//                setMethod(method, object, patamType);
//
//            }
//        }
//    }
//
//    private void setMethod(Method method, Object object, Class parametrType) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        for (Object dependency : singletons.values()) {
//            if (dependency.getClass().equals(parametrType)) {
//                serchDependency(dependency);
//                method.invoke(object, dependency);
//            }
//        }
//    }
//
//    private void setMethodInterf(Method method, Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        for (Object dependency : singletons.values()) {
//            Class[] interfaces = dependency.getClass().getInterfaces();
//            for (Class interfaceClass : interfaces)
//                if (interfaceClass.getName().equals(method.getParameterTypes()[0].getName())) {
//                    serchDependency(dependency);
//                    method.invoke(object, dependency);
//                }
//        }
//
//    }
}
