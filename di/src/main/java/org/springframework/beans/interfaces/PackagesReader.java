package org.springframework.beans.interfaces;

import java.util.Set;

public interface PackagesReader {
    Set<Class<?>> getClassesWithAnnatationComponent();

    <T> Class<? extends T> getImplClass(Class<T> type);
}
