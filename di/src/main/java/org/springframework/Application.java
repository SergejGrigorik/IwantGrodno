package org.springframework;


import org.springframework.factory.ApplicationContext;
import org.springframework.factory.ObjectFactory;
import org.springframework.scanner.PackagesReaderWithReflection;

public class Application {
    public static ApplicationContext run(String packageToScan) throws Exception {
        PackagesReaderWithReflection packagesReader = new PackagesReaderWithReflection(packageToScan);
        ApplicationContext applicationContext = new ApplicationContext(packagesReader);
        ObjectFactory objectFactory = new ObjectFactory(applicationContext);


        applicationContext.setObjectFactory(objectFactory);
        return applicationContext;
    }


}
