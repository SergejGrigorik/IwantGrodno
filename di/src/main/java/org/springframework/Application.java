package org.springframework;


import org.springframework.beans.factory.ApplicationContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.scanner.PackagesReaderWithReflection;

public class Application {
    public static ApplicationContext run(String packageToScan) throws Exception {
        PackagesReaderWithReflection packagesReader = new PackagesReaderWithReflection(packageToScan);
        ApplicationContext applicationContext = new ApplicationContext(packagesReader);
        ObjectFactory objectFactory = new ObjectFactory(applicationContext);


        applicationContext.setObjectFactory(objectFactory);
        applicationContext.createSingletonsWithAnnatationComponent();

        return applicationContext;
    }


}
