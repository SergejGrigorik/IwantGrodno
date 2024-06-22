package com.kciray;

import com.kciray.controller.impl.ControllerImpl;
import com.kciray.dao.DaoInterface;
import com.kciray.dao.impl.DaoImpl;
import com.kciray.service.ServiceInterface;
import com.kciray.service.impl.ServiceImpl;
import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.factory.ApplicationPropertiesReader;
import org.springframework.beans.factory.BeanFactory;

import javax.management.MBeanServerFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.instantiateBean("com.kciray");
        beanFactory.populatePropertiesSet();

//        beanFactory.populatePropertiesSetField();
        beanFactory.injectBeanNames();
//        beanFactory.populatePropertiesSetMethod();
//        beanFactory.populatePropertiesSetFildsInterf();
        ProductService productService = (ProductService) beanFactory.getBean("productService");
        PromotionsService promotionsService1 = productService.getPromotionsService();

        PromotionsService promotionsService = (PromotionsService) beanFactory.getBean("promotionsService");
        ControllerImpl controller = (ControllerImpl) beanFactory.getBean("controllerImpl");
        DaoImpl daoInterface = (DaoImpl) beanFactory.getBean("daoImpl");
        System.out.println(daoInterface.getOooo());
        ServiceImpl serviceInterface = (ServiceImpl) beanFactory.getBean("serviceImpl");
        System.out.println(productService+   "    "  +serviceInterface + "   " + daoInterface + "     " + controller);
        System.out.println(serviceInterface.getDaoInterface());
        System.out.println(controller.getServiceInterface());
//        System.out.println(controller.getServiceImpl());

//        for (Class classes : beanFactory.getArrayList()){
//            for (Object c : beanFactory.getSingletons().values()){
//                Class [] lasses1 = c.getClass().getInterfaces();
//                for (Class class1 : lasses1) {
//                    if (class1.equals(classes)) {
//                        System.out.println(c.getClass() + "   " + classes.getName() + "eeeeeeeeeeeeeeeeeeeeeeeeb  ");
//                    }
//                }
//
//            }
//        }



    }
}

