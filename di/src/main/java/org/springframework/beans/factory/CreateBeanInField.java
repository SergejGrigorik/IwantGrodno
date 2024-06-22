package org.springframework.beans.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class CreateBeanInField {
//    private Map<String,Object> singletons ;
//
//    public CreateBeanInField(Map<String, Object> singletons) {
//        this.singletons = singletons;
//    }
//
//    public void populatePropertiesSetField(Object object, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        if (!field.getType().isInterface()) {
//            SetFieldDependency(field, object);
//        } else if (field.getType().isInterface()) {
//            SetFieldDependencyInterf(field, object);
//        }
//
//
//    }
//
//    private void SetFieldDependencyInterf(Field field, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        for (Object dependency : singletons.values()) {
//            Class[] interfaces = dependency.getClass().getInterfaces();
//            for (Class interf : interfaces) {
//                if (interf.equals(field.getType())) {
//                    serchDepe ndency(dependency);
//                    String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
//                    Method setter = object.getClass().getMethod(setterName, interf);
//                    setter.invoke(object, dependency);
//                }
//            }
//
//        }
//    }
//
//    private void SetFieldDependency(Field field, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        for (Object dependency : singletons.values()) {
//            if (dependency.getClass().equals(field.getType())) {
//                serchDependency(dependency);
//                String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
//                Method setter = object.getClass().getMethod(setterName, dependency.getClass());
//                setter.invoke(object, dependency);
//            }
//        }
//    }

}
